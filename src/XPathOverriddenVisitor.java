import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

/**
 * This class overrides the XPathBaseVisitor
 * It also includes several helper methods such as
 * readXMLFile(), getChildren(), getTxt(), getAttribute(), getDescendent(), unique()
 * getParent(), haveEqualNodes(), haveSameNodes()
 */
public class XPathOverriddenVisitor extends XPathBaseVisitor<LinkedList<Node>> {

    private LinkedList<Node> cur;

    public XPathOverriddenVisitor(){
        this.cur = new LinkedList<>();
    }

    //###########################################################################
    // Helper functions
    //###########################################################################
    /**
     * read an XML file with Java XML Parser support
     *
     * @param: file String
     * @return a LinkedList of the Node
     */
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

    // a node is either a text Node or an element Node
    // a text node may be associated to an element node
    /**
     * helper function for visitTxt()
     * get all text children of node n
     *
     * @param: A Node
     * @return children text list nodes
     */
    public static LinkedList<Node>  getTxt(Node n) {
        System.out.println("call getTxt");
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
        System.out.println("call getAttribute");
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
        System.out.println("call getDescendent");
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
        System.out.println("call unique");
        //want to preserve order so do not use a hashset!
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
        System.out.println("call haveEqualNodes");
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
        System.out.println("call haveSameNodes");
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
    // override of absolute path functions
    //###################################################################
    /**
     * 'doc' '(' FPath ')'     #ApDoc
     * @param ctx
     * @return
     */
    @Override
    public LinkedList<Node> visitApDoc(XPathParser.ApDocContext ctx) {
        System.out.println("call visitApDoc");
        cur = readXMLFile(ctx.FPath().getText());
        return cur;
    }

    /**
     * doc '/' rp
     * doc '//' rp            #ApPath
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>getDescendent
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitApPath(XPathParser.ApPathContext ctx) {
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
    public LinkedList<Node> visitTagName(XPathParser.TagNameContext ctx) {
        System.out.println("call visitTagName");
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
    public LinkedList<Node> visitCurrent(XPathParser.CurrentContext ctx) {
        System.out.println("call visitCurrent");
        return this.cur;
    }

    /**
     * '..'                         # Parent
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitParent(XPathParser.ParentContext ctx) {
        System.out.println("call visitParent");
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
    public LinkedList<Node> visitAllChildren(XPathParser.AllChildrenContext ctx) {
        System.out.println("call visitAllChildren");
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
    public LinkedList<Node> visitTxt(XPathParser.TxtContext ctx) {
        System.out.println("call visitTxt");
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
    public LinkedList<Node> visitAttribute(XPathParser.AttributeContext ctx) {
        System.out.println("call visitAttribute");
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
    public LinkedList<Node>  visitRpwithP(XPathParser.RpwithPContext ctx) {
        System.out.println("call visitRpwithP");
        return visit(ctx.rp());
    }

    /**
     * rp '/' rp                    # RpChildren
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node>  visitRpChildren(XPathParser.RpChildrenContext ctx) {
        System.out.println("call visitRpChildren");
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
    public  LinkedList<Node> visitRpDescendent(XPathParser.RpDescendentContext ctx) {
        System.out.println("call visitRpDescendent");
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
    public LinkedList<Node>  visitRpFilter(XPathParser.RpFilterContext ctx) {
        System.out.println("call visitRpFilter");
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
    public LinkedList<Node>  visitTwoRp(XPathParser.TwoRpContext ctx) {
        System.out.println("call visitTwoRp");
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
    public LinkedList<Node> visitFltRp(XPathParser.FltRpContext ctx) {
        System.out.println("call visitFltRp");
        LinkedList<Node> curCopy = this.cur;
        LinkedList<Node> filterNodes = visit(ctx.rp());
        this.cur = curCopy;
        return filterNodes;
    }

    /**
     * rp '=' rp                    # FltEqual
     * rp 'eq' rp                   # FltEqual
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltEqual(XPathParser.FltEqualContext ctx) {
        System.out.println("call visitFltEqual");
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
    public LinkedList<Node> visitFltIs(XPathParser.FltIsContext ctx) {
        System.out.println("call visitFltIs");
        LinkedList<Node> curCopy = this.cur;
        LinkedList<Node> l = visit(ctx.rp(0));
        this.cur = curCopy;
        LinkedList<Node> r = visit(ctx.rp(1));
        this.cur = curCopy;
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
    public LinkedList<Node>  visitFltwithP(XPathParser.FltwithPContext ctx){
        System.out.println("call visitFltwithP");
        return visit(ctx.filter());
    }

    /**
     * 'and' filter                 # FltAnd
     * @param ctx
     * @return a list of nodes
     */
    @Override
    public LinkedList<Node> visitFltAnd(XPathParser.FltAndContext ctx) {
        System.out.println("call visitFltAnd");
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
    public LinkedList<Node> visitFltOr(XPathParser.FltOrContext ctx) {
        System.out.println("call visitFltOr");
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
    public LinkedList<Node> visitFltNot(XPathParser.FltNotContext ctx) {
        System.out.println("call visitFltNot");
        if (visit(ctx.filter()).isEmpty()) {
            return this.cur;
        }
        return new LinkedList<>();
    }
}