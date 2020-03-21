import java.util.*;


public class XQueryRewriterVisitor2 extends XQueryOptOverriddenVisitor{

    //####################################################################################
    // define data structures to build a graph dependency for variables in the query, help detect join
    //      Node -> QueryTree -> DependencyGraph
    //####################################################################################
    class Node extends Object{
        String name;
        String queryClause;
        Node parent;

        public Node(String name, String queryClause, Node parent){
            this.name = name;
            this.queryClause = queryClause;
            this.parent = parent;
        }
    }

    class QueryTree{
        Node root;
        Map<String,Node> nodesMap;     // store its children nodes. map nodeName to node

        public QueryTree(Node nRoot){
            this.root = nRoot;
            nRoot.parent = null;
            nodesMap = new HashMap<>();
            nodesMap.put(nRoot.name, root);
        }

        public Node findNode(String variableName){
            if(this.nodesMap.containsKey(variableName)){
                return nodesMap.get(variableName);
            }
            return null;
        }

        public void addNode(Node newNode){
            this.nodesMap.put(newNode.name, newNode);
        }

        // run BSF to obtain the list of nodes in this QueryTree
        LinkedList<Node> BFS(){
            LinkedList<Node> res = new LinkedList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                Node cur = queue.poll();
                res.add(cur);
                for(String key : nodesMap.keySet()){
                    Node child = nodesMap.get(key);
                    if(child.parent == cur){
                        queue.add(child);
                    }
                }
            }
            return res;
        }
    }

    // construct a labeled graph that represents the input XQuery expressions
    // and captures the navigation and the conditions
    class DependencyGraph{

        public LinkedList<QueryTree> queryTrees;                //DependencyGraph holds a list of QueryTree

        /**
         * Map for variable value equalities,
         * for every equality ($a = $b), the set of equalities of $a contains $b and the set of equalities of $b contains $a
         */
        public Map<Node, Set<Node>> varEqualMap;

        /**
         * Map for variable value equality restrictions
         * for every equality ($a = "..." or "..." = $a), the list of equalities of $a contains "..."
         */
        public Map<Node, List<String>> varRestrictMap;

        /**
         * List of free value equality restrictions
         * for every equality ("..." = "..."), the list contains a string representation of the equality ("..." = "...")
         */
        public List<String> freeRestrictList;

//        /**
//         * List of variables already been joint into the rewritten join query
//         */
////        public List<Node> joinedNodes;
        LinkedList<List<Node>> joinNodesList;

        public boolean isOptimizable = false;

        public DependencyGraph(){
            this.queryTrees = new LinkedList<>();
            this.varEqualMap = new HashMap<>();
            this.varRestrictMap = new HashMap<>();
            this.freeRestrictList = new LinkedList<>();
//            this.joinedNodes = new LinkedList<>();
            this.joinNodesList = new LinkedList<>();

        }


        public Node findNodes(String targetNode){
            // traverse all queryTree to search for the targetNode
            for(QueryTree queryTree: this.queryTrees){
                if(queryTree.findNode(targetNode)!=null){
                    return queryTree.findNode(targetNode);
                }
            }
            return null;
        }

        public QueryTree findQueryTree(String targetNode){
            for(QueryTree queryTree: this.queryTrees){
                if(queryTree.findNode(targetNode)!=null){
                    return queryTree;
                }
            }
            return null;
        }

        /**
         * construct the XQuery string by the given queryTree
         *
         *    in the format of for ... where ... return ...
         *    for and return is mandatory, where is optional
         *
         *  EX  for $s in  doc("j_caesar.xml") //SPEAKER,
         *      $stxt in $s/text() where $stxt = "CAESAR"
         *      return <tuple>{<s>{$s}</s> , <stxt>{$stxt}</stxt>}</tuple>,
         */

        public String getNextQuery(){

            QueryTree queryTree = queryTrees.poll();
            List<Node> BFSList = queryTree.BFS();

            StringBuilder xqueryStr = new StringBuilder();

            //#######################   for  ###########################
            //#############################################################
            List<String> forVarList = new LinkedList<>();

            for(Node node : BFSList){
                String varQuery = node.name + " in " + node.queryClause;
                forVarList.add(varQuery);
            }

            xqueryStr.append(" for ");       // for $tuple in ...
            xqueryStr.append(String.join(",", forVarList));

            //#######################   where  ###########################
            //#############################################################
            List<String> whereList = new LinkedList<>();

            // check in varRestrictMap
            for(Node node : BFSList){
                if(varRestrictMap.containsKey(node)){
                    for(String constant : varRestrictMap.get(node)){
                        whereList.add(node.name +" = "+ constant);
                    }
                }
            }

            // update varEqualMap
            for(Node left : BFSList){
                for(Node right : BFSList){
                    if(varEqualMap.containsKey(left) && varEqualMap.get(left).contains(right)){
                        whereList.add(left.name + " = "+ right.name);
                        varEqualMap.get(left).remove(right);
                        varEqualMap.get(right).remove(left);
                    }
                }
            }

            // check if where exists
            if(whereList.size()>0){
                xqueryStr.append(" where ");
                xqueryStr.append( String.join(" and ", whereList));
            }

            //#######################   return  ###########################
            //#############################################################
            LinkedList<String> retValList = new LinkedList<>(); // holds the tuple to return

            // update
            for(Node node : BFSList){
                String var = node.name;
                String tagName= node.name.substring(1);
                String varTagStr = "<" + tagName + ">{" + var + "}</" + tagName + ">";
                retValList.add(varTagStr);
            }

            xqueryStr.append(" return ");
            xqueryStr.append("<tuple>{" + String.join(" , ", retValList) + "}</tuple>");

            return xqueryStr.toString();
        }
    }

    //####################################################################################
    // Build the DependencyGraph
    //####################################################################################
    // visitXqFWR()             ==> cosntruct the dependency graph,
    // visitPath()
    // visitCondEqual()         ==> update varEqualMap, freeRestrictList, varRestrictMap
    //####################################################################################
    DependencyGraph dependencyGraph = new DependencyGraph();


    /**
     * 'for' Variable 'in' path (',' Variable 'in' path)* 'where' cond 'return' returnClause      #XqFWR
     * @param ctx
     * @return String
     */
    @Override
    public String visitXqFWR(XQueryOptParser.XqFWRContext ctx) {

        //################################################################
        // 1  collect all the variables AND creat the dependency graph
        //################################################################
        for(int i=0;i<ctx.Variable().size();++i){

            String variable = ctx.Variable(i).getText();
            String queryPath = visit(ctx.path(i));       // visit path

            if(queryPath.trim().startsWith("doc")){     // for $s in  doc("j_caesar.xml") .....
                Node newRoot = new Node(variable,queryPath, null);
                QueryTree newTree = new QueryTree(newRoot);
                dependencyGraph.queryTrees.add(newTree);
            }else{
                String parentName = queryPath.split("/")[0];
                QueryTree qTree = dependencyGraph.findQueryTree(parentName);

                Node parentNode = qTree.findNode(parentName);
                Node newNode = new Node(variable,queryPath,parentNode);
                qTree.addNode(newNode);
            }
        }

        visit(ctx.cond());      //visit where


        //################################################################
        // 2    construct the rewrittern XQuery String given from the DependencyGraph
        //################################################################
        StringBuilder rewritternXQuery = new StringBuilder();

        rewritternXQuery.append("for $tuple in ");

        String joinStr = "";        //dependencyGraph.getNextQuery();

        LinkedList<String> joinStringList = new LinkedList<>();
        int index = 0;

        // execute bushy join !!!
        while(dependencyGraph.queryTrees.size()>0){

            List<Node> nextNodes1 = dependencyGraph.queryTrees.get(0).BFS();    // List<Node> nextNodes = dependencyGraph.queryTrees.peek().BFS();
            List<Node> nextNodes2 = dependencyGraph.queryTrees.get(1).BFS();

            LinkedList<String> leftStrLs = new LinkedList<>();
            LinkedList<String> rightStrLs = new LinkedList<>();

            nextNodes1.addAll(nextNodes2);
            dependencyGraph.joinNodesList.add(nextNodes1);  // add both to joinNodesList

            // detect if can rewrite to join
            for(Node leftNode : nextNodes1){
                if(dependencyGraph.varEqualMap.containsKey(leftNode)){
                Set<Node> leftEqualSet = dependencyGraph.varEqualMap.get(leftNode);
                for(Node rightNode : nextNodes2){
                    if(leftEqualSet.contains(rightNode)){
                        leftStrLs.add(leftNode.name.substring(1));
                        rightStrLs.add(rightNode.name.substring(1));
                        dependencyGraph.varEqualMap.get(leftNode).remove(rightNode);
                        dependencyGraph.varEqualMap.get(rightNode).remove(leftNode);
                    }
                }
                }
            }

            String nextQueryStr1  = dependencyGraph.getNextQuery(); // poll first node
            String nextQueryStr2  = dependencyGraph.getNextQuery(); // poll 2nd node

            // format the rewritten Xquery inside join(...)
            joinStringList.add( ("join(" +
                    nextQueryStr1 + "," +
                    nextQueryStr2 + "," +
                    "[" + String.join(",", leftStrLs) + "]," +
                    "[" + String.join(",", rightStrLs) + "])") );
        }

        // ##########################################################
        while(joinStringList.size()>1){

            // traverse all pairs of nodes in joinNodesList
            index = 0;
            while(index < dependencyGraph.joinNodesList.size()){

                List<Node> nextNodes1 = dependencyGraph.joinNodesList.get(index);
                List<Node> nextNodes2 = dependencyGraph.joinNodesList.get(index+1);

                LinkedList<String> leftStrLs = new LinkedList<>();
                LinkedList<String> rightStrLs = new LinkedList<>();

                // detect if can rewrite to join
                for(Node leftNode : nextNodes1){
                    if(dependencyGraph.varEqualMap.containsKey(leftNode)){
                        Set<Node> leftEqualSet = dependencyGraph.varEqualMap.get(leftNode);
                        for(Node rightNode : nextNodes2){
                            if(leftEqualSet.contains(rightNode)){
                                leftStrLs.add(leftNode.name.substring(1));
                                rightStrLs.add(rightNode.name.substring(1));
                                dependencyGraph.varEqualMap.get(leftNode).remove(rightNode);
                                dependencyGraph.varEqualMap.get(rightNode).remove(leftNode);
                            }
                        }
                    }
                }

                dependencyGraph.joinNodesList.pop();    // pop old nextNodes1
                dependencyGraph.joinNodesList.pop();    // pop old nextNodes2

                nextNodes1.addAll(nextNodes2);
                dependencyGraph.joinNodesList.add(nextNodes1);  // add both to joinNodesList

                // similarly
                String nextQueryStr1 = joinStringList.get(index);
                String nextQueryStr2 = joinStringList.get(index+1);

                joinStringList.pop();
                joinStringList.pop();

                joinStringList.add( ("join(" +
                        nextQueryStr1 + "," +
                        nextQueryStr2 + "," +
                        "[" + String.join(",", leftStrLs) + "]," +
                        "[" + String.join(",", rightStrLs) + "])") );


                index += 2;
            }
        }


        // ###########################################################
        // set to true if is optimizable
        dependencyGraph.isOptimizable = true;

        // finialize the format of  for $tuple in join(     ) return ....
        String retClauseStr = visit(ctx.returnClause());        // visit returnClause
        joinStr = joinStringList.poll() + " return" + retClauseStr;
        rewritternXQuery.append(joinStr);

        dependencyGraph = new DependencyGraph();

        return rewritternXQuery.toString();
    }


    @Override
    public String visitPath(XQueryOptParser.PathContext ctx) {
        StringBuilder result = new StringBuilder();

        if(dependencyGraph.isOptimizable){
            if(ctx.StringConstant()!=null){
                result.append(" doc(" + ctx.StringConstant().getText() + ") ");
            }else{
                // #################################################
                String varName = ctx.Variable().getText().substring(1);
                result.append( "$tuple/" + varName + "/*");
                // #################################################
            }

            // traverse all (sep NAME)
            for(int i=0;i<ctx.NAME().size();++i){
                result.append(ctx.sep(i).getText());
                result.append(ctx.NAME(i).getText());
            }

            // check if has an extra sep text() in the end
            if(ctx.sep().size() > ctx.NAME().size()){
                result.append(ctx.sep(ctx.sep().size()-1).getText() + "text()");
            }
            return result.toString();
        }

        return super.visitPath(ctx);
    }

    /**
     * (Variable|StringConstant) ('=' | 'eq') (Variable|StringConstant)       # CondEqual
     * @param ctx
     * @return String
     */
    @Override
    public String visitCondEqual(XQueryOptParser.CondEqualContext ctx) {
        String left, right;

        if(ctx.Variable().size()>=2){
            left = (ctx.Variable(0).getText());
            right = (ctx.Variable(1).getText());

            Node leftNode = dependencyGraph.findNodes(left);
            Node rightNode = dependencyGraph.findNodes(right);

            dependencyGraph.varEqualMap.putIfAbsent(leftNode,new HashSet<>());
            dependencyGraph.varEqualMap.get(leftNode).add(rightNode);

            dependencyGraph.varEqualMap.putIfAbsent(rightNode,new HashSet<>());
            dependencyGraph.varEqualMap.get(rightNode).add(leftNode);
        }
        else if(ctx.StringConstant().size()>=2){
            left = ctx.StringConstant(0).getText();
            right = (ctx.StringConstant(1).getText());

            dependencyGraph.freeRestrictList.add(" "+ left + " = " + right + " ");
        }
        else{
            String constValue = (ctx.StringConstant(0).getText());
            String varStr = (ctx.Variable(0).getText());

            Node node = dependencyGraph.findNodes(varStr);
            dependencyGraph.varRestrictMap.putIfAbsent(node,new LinkedList<>());
            dependencyGraph.varRestrictMap.get(node).add(constValue);
        }

        return super.visitCondEqual(ctx);
    }

}
