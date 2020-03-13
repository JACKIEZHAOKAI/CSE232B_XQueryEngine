// Generated from /Users/xuzhaokai/Desktop/XQueryEngine/src/XQuery.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryParser}.
 */
public interface XQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code ApPath}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApPath(XQueryParser.ApPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ApPath}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApPath(XQueryParser.ApPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ApDoc}
	 * labeled alternative in {@link XQueryParser#doc}.
	 * @param ctx the parse tree
	 */
	void enterApDoc(XQueryParser.ApDocContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ApDoc}
	 * labeled alternative in {@link XQueryParser#doc}.
	 * @param ctx the parse tree
	 */
	void exitApDoc(XQueryParser.ApDocContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAllChildren(XQueryParser.AllChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAllChildren(XQueryParser.AllChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpwithP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpwithP(XQueryParser.RpwithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpwithP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpwithP(XQueryParser.RpwithPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Txt}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTxt(XQueryParser.TxtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Txt}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTxt(XQueryParser.TxtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TagName}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TagName}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpAll}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAll(XQueryParser.RpAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpAll}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAll(XQueryParser.RpAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParent(XQueryParser.ParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParent(XQueryParser.ParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XQueryParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XQueryParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpChildren}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpChildren(XQueryParser.RpChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpChildren}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpChildren(XQueryParser.RpChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Current}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterCurrent(XQueryParser.CurrentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Current}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitCurrent(XQueryParser.CurrentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TwoRp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTwoRp(XQueryParser.TwoRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TwoRp}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTwoRp(XQueryParser.TwoRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XQueryParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XQueryParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltEqual}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltEqual(XQueryParser.FltEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltEqual}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltEqual(XQueryParser.FltEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltRp}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltRp(XQueryParser.FltRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltRp}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltRp(XQueryParser.FltRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltAnd}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltAnd(XQueryParser.FltAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltAnd}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltAnd(XQueryParser.FltAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltIs}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltIs(XQueryParser.FltIsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltIs}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltIs(XQueryParser.FltIsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltOr}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltOr(XQueryParser.FltOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltOr}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltOr(XQueryParser.FltOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltNot}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltNot(XQueryParser.FltNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltNot}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltNot(XQueryParser.FltNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FltwithP}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFltwithP(XQueryParser.FltwithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FltwithP}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFltwithP(XQueryParser.FltwithPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqAp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqAp(XQueryParser.XqApContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqAp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqAp(XQueryParser.XqApContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqTwoXq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqTwoXq(XQueryParser.XqTwoXqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqTwoXq}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqTwoXq(XQueryParser.XqTwoXqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqAll}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqAll(XQueryParser.XqAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqAll}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqAll(XQueryParser.XqAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqJoin}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqJoin(XQueryParser.XqJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqJoin}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqJoin(XQueryParser.XqJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqVariable}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqVariable(XQueryParser.XqVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqVariable}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqVariable(XQueryParser.XqVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqwithP}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqwithP(XQueryParser.XqwithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqwithP}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqwithP(XQueryParser.XqwithPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqFLWR}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqFLWR(XQueryParser.XqFLWRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqFLWR}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqFLWR(XQueryParser.XqFLWRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqConstant}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqConstant(XQueryParser.XqConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqConstant}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqConstant(XQueryParser.XqConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqTag}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqTag(XQueryParser.XqTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqTag}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqTag(XQueryParser.XqTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqLet}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqLet(XQueryParser.XqLetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqLet}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqLet(XQueryParser.XqLetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XqRp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqRp(XQueryParser.XqRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XqRp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqRp(XQueryParser.XqRpContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondIdentityEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondIdentityEqual(XQueryParser.CondIdentityEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondIdentityEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondIdentityEqual(XQueryParser.CondIdentityEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondWithP}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondWithP(XQueryParser.CondWithPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondWithP}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondWithP(XQueryParser.CondWithPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondOr(XQueryParser.CondOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondOr(XQueryParser.CondOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondAnd(XQueryParser.CondAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondAnd(XQueryParser.CondAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondValueEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondValueEqual(XQueryParser.CondValueEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondValueEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondValueEqual(XQueryParser.CondValueEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEmpty(XQueryParser.CondEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEmpty(XQueryParser.CondEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondSome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondSome(XQueryParser.CondSomeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondSome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondSome(XQueryParser.CondSomeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondNot(XQueryParser.CondNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondNot(XQueryParser.CondNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#tList}.
	 * @param ctx the parse tree
	 */
	void enterTList(XQueryParser.TListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#tList}.
	 * @param ctx the parse tree
	 */
	void exitTList(XQueryParser.TListContext ctx);
}