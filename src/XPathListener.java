// Generated from /Users/xuzhaokai/Desktop/XQueryEngine/src/XPath.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code ApDoc}
	 * labeled alternative in {@link XPathParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterApDoc(XPathParser.ApDocContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ApDoc}
	 * labeled alternative in {@link XPathParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitApDoc(XPathParser.ApDocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ApPath}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApPath(XPathParser.ApPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ApPath}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApPath(XPathParser.ApPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAllChildren(XPathParser.AllChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAllChildren(XPathParser.AllChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpwithP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpwithP(XPathParser.RpwithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpwithP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpwithP(XPathParser.RpwithPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Txt}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTxt(XPathParser.TxtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Txt}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTxt(XPathParser.TxtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpDescendent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpDescendent(XPathParser.RpDescendentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpDescendent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpDescendent(XPathParser.RpDescendentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TagName}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XPathParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TagName}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XPathParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParent(XPathParser.ParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParent(XPathParser.ParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XPathParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XPathParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpChildren}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpChildren(XPathParser.RpChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpChildren}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpChildren(XPathParser.RpChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Current}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterCurrent(XPathParser.CurrentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Current}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitCurrent(XPathParser.CurrentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TwoRp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTwoRp(XPathParser.TwoRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TwoRp}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTwoRp(XPathParser.TwoRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XPathParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XPathParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltEqual}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltEqual(XPathParser.FltEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltEqual}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltEqual(XPathParser.FltEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltRp}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltRp(XPathParser.FltRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltRp}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltRp(XPathParser.FltRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltAnd}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltAnd(XPathParser.FltAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltAnd}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltAnd(XPathParser.FltAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltIs}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltIs(XPathParser.FltIsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltIs}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltIs(XPathParser.FltIsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltOr}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltOr(XPathParser.FltOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltOr}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltOr(XPathParser.FltOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltNot}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltNot(XPathParser.FltNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltNot}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltNot(XPathParser.FltNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltwithP}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltwithP(XPathParser.FltwithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltwithP}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltwithP(XPathParser.FltwithPContext ctx);
}