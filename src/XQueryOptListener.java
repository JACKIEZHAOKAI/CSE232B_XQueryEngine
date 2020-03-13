// Generated from /Users/xuzhaokai/Desktop/XQueryEngine/src/XQueryOpt.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryOptParser}.
 */
public interface XQueryOptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code XqFWR}
	 * labeled alternative in {@link XQueryOptParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqFWR(XQueryOptParser.XqFWRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqFWR}
	 * labeled alternative in {@link XQueryOptParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqFWR(XQueryOptParser.XqFWRContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryOptParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(XQueryOptParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryOptParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(XQueryOptParser.PathContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryOptParser#sep}.
	 * @param ctx the parse tree
	 */
	void enterSep(XQueryOptParser.SepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryOptParser#sep}.
	 * @param ctx the parse tree
	 */
	void exitSep(XQueryOptParser.SepContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqreturnVar}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterXqreturnVar(XQueryOptParser.XqreturnVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqreturnVar}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitXqreturnVar(XQueryOptParser.XqreturnVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqTworeturn}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterXqTworeturn(XQueryOptParser.XqTworeturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqTworeturn}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitXqTworeturn(XQueryOptParser.XqTworeturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Xqreturnpath}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterXqreturnpath(XQueryOptParser.XqreturnpathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Xqreturnpath}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitXqreturnpath(XQueryOptParser.XqreturnpathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqTagreturn}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterXqTagreturn(XQueryOptParser.XqTagreturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqTagreturn}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitXqTagreturn(XQueryOptParser.XqTagreturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondAnd}
	 * labeled alternative in {@link XQueryOptParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondAnd(XQueryOptParser.CondAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondAnd}
	 * labeled alternative in {@link XQueryOptParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondAnd(XQueryOptParser.CondAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondEqual}
	 * labeled alternative in {@link XQueryOptParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEqual(XQueryOptParser.CondEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondEqual}
	 * labeled alternative in {@link XQueryOptParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEqual(XQueryOptParser.CondEqualContext ctx);
}