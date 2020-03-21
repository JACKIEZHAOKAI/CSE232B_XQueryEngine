import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CharStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.LinkedList;


/**
 * This class is the main calss to run the Antlr parser and optimized XQuery query engine using Join if possible
 */
// ################################################################
// MileStore 3  Join for XQuery
// ################################################################
public class AntlrParserMain {
    public static void main(String[] args) {

        String inputFilePath = args[0];
        if (args[1]==null){
            System.out.println("please provide a flag.");
        }
        String flag = args[1];
        boolean optimizeOpen = true;

        try {
            CharStream antlrStr = CharStreams.fromFileName(inputFilePath);

            // #########################################################################################
            // optimize the XQuery and store in antlrStr
            if(optimizeOpen){
//                System.out.println("part1: Output Rewritten query -- take as input a test query, detect join and then rewrite a query using join operator.");
                XQueryOptLexer xQueryLexer = new XQueryOptLexer(antlrStr);
                CommonTokenStream tokens = new CommonTokenStream(xQueryLexer);
                XQueryOptParser xQueryParser = new XQueryOptParser(tokens);
                ParseTree xPathTree = xQueryParser.xq();
                String optimizedQuery="";
                String optimizedQuery2="";


                if (flag.equals("-B")){
                    System.out.println("execute bushy join plan.");
                    XQueryRewriterVisitor2  bushyjoinVisitor = new XQueryRewriterVisitor2();      // run bushyJoin
                    optimizedQuery2 = bushyjoinVisitor.visit(xPathTree);                 // visit and output a rewritten string
                    System.out.println("############# optimized query #############");
                    System.out.println(optimizedQuery2);
                    antlrStr = CharStreams.fromString(optimizedQuery2);
                }
                else if (flag.equals("-L")){
                    System.out.println("execute left-deep join plan");
                    XQueryRewriterVisitor leftDeepJoinVisitor = new XQueryRewriterVisitor();      // run leftDeepJoin
                    optimizedQuery = leftDeepJoinVisitor.visit(xPathTree);                 // visit and output a rewritten string
                    System.out.println("############# optimized query #############");
                    System.out.println(optimizedQuery);
                    antlrStr = CharStreams.fromString(optimizedQuery);
                }
                else{
                    System.out.println("Error: invalid flag, please provide a valid flag.");
                }

                System.out.println("############################################");
            }

            // #########################################################################################
            // Evaluate the XQuery with the added Join operator
            System.out.println("part2: Evaluate rewritten query  -- take as input your rewritten query  (the one that you produced in the step 1 above)  and evaluate it.");
            System.out.println("built the XQueryTree Tree");
            XQueryLexer xQueryLexer = new XQueryLexer(antlrStr);
            CommonTokenStream tokens = new CommonTokenStream(xQueryLexer);
            XQueryParser xQueryParser = new XQueryParser(tokens);
            ParseTree xPathTree = xQueryParser.xq();
            XQueryOverriddenVisitor queryMyVisitor = new XQueryOverriddenVisitor();     // use XQueryOverriddenVisitor

            System.out.println("queried the XQueryTree obtain a resList");
            LinkedList<Node> resList =  queryMyVisitor.visit(xPathTree);                // vist and output a list of nodes

            try
            {
                System.out.println("generate an output file to hold result");
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();

                Element rootElement = doc.createElement("root");
                doc.appendChild(rootElement);

                // construct result from resList to rootElement
                for(Node node : resList){
                    node = doc.importNode(node,true);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) node.cloneNode(true);
                        rootElement.appendChild(elem);
                    }else if(node.getNodeType() == Node.ATTRIBUTE_NODE){
                        Element elem = doc.createElement("AttrResult");
                        elem.setAttribute(node.getNodeName(),node.toString());
                        rootElement.appendChild(elem);
                    }
                }

                // output the result file
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("result.xml"));
                transformer.transform(source, result);

