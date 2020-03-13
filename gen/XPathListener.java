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
	 * Enter a parse tree produced by the {@code ApChildren}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApChildren(XPathParser.ApChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ApChildren}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApChildren(XPathParser.ApChildrenContext ctx);
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
	 * Enter a parse tree produced by the {@code RpAll}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAll(XPathParser.RpAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpAll}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAll(XPathParser.RpAllContext ctx);
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
	 * Enter a parse tree produced by the {@code Text}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterText(XPathParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Text}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitText(XPathParser.TextContext ctx);
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
	 * Enter a parse tree produced by the {@code FilterEqual}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterEqual(XPathParser.FilterEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterEqual}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterEqual(XPathParser.FilterEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterNot}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterNot(XPathParser.FilterNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterNot}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterNot(XPathParser.FilterNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterOr}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterOr(XPathParser.FilterOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterOr}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterOr(XPathParser.FilterOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterAnd}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterAnd(XPathParser.FilterAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterAnd}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterAnd(XPathParser.FilterAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterRp}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterRp(XPathParser.FilterRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterRp}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterRp(XPathParser.FilterRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterwithP}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterwithP(XPathParser.FilterwithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterwithP}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterwithP(XPathParser.FilterwithPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterIs}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterIs(XPathParser.FilterIsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterIs}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterIs(XPathParser.FilterIsContext ctx);
}