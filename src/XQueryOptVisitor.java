// Generated from /Users/xuzhaokai/Desktop/XQueryEngine/src/XQueryOpt.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XQueryOptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XQueryOptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code XqFWR}
	 * labeled alternative in {@link XQueryOptParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqFWR(XQueryOptParser.XqFWRContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryOptParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(XQueryOptParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryOptParser#sep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSep(XQueryOptParser.SepContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XqreturnVar}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqreturnVar(XQueryOptParser.XqreturnVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XqTworeturn}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqTworeturn(XQueryOptParser.XqTworeturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Xqreturnpath}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqreturnpath(XQueryOptParser.XqreturnpathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XqTagreturn}
	 * labeled alternative in {@link XQueryOptParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqTagreturn(XQueryOptParser.XqTagreturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CondAnd}
	 * labeled alternative in {@link XQueryOptParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondAnd(XQueryOptParser.CondAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CondEqual}
	 * labeled alternative in {@link XQueryOptParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondEqual(XQueryOptParser.CondEqualContext ctx);
}