                System.out.println("Result file saved!" + " as result.xml");

            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ################################################################
// MileStore 2  XQuery
// ################################################################
///**
// * This class is the main calss to run the Antlr parser and XQuery query engine
// * It takes an input XQuery file as its only argument and output a XML format result
// */
//public class AntlrParserMain
//{
//    public static void main(String[] args) {
//
//        try
//        {
//            //1 read data
//            System.out.println("read XQuery input file:"+ args[0]);
//            CharStream codePointCharStream = CharStreams.fromFileName(args[0]); //"input.txt"
//
//            //2 built the xQueryTree
//            System.out.println("built the XQueryTree");
//            XQueryLexer xQueryLexer = new XQueryLexer(codePointCharStream);
//            CommonTokenStream tokens = new CommonTokenStream(xQueryLexer);
//            XQueryParser xQueryParser = new XQueryParser(tokens);
//            ParseTree xQueryTree = xQueryParser.xq();
//
//            //3 query the XPathTree obtain a resList
//            XQueryOverriddenVisitor queryMyVisitor = new XQueryOverriddenVisitor();
//            System.out.println("queried the XQueryTree obtain a resList");
//            LinkedList<Node> resList = queryMyVisitor.visit(xQueryTree);
//
//            //4 generate an output file to hold result
//            System.out.println("generate an output file to hold result");
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            Document doc = docBuilder.newDocument();
//
//            Element rootElement = doc.createElement("root");
//            doc.appendChild(rootElement);
//
//            // 4.1 construct result from resList to rootElement
//            for(Node node : resList){
//                node = doc.importNode(node,true);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element elem = (Element) node.cloneNode(true);
//                    rootElement.appendChild(elem);
//                }else if(node.getNodeType() == Node.ATTRIBUTE_NODE){
//                    Element elem = doc.createElement("AttrResult");
//                    elem.setAttribute(node.getNodeName(),node.toString());
//                    rootElement.appendChild(elem);
//                }
//            }
//
//            // 4.2 output the result file
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//
//            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(new File("result.xml"));
//            transformer.transform(source, result);
//
//            System.out.println("Result file saved!" + " as result.xml");
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}



// ################################################################
// MileStore 1  XPath
// ################################################################
//import org.antlr.v4.runtime.*;
//import org.antlr.v4.runtime.tree.ParseTree;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.File;
//import java.util.LinkedList;
//
//
///**
// * This class is the main calss to run the Antlr parser and XPath query engine
// * It takes an input Xpath file as its only argument and output a XML format result
// */
//public class AntlrParserMain
//{
//    public static void main(String[] args) {
//
//        try
//        {
//            //1 read data
//            System.out.println("read XPath input file:"+ args[0]);
//            CharStream codePointCharStream = CharStreams.fromFileName(args[0]); //"input.txt"
//
//            //2 built the xPathTree
//            System.out.println("built the xPathTree");
//            XPathLexer xPathLexer = new XPathLexer(codePointCharStream);
//            System.out.println("queried the XPathTree obtain a resList");
//
//            CommonTokenStream tokens = new CommonTokenStream(xPathLexer);
//            System.out.println(tokens);
//            XPathParser xPathParser = new XPathParser(tokens);
//            XPathOverriddenVisitor xPathVisitor = new XPathOverriddenVisitor();
//
//            ParseTree xPathTree = xPathParser.ap();
//
//            //3 query the XPathTree obtain a resList
//            //Main implementation pf xPathVisitor under xPatOverriddenhVisitor
//            System.out.println("queried the XPathTree obtain a resList");
//            LinkedList<Node> resList = xPathVisitor.visit(xPathTree);
//
//            //4 generate an output file to hold result
//            System.out.println("generate an output file to hold result");
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            Document doc = docBuilder.newDocument();
//
//            Element rootElement = doc.createElement("root");
//            doc.appendChild(rootElement);
//
//            // 4.1 construct result from resList to rootElement
//            for(Node node : resList){
//                node = doc.importNode(node,true);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element elem = (Element) node.cloneNode(true);
//                    rootElement.appendChild(elem);
//                }else if(node.getNodeType() == Node.ATTRIBUTE_NODE){
//                    Element elem = doc.createElement("AttrResult");
//                    elem.setAttribute(node.getNodeName(),node.toString());
//                    rootElement.appendChild(elem);
//                }
//            }
//
//            // 4.2 output the result file
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//
//            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(new File("result.xml"));
//            transformer.transform(source, result);
//
//            System.out.println("Result file saved!" + " as result.xml");
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}