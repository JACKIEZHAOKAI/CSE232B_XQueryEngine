import java.io.File;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class XQueryOverriddenVisitor extends XQueryBaseVisitor<LinkedList<Node>> {

    private LinkedList<Node> cur;

    private HashMap<String, LinkedList<Node>> vars;

    Document doc;   //Java dom tree object

    /**
     *  constructor
     */
    public XQueryOverriddenVisitor() {
        this.cur = new LinkedList<>();
        this.vars = new HashMap<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        this.doc = builder.newDocument();
    }


/*  #################################################################
    This part is the Override of XQueryBaseVisitor functions
    #################################################################
*/
    //##########################################################################
    //  XQuery basic
    //        XQ ! V ar | StringConstant | ap
    //        | (XQ1) | XQ1,XQ2 | XQ1/rp| XQ1//rp
    //        | htagNamei{XQ1}h/tagNamei
    //        | forClause letClause whereClause returnClause
    //        | letClause XQ1
    //##########################################################################
    @Override
    public LinkedList<Node> visitXqVariable(XQueryParser.XqVariableContext ctx) {
        if (vars.containsKey(ctx.Variable().getText())) {
            this.cur = vars.get(ctx.Variable().getText());
            return this.cur;
        }
        return new LinkedList<>();
    }

    /*  Helper function:
        makeText(s)
            takes as argument a string constant s and returns an XML text node with value s.*/
    public Node makeText(String s) {
        return doc.createTextNode(s.substring(1, s.length() - 1));
    }

    @Override
    public LinkedList<Node> visitXqConstant(XQueryParser.XqConstantContext ctx) {
        LinkedList<Node> resList = new LinkedList<>();
        String stringConst = ctx.StringConstant().getText();

        Node res = makeText(stringConst);

        resList.add(res);
        this.cur = resList;
        return resList;
    }

    @Override
    public LinkedList<Node> visitXqAp(XQueryParser.XqApContext ctx) {
        return visit(ctx.ap());
    }

    @Override
    public LinkedList<Node> visitXqwithP(XQueryParser.XqwithPContext ctx) {
        return visit(ctx.xq());
    }

    @Override
    public LinkedList<Node> visitXqRp(XQueryParser.XqRpContext ctx) {
        visit(ctx.xq());    //visit
        this.cur = unique(visit(ctx.rp())); //visit
        return this.cur;
    }

    @Override
    public LinkedList<Node> visitXqAll(XQueryParser.XqAllContext ctx) {
        LinkedList<Node> currList = visit(ctx.xq());    //visit
        this.cur = getReachable(currList);
        this.cur = unique(visit(ctx.rp()));     //visit
        return this.cur;
    }


    @Override
    public LinkedList<Node> visitXqTwoXq(XQueryParser.XqTwoXqContext ctx) {
        LinkedList<Node> copyList = new LinkedList<>(this.cur);
        LinkedList<Node> res1, res2;

        res1 = visit(ctx.xq(0));    //visit
        this.cur = copyList;

        res2 = visit(ctx.xq(1));    //visit

        LinkedList<Node> currList = new LinkedList<>(res1);
        currList.addAll(res2);
        this.cur = currList;

        return currList;
    }

    /*  Helper Function:
        makeElem(t, l)
            takes as arguments a tag name t and a (potentially empty) list of XML nodes l
            returns a new XML element node n with tag(n) = t and
            children(n) a list of copies of the nodes in l
    */
    public Node makeElement(String tagName, LinkedList<Node> l) {
        Element elem = doc.createElement(tagName);
        for (Node n : l) {
            Node c = doc.importNode(n, true);
            if (c.getNodeType() == Node.ATTRIBUTE_NODE) {
                elem.setAttributeNode((Attr) c);
            } else {
                elem.appendChild(c);
            }
        }
        return elem;
    }

    @Override
    public LinkedList<Node> visitXqTag(XQueryParser.XqTagContext ctx) {
        String tagName = ctx.NAME(0).getText();
        LinkedList<Node> nodeList = visit(ctx.xq());    //visit
        LinkedList<Node> resList = new LinkedList<>();

        Node res = makeElement(tagName, nodeList);

        resList.add(res);
        this.cur = resList;
        return resList;
    }

    /**
     * change the variables, and does not change the context
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitXqLet(XQueryParser.XqLetContext ctx) {
        HashMap<String, LinkedList<Node>> copyVars = new HashMap<>(this.vars);
        visit(ctx.letClause()); //visit
        visit(ctx.xq());        //visit
        this.vars = copyVars;
        return this.cur;
    }

    //##########################################################################
    // Let For Where Return Clause
    //    forClause ! for V ar1 in XQ1, V ar2 in XQ2, . . . ,V arn in XQn
    //    letClause ! ✏ | let V arn+1 := XQn+1, . . . ,V arn+k := XQn+k
    //    whereClause ! ✏ | where Cond
    //    returnClause ! return XQ1
    //##########################################################################
    /**
     * helper function to parse FLWR
     *  forClause letClause? whereClause? returnClause
     *  there may be nested for clause, and then followed by let/where/return
     *  there must be a return clause but let and where is optional
     *
     *  EX  for v1 in XQ1, v2 in XQ2 where v1==v2 return XQ3
     *  lets say    v1 refers to a list of three nodes node1 node2 and node3,
     *              v2 refers to a list of two nodes node4 and node5
     *
     *          /         |           \
     *       node1      node2       node3           1st recursive call
     *        /  \       /  \        /  \
     *     node4 node5 node4 node5 node4 node5      2nd recursive call
     *
     *   Like a Join op, nested for will check if node1 = node4 and then node1 = node5
     *
     */
    public void parseFLWR(XQueryParser.XqFLWRContext ctx, int depth, LinkedList<Node> nodes) {

        // parse the nested for clause
        if (depth < ctx.forClause().Variable().size()) {
            /*  String getText()
                Return the combined text of all leaf nodes.  */
            String varName = ctx.forClause().Variable(depth).getText();
            LinkedList<Node> xqueryResult = visit(ctx.forClause().xq(depth));   //visit

            for (Node node : xqueryResult) {
                // For successively binds each item from the input sequence, which is a node at a time
                //  Since we maintain a HashMap<String, LinkedList<Node>> because of the LetClause
                //  we will use a resList to hold the node
                LinkedList<Node> resList = new LinkedList<>();
                resList.add(node);

                // if varName already existed in the map vars, we need to update it with new value
                if (this.vars.containsKey(varName)){
                    this.vars.remove(varName);
                    this.vars.put(varName, resList);
                }
                else{
                    this.vars.put(varName, resList);
                }

                // recursive call to parse the nested for clause
                parseFLWR(ctx, depth + 1, nodes);
            }
            return;
        }

        // No more for clause, parse let/ where/ return clause
        HashMap<String, LinkedList<Node>> copyVars = new HashMap<>(this.vars);

        if (ctx.letClause() != null) {
            visit(ctx.letClause());     //visit
        }

        if (ctx.whereClause() == null || visit(ctx.whereClause()).size() > 0) {
            nodes.addAll(visit(ctx.returnClause()));    //visit
        }
        this.vars = copyVars;
        return;
    }

    /**
     * Ilterate all let clauses and values in each let clause
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitXqFLWR(XQueryParser.XqFLWRContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        HashMap<String, LinkedList<Node>> copyVars = new HashMap<>(this.vars);

        // forClause letClause? whereClause? returnClause
        parseFLWR(ctx, 0, res);     // helper function to parse FLWR

        this.cur = res;
        this.vars = copyVars;
        return res;
    }

    @Override
    public LinkedList<Node> visitForClause(XQueryParser.ForClauseContext ctx) {
        return null;
    }

    @Override
    public LinkedList<Node> visitLetClause(XQueryParser.LetClauseContext ctx) {
        LinkedList<Node> res;
        for (int i = 0; i < ctx.Variable().size(); i++) {
            HashMap<String, LinkedList<Node>> copyVars = new HashMap<>(this.vars);

            //  Let binds the whole input sequence, which is a list of nodes at a time
            res = visit(ctx.xq(i));     //visit
            this.vars = copyVars;
            this.vars.put(ctx.Variable(i).getText(), res);
        }
        return null;
    }

    @Override
    public LinkedList<Node> visitWhereClause(XQueryParser.WhereClauseContext ctx) {
        return visit(ctx.cond());
    }

    @Override
    public LinkedList<Node> visitReturnClause(XQueryParser.ReturnClauseContext ctx) {
        return visit(ctx.xq());
    }

    //##########################################################################
    // Conditon functions
    //  Cond ! XQ1 = XQ2 | XQ1 eq XQ2
    //  | XQ1 == XQ2 | XQ1 is XQ2
    //  | empty(XQ1)
    //  | some V ar1 in XQ1, . . . ,V arn in XQn satisfies Cond
    //  | (Cond1) | Cond1 and Cond2 | Cond1 or Cond2 | not Cond1
    //##########################################################################
    /**
     * : xq ('=' | 'eq') xq    #CondValueEqual
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondValueEqual(XQueryParser.CondValueEqualContext ctx) {
        // use copyCur to store this.cur and reassign after the visit()
        LinkedList<Node> copyCur = new LinkedList<>(this.cur);
        LinkedList<Node> left, right;

        left= visit(ctx.xq(0));         //visit
        this.cur = copyCur;

        right = visit(ctx.xq(1));       //visit
        this.cur = copyCur;

        if (haveEqualNodes(left, right)) {
            return this.cur;
        }
        return new LinkedList<>();
    }

    /**
     * | xq ('==' | 'is') xq   #CondIdentityEqual
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondIdentityEqual(XQueryParser.CondIdentityEqualContext ctx) {
        LinkedList<Node> copyCur = new LinkedList<>(this.cur);
        LinkedList<Node> left, right;

        left = visit(ctx.xq(0));        //visit
        this.cur = copyCur;
        right = visit(ctx.xq(1));       //visit
        this.cur = copyCur;

        if (haveSameNodes(left, right)) {
            return this.cur;
        }
        return new LinkedList<>();
    }

    /**
     * 'empty' '(' xq ')'   # CondEmpty
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondEmpty(XQueryParser.CondEmptyContext ctx) {
        LinkedList<Node> copyCur = new LinkedList<>(this.cur);
        LinkedList<Node> targetNodes = visit(ctx.xq());     //visit
        this.cur = copyCur;
        if (targetNodes.isEmpty()) {
            return this.cur;
        }
        return new LinkedList<>();
    }

    /**
     * 'some' Variable 'in' xq (',' Variable 'in' xq)* 'satisfies' cond         # CondSome
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondSome(XQueryParser.CondSomeContext ctx) {
        LinkedList<Node> copyCur = new LinkedList<>(this.cur);
        HashMap<String, LinkedList<Node>> copyVars = new HashMap<>(this.vars);
        LinkedList<Node> xqRes, condRes;

        for (int i = 0; i < ctx.Variable().size(); i++) {
            xqRes = visit(ctx.xq(i));   //visit
            this.vars.put( ctx.Variable(i).getText(), xqRes);   //store var text to nodes
        }

        condRes = visit(ctx.cond());    //visit
        this.vars = copyVars;
        this.cur = copyCur;

        return condRes;
    }

    /**
     * '(' cond ')'   #CondWithP
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondWithP(XQueryParser.CondWithPContext ctx) {
        return visit(ctx.cond());
    }

    /**
     * cond 'and' cond     #CondAnd
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondAnd(XQueryParser.CondAndContext ctx) {
        if (visit(ctx.cond(0)).isEmpty() || visit(ctx.cond(1)).isEmpty()) {
            return new LinkedList<>();
        }
        return this.cur;
    }

    /**
     * cond 'or' cond
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondOr(XQueryParser.CondOrContext ctx) {
        if (visit(ctx.cond(0)).isEmpty() && visit(ctx.cond(1)).isEmpty()) {
            return new LinkedList<>();
        }
        return this.cur;
    }

    /**
     * 'not' cond
     *
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitCondNot(XQueryParser.CondNotContext ctx) {
        if (visit(ctx.cond()).isEmpty()) {
            return this.cur;
        }
        return new LinkedList<>();
    }

    /**
     * Helper function to generate the hashkey for the node
     * */
    String buildKey(Node node, LinkedList<String> tList){

        String key = "";
        for(String context: tList){
            for(Node child: getChildren(node)){
                if(child.getNodeName().equals(context)){
                    key += child.getFirstChild().getTextContent();
                }
            }
        }
        return key;
    }

    /** ########################################################################################################
     * MS 3, an extension of XQuery evaluation engine that supports input queries with the join keyword.
     * The join is implemented using a hash join algorithm as discussed in class.
     *
     *
     * The "hash join" algorithm consists of two steps:
     *
     * 1    Hash phase: Create a multimap from one of the two tables, mapping from each join column value to all the rows that contain it.
     * The multimap must support hash-based lookup which scales better than a simple linear search, because that's the whole point of this algorithm.
     * Ideally we should create the multimap for the smaller table, thus minimizing its creation time and memory size.
     * 2    Join phase: Scan the other table, and find matching rows by looking in the multimap created before.
     *
     * In pseudo-code, the algorithm could be expressed as follows:
     *
     * let A = the first input table (or ideally, the larger one)
     * let B = the second input table (or ideally, the smaller one)
     * let jA = the join column ID of table A
     * let jB = the join column ID of table B
     * let MB = a multimap for mapping from single values to multiple rows of table B (starts out empty)
     * let C = the output table (starts out empty)
     *
     * for each row b in table B:
     *    place b in multimap MB under key b(jB)
     *
     * for each row a in table A:
     *    for each row b in multimap MB under key a(jA):
     *       let c = the concatenation of row a and row b
     *       place row c in table C
     * ########################################################################################################
     */
    /**
     * 'join' '(' xq ',' xq ',' tList ',' tList ')'                 # XqJoin
     * join to list of XQuery nodes if can join
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitXqJoin(XQueryParser.XqJoinContext ctx) {

        // join(for A in ... $ta in ...,
        //      for B in ... $tb in ...,
        //      [tA] ,   [tB]    )
        //
        // check if tA == tB,  { tA -> [A, B]   }

        // create two tList
        LinkedList<String> tA = new LinkedList<>();
        LinkedList<String> tB = new LinkedList<>();
        for(int i = 0; i<ctx.tList(0).NAME().size(); i++){
            tA.add(ctx.tList(0).NAME(i).getText());
            tB.add(ctx.tList(1).NAME(i).getText());
        }

        // visit the two XQuery to obtain list of nodes result
        LinkedList<Node> A, B;
        LinkedList<Node> copyCur = new LinkedList<>(this.cur);
        A = visit(ctx.xq(0));      // visit
        this.cur = copyCur;
        B = visit(ctx.xq(1));     // visit


        LinkedList<Node> res = new LinkedList<>();
        HashMap<String, LinkedList<Node>> XqMap = new HashMap<>();     //map jointKey to xQuery result
        String B_key, A_key;

        // 1 add B into the hashmap
        for (Node b : B){
            B_key = buildKey(b, tB);

            if(!XqMap.containsKey(B_key)){
                XqMap.put(B_key, new LinkedList<>());
            }
            XqMap.get(B_key).add(b);
        }

        // 2 traverse A and check if has any same key with B, then can join a to b
        for (Node a : A){
            A_key = buildKey(a, tA);

            if(XqMap.containsKey(A_key)){   // join the two entries

                LinkedList<Node> bList = XqMap.get(A_key);
                for (Node b : bList) {
                    // concatenate a and b
                    LinkedList<Node> concat = new LinkedList<>();
                    concat.addAll(getChildren(a));
                    concat.addAll(getChildren(b));

                    String a_tag = a.getNodeName();
                    res.add(makeElement(a_tag, concat));
                }
            }
        }
        this.cur = res;
        return res;
    }

/*  #################################################################
    #################################################################
    The following part is just copies of the xpath function
    #################################################################
 */
    /**
     * helper function for traversal
     * get all the children of node n
     *
     * @param: A parent Node
     * @return a List of the children
     */
    public static LinkedList<Node> getChildren(Node n){
        LinkedList<Node> children = new LinkedList<>();
        NodeList nodeList = n.getChildNodes();
        for(int i=0; i< nodeList.getLength(); i++){
            children.add(nodeList.item(i));
        }
        return children;
    }

    /**
     * helper function for visitTxt()
     * get all text children of node n
     *
     * @param: A Node
     * @return children text list nodes
     */
    public static LinkedList<Node>  getTxt(Node n) {
        LinkedList<Node> res = new LinkedList<>();
        LinkedList<Node> children = getChildren(n);
        for (Node c : children) {
            if ((c.getNodeType() == Node.TEXT_NODE) && (c.getTextContent() != null) && (!c.getTextContent().isEmpty())) {
                res.add(c);
            }
        }
        return res;
    }

    /**
     * helper function for visitAttribute()
     * get all attribute nodes of a given node with its attribute name
     *
     * @param: a Node and attributeName
     * @return element text list nodes
     */
    public  static LinkedList<Node> getAttribute(Node n, String attributeName) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (n.getNodeType() != Node.ELEMENT_NODE) {
            return nodes;
        }
        Element e = (Element) n;
        if (!e.hasAttribute(attributeName)) {
            return nodes;
        }
        nodes.add(e.getAttributeNode(attributeName));
        return nodes;
    }

    /**
     * helper function for visitRpAll() and visitApAll()
     * run BFS breath first search to traverse all reachable nodes in the XML tree
     *
     * @param nodes
     * @return List of nodes
     */
    public static LinkedList<Node>  getReachable(LinkedList<Node> nodes) {
        LinkedList<Node> res = new LinkedList<>();
        LinkedList<Node> queue = new LinkedList<>();    //BFS queue
        queue.addAll(nodes);
        res.addAll(nodes);
        while(!queue.isEmpty()){
            Node n = queue.get(0);
            queue.remove(0);
            LinkedList<Node> children = getChildren(n);
            queue.addAll(children);
            res.addAll(children);
        }
        return res;
    }

    /**
     * extract unique nodes from a list of node
     *
     * @param nodes List of nodes with possible duplicates
     * @return List of nodes without duplicates
     */
    public static LinkedList<Node> unique(List<Node> nodes) {
        //want to preserve order
        LinkedList<Node> uniqueNodes = new LinkedList<>();
        for (Node n : nodes) {
            if (!uniqueNodes.contains(n)) {
                uniqueNodes.add(n);
            }
        }
        return uniqueNodes;
    }

    /**
     * Returns the parent of a node
     *
     * @param n Node
     * @return Singleton list containing the parent of the element node, if it has a parent
     * - an empty list otherwise
     */
    public static LinkedList<Node> getParent(Node n) {
        LinkedList<Node> nodes = new LinkedList<>();
        Node parent;
        // check if its attribute node
        if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
            parent = ((Attr) n).getOwnerElement();
        }
        else{
            parent = n.getParentNode();
        }
        if (parent != null) {
            nodes.add(parent);
        }
        return nodes;
    }

    /**
     * Checks any node in the left list is equal to the one in the right list
     *
     * @param ls First list of nodes
     * @param rs Second list of nodes
     * @return ∃ l ∈ ls ∃ r ∈ rs / l eq r
     */
    public static boolean haveEqualNodes(LinkedList<Node> ls, LinkedList<Node> rs) {
        for (Node l : ls) {
            for (Node r : rs) {
                if (l.isEqualNode(r)) {     //check if isEqualNode
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if two list contains the same node
     *
     * @param ls First list of nodes
     * @param rs Second list of nodes
     * @return ∃ l ∈ ls ∃ r ∈ rs / l is r
     */
    public static boolean haveSameNodes(LinkedList<Node> ls, LinkedList<Node> rs) {
        for (Node l : ls) {
            for (Node r : rs) {
                if (l.isSameNode(r)) {      // check if isSameNode
                    return true;
                }
            }
        }
        return false;
    }

    //###################################################################
    // read XML file
    //###################################################################
    public static  LinkedList<Node> readXMLFile(String fileName) {

        fileName = fileName.substring(1, fileName.length() - 1);    // Remove quotes "..."
        System.out.println("reading XML file:"+fileName);
        File xmlFile = new File(fileName);
        LinkedList<Node> nodes = new LinkedList<>();
        try{
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);

            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse the input file to get a Document object
            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();   //Returns the root element of the documen
            nodes.add(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }

    //###################################################################
    // override of absolute path functions
    //###################################################################
    /**
     * 'doc' '(' FPath ')'     #ApDoc
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitApDoc(XQueryParser.ApDocContext ctx) {
        System.out.println("call visitApDoc");
        cur = readXMLFile(ctx.StringConstant().getText());
        return cur;
    }

    /**
     * doc '/' rp
     * doc '//' rp            #ApPath
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @param ctx
     * @return a list of nodes
     */

    @Override
    public LinkedList<Node> visitApPath(XQueryParser.ApPathContext ctx){
        System.out.println("call visitApPath");
        this.visit(ctx.doc());
        cur = getReachable(cur);
        cur = unique(visit(ctx.rp()));
        return cur;
    }

    //###################################################################
    // override of relative path functions
    //###################################################################
    /**
     * NAME                          # TagName
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitTagName(XQueryParser.TagNameContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        String leafName = ctx.getText();
        for(Node n: this.cur){
            LinkedList<Node> c = getChildren(n);
            for(Node nn:c){
                if((nn.getNodeName()).equals(leafName))
                    res.add(nn);
            }
        }
        this.cur = res;
        return res;
    }

    /**
     * '.'                          # Current
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitCurrent(XQueryParser.CurrentContext ctx) {
        return this.cur;
    }

    /**
     * '..'                         # Parent
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitParent(XQueryParser.ParentContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        for(Node n : this.cur){
            res.addAll(getParent(n));
        }
        cur = res;
        return res;
    }

    /**
     * '*'                          # AllChildren
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitAllChildren(XQueryParser.AllChildrenContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        for(Node n : this.cur){
            res.addAll(getChildren(n));
        }
        this.cur = res;
        return res;
    }

    /**
     *  'text()'                     # Txt
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitTxt(XQueryParser.TxtContext ctx) {
        LinkedList<Node> res = new LinkedList<Node>();
        for(Node n:cur){
            res.addAll(getTxt(n));
        }
        cur = res;
        return res;
    }

    /**
     *  '@' NAME                     # Attribute
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitAttribute(XQueryParser.AttributeContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        //get attribute name
        String leafName = ctx.getText();
        for(Node n:cur){
            res.addAll(getAttribute(n,leafName));
        }
        cur = res;
        return res;
    }

    /**
     * '(' rp ')'                   # RpwithP
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node>  visitRpwithP(XQueryParser.RpwithPContext ctx) {
        return visit(ctx.rp());
    }

    /**
     * rp '/' rp                    # RpChildren
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node>  visitRpChildren(XQueryParser.RpChildrenContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        visit(ctx.rp(0));
        res = visit(ctx.rp(1));
        this.cur = this.unique(res);
        return this.cur;
    }

    /**
     * rp '//' rp                   # RpAll
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public  LinkedList<Node>  visitRpAll(XQueryParser.RpAllContext ctx){
        visit(ctx.rp(0));
        LinkedList<Node> temp = getReachable(cur);
        cur = temp;
        LinkedList<Node> res = visit(ctx.rp(1));
        cur = this.unique(res);
        return res;
    }

    /**
     * rp '[' filter ']'            # RpFilter
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node>  visitRpFilter(XQueryParser.RpFilterContext ctx) {
        LinkedList<Node> preNodes = visit(ctx.rp());
        LinkedList<Node> res = new LinkedList<Node>();
        for(Node n: preNodes){
            LinkedList<Node> tempNodes = new LinkedList<Node>();
            tempNodes.add(n);
            this.cur = tempNodes;
            if( !visit(ctx.filter()).isEmpty() )
                res.add(n);
        }
        this.cur = res;
        return res;
    }

    /**
     * rp ',' rp                    # TwoRp
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node>  visitTwoRp(XQueryParser.TwoRpContext ctx) {
        LinkedList<Node> copycur = new LinkedList<>(cur);
        LinkedList<Node> res1 = new LinkedList<>();
        LinkedList<Node> res2 = new LinkedList<>();
        res1 = visit(ctx.rp(0));
        this.cur  = copycur;
        res2 = visit(ctx.rp(1));
        LinkedList<Node> res = new LinkedList<Node>(res1);
        res.addAll(res2);
        this.cur = res;
        return res;
    }

    //###################################################################
    // override of filter functions
    // filter functions should not change the current list of nodes
    //###################################################################
    /**
     * rp                           # FltRp
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltRp(XQueryParser.FltRpContext ctx) {
        LinkedList<Node> copy = this.cur;
        LinkedList<Node> filterNodes = visit(ctx.rp());
        this.cur = copy;
        return filterNodes;
    }

    /**
     * rp '=' rp                    # FltEqual
     * rp 'eq' rp                   # FltEqual
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltEqual(XQueryParser.FltEqualContext ctx) {
        LinkedList<Node> nodes = this.cur;
        LinkedList<Node> l = visit(ctx.rp(0));
        this.cur = nodes;
        LinkedList<Node> r = visit(ctx.rp(1));
        this.cur = nodes;
        if (haveEqualNodes(l, r)) {
            return this.cur;
        }
        return new LinkedList<>();
    }

    /**
     * rp '==' rp                   # FltIs
     * rp 'is' rp
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltIs(XQueryParser.FltIsContext ctx) {
        LinkedList<Node> copy = this.cur;
        LinkedList<Node> l = visit(ctx.rp(0));
        this.cur = copy;
        LinkedList<Node> r = visit(ctx.rp(1));
        this.cur = copy;
        if (haveSameNodes(l, r)) {
            return this.cur;
        }
        return new LinkedList<>();
    }

    /**
     * '(' filter ')'               # FltwithP
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node>  visitFltwithP(XQueryParser.FltwithPContext ctx){
        return visit(ctx.filter());
    }

    /**
     * 'and' filter                 # FltAnd
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltAnd(XQueryParser.FltAndContext ctx) {
        if ((visit(ctx.filter(0)).isEmpty()) || (visit(ctx.filter(1)).isEmpty())) {
            return new LinkedList<>();
        }
        return this.cur;
    }

    /**
     * 'or' filter                  # FltOr
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltOr(XQueryParser.FltOrContext ctx) {
        if ((visit(ctx.filter(0)).isEmpty()) && (visit(ctx.filter(1)).isEmpty())) {
            return new LinkedList<>();
        }
        return this.cur;
    }

    /**
     * 'not' filter                 # FltNot
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltNot(XQueryParser.FltNotContext ctx) {
        if (visit(ctx.filter()).isEmpty()) {
            return this.cur;
        }
        return new LinkedList<>();
    }

}
