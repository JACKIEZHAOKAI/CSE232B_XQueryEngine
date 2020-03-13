import java.util.*;

/**
 * Base string visitor, convert the xquery to string
 */

public class XQueryOptOverriddenVisitor extends XQueryOptBaseVisitor<String> {

    // ##############################################################
    // XqFWR
    // ##############################################################
//    /**
//     * 'for' Variable 'in' path (',' Variable 'in' path)* 'where' cond 'return' returnClause      #XqFWR
//     * @param ctx
//     * @return String
//     */
//    @Override
//    public String visitXqFWR(XQueryOptParser.XqFWRContext ctx) {
//
//        StringBuilder result = new StringBuilder();
//
//        // traverse all variables in the nested for loop
//        result.append(" for ");
//        for(int i=0; i<ctx.Variable().size(); i++){
//            result.append(ctx.Variable(i).getText());
//            result.append(" in ");
//            result.append(visit(ctx.path(i)));
//            // check if not reaching the end, append ','
//            if (i != (ctx.Variable().size() - 1)) {
//                result.append(" , ");
//            }
//        }
//
//        // where
//        if(ctx.cond()!= null){
//            result.append(" where ");
//            result.append(visit(ctx.cond()));
//        }
//
//        // return
//        result.append(" return ");
//        result.append(visit(ctx.returnClause()));   //visit
//
//        return result.toString();
//    }

    // ##############################################################
    // path
    // ##############################################################
    /**
     * ('doc' '(' StringConstant ')' | Variable)   (sep NAME)*
     * | ('doc' '(' StringConstant ')' | Variable) (sep NAME)*  sep 'text()'    #Path
     * @param ctx
     * @return  String
     */
    @Override
    public String visitPath(XQueryOptParser.PathContext ctx) {

        StringBuilder result = new StringBuilder();

        if(ctx.StringConstant()!=null){
            result.append(" doc(" + ctx.StringConstant().getText() + ") ");
        }else{
            result.append(ctx.Variable().getText());
        }

        // traverse all (sep NAME)
        for(int i=0; i<ctx.NAME().size(); i++){
            result.append(ctx.sep(i).getText());
            result.append(ctx.NAME(i).getText());
        }

        // check if has an extra sep text() in the end
        if(ctx.sep().size() > ctx.NAME().size()){
            String lastSep = ctx.sep(ctx.sep().size()-1).getText();
            result.append( lastSep + "text()");
        }

        return result.toString();
    }

    // ##############################################################
    // sep
    // ##############################################################
    @Override
    public String visitSep(XQueryOptParser.SepContext ctx) {
        return super.visitSep(ctx);
    }

    // ##############################################################
    // returnClause
    // ##############################################################
    /**
     * Variable      #XqreturnVar
     * @param ctx
     * @return String
     */
    @Override
    public String visitXqreturnVar(XQueryOptParser.XqreturnVarContext ctx) {
        return "$tuple/" + ctx.Variable().getText().substring(1) + "/*";
    }

    /**
     * returnClause  ',' returnClause         #XqTworeturn
     * @param ctx
     * @return
     */
    @Override
    public String visitXqTworeturn(XQueryOptParser.XqTworeturnContext ctx) {
        String first = visit(ctx.returnClause(0));
        String second = visit(ctx.returnClause(1));
        return first+" , "+second;
    }

    /**
     * '<' NAME '>' '{'returnClause'}' '</' NAME '>'   #XqTagreturn
     * @param ctx
     * @return String
     */
    @Override
    public String visitXqTagreturn(XQueryOptParser.XqTagreturnContext ctx) {
        String tag = ctx.NAME().get(0).getText();
        String returnClause = visit(ctx.returnClause());
        return " <"+ tag + ">" + "{ "+returnClause +" }"+"</" +tag+"> ";
    }

    /**
     *     | path         #Xqreturnpath
     * @param ctx
     * @return String
     */
    @Override
    public String visitXqreturnpath(XQueryOptParser.XqreturnpathContext ctx) {
        return visit(ctx.path());
    }

    // ##############################################################
    // cond
    // ##############################################################
    @Override
    public String visitCondEqual(XQueryOptParser.CondEqualContext ctx) {
        String left, right;

        if(ctx.Variable().size()>=2){
            left = (ctx.Variable(0).getText());
            right = (ctx.Variable(1).getText());
        }
        else if(ctx.StringConstant().size()>=2){
            left = ctx.StringConstant(0).getText();
            right = (ctx.StringConstant(1).getText());
        }
        else{
            // base case:  StringConstant = Variable
            left = (ctx.StringConstant(0).getText());
            right = (ctx.Variable(0).getText());
        }
        return left+" = "+ right;
    }

    /**
     * | cond 'and' cond            # CondAnd
     * @param ctx
     * @return String
     */
    @Override
    public String visitCondAnd(XQueryOptParser.CondAndContext ctx) {
        String cond1 = visit(ctx.cond(0));
        String cond2 = visit(ctx.cond(1));
        return cond1+" and "+cond2;
    }
}
