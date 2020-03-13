// Generated from /Users/xuzhaokai/Desktop/XQueryEngine/src/XQueryOpt.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XQueryOptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, NAME=20, StringConstant=21, Variable=22, WhiteSpace=23;
	public static final int
		RULE_xq = 0, RULE_path = 1, RULE_sep = 2, RULE_returnClause = 3, RULE_cond = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"xq", "path", "sep", "returnClause", "cond"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'for'", "'in'", "','", "'where'", "'return'", "'doc'", "'('", 
			"')'", "'text()'", "'/'", "'//'", "'<'", "'>'", "'{'", "'}'", "'</'", 
			"'='", "'eq'", "'and'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "NAME", "StringConstant", 
			"Variable", "WhiteSpace"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XQueryOpt.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XQueryOptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class XqContext extends ParserRuleContext {
		public XqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xq; }
	 
		public XqContext() { }
		public void copyFrom(XqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class XqFWRContext extends XqContext {
		public List<TerminalNode> Variable() { return getTokens(XQueryOptParser.Variable); }
		public TerminalNode Variable(int i) {
			return getToken(XQueryOptParser.Variable, i);
		}
		public List<PathContext> path() {
			return getRuleContexts(PathContext.class);
		}
		public PathContext path(int i) {
			return getRuleContext(PathContext.class,i);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public ReturnClauseContext returnClause() {
			return getRuleContext(ReturnClauseContext.class,0);
		}
		public XqFWRContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterXqFWR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitXqFWR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitXqFWR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XqContext xq() throws RecognitionException {
		XqContext _localctx = new XqContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xq);
		int _la;
		try {
			_localctx = new XqFWRContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(T__0);
			setState(11);
			match(Variable);
			setState(12);
			match(T__1);
			setState(13);
			path();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(14);
				match(T__2);
				setState(15);
				match(Variable);
				setState(16);
				match(T__1);
				setState(17);
				path();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(T__3);
			setState(24);
			cond(0);
			setState(25);
			match(T__4);
			setState(26);
			returnClause(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathContext extends ParserRuleContext {
		public TerminalNode StringConstant() { return getToken(XQueryOptParser.StringConstant, 0); }
		public TerminalNode Variable() { return getToken(XQueryOptParser.Variable, 0); }
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public List<TerminalNode> NAME() { return getTokens(XQueryOptParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(XQueryOptParser.NAME, i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_path);
		try {
			int _alt;
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(28);
					match(T__5);
					setState(29);
					match(T__6);
					setState(30);
					match(StringConstant);
					setState(31);
					match(T__7);
					}
					break;
				case Variable:
					{
					setState(32);
					match(Variable);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(35);
						sep();
						setState(36);
						match(NAME);
						}
						} 
					}
					setState(42);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(43);
					match(T__5);
					setState(44);
					match(T__6);
					setState(45);
					match(StringConstant);
					setState(46);
					match(T__7);
					}
					break;
				case Variable:
					{
					setState(47);
					match(Variable);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(50);
						sep();
						setState(51);
						match(NAME);
						}
						} 
					}
					setState(57);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				setState(58);
				sep();
				setState(59);
				match(T__8);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SepContext extends ParserRuleContext {
		public SepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterSep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitSep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitSep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SepContext sep() throws RecognitionException {
		SepContext _localctx = new SepContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnClauseContext extends ParserRuleContext {
		public ReturnClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnClause; }
	 
		public ReturnClauseContext() { }
		public void copyFrom(ReturnClauseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class XqreturnVarContext extends ReturnClauseContext {
		public TerminalNode Variable() { return getToken(XQueryOptParser.Variable, 0); }
		public XqreturnVarContext(ReturnClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterXqreturnVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitXqreturnVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitXqreturnVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqTworeturnContext extends ReturnClauseContext {
		public List<ReturnClauseContext> returnClause() {
			return getRuleContexts(ReturnClauseContext.class);
		}
		public ReturnClauseContext returnClause(int i) {
			return getRuleContext(ReturnClauseContext.class,i);
		}
		public XqTworeturnContext(ReturnClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterXqTworeturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitXqTworeturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitXqTworeturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqreturnpathContext extends ReturnClauseContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public XqreturnpathContext(ReturnClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterXqreturnpath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitXqreturnpath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitXqreturnpath(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqTagreturnContext extends ReturnClauseContext {
		public List<TerminalNode> NAME() { return getTokens(XQueryOptParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(XQueryOptParser.NAME, i);
		}
		public ReturnClauseContext returnClause() {
			return getRuleContext(ReturnClauseContext.class,0);
		}
		public XqTagreturnContext(ReturnClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterXqTagreturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitXqTagreturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitXqTagreturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnClauseContext returnClause() throws RecognitionException {
		return returnClause(0);
	}

	private ReturnClauseContext returnClause(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ReturnClauseContext _localctx = new ReturnClauseContext(_ctx, _parentState);
		ReturnClauseContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_returnClause, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new XqreturnVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(66);
				match(Variable);
				}
				break;
			case 2:
				{
				_localctx = new XqTagreturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(67);
				match(T__11);
				setState(68);
				match(NAME);
				setState(69);
				match(T__12);
				setState(70);
				match(T__13);
				setState(71);
				returnClause(0);
				setState(72);
				match(T__14);
				setState(73);
				match(T__15);
				setState(74);
				match(NAME);
				setState(75);
				match(T__12);
				}
				break;
			case 3:
				{
				_localctx = new XqreturnpathContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				path();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(85);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new XqTworeturnContext(new ReturnClauseContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_returnClause);
					setState(80);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(81);
					match(T__2);
					setState(82);
					returnClause(4);
					}
					} 
				}
				setState(87);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
	 
		public CondContext() { }
		public void copyFrom(CondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CondAndContext extends CondContext {
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public CondAndContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterCondAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitCondAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitCondAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondEqualContext extends CondContext {
		public List<TerminalNode> Variable() { return getTokens(XQueryOptParser.Variable); }
		public TerminalNode Variable(int i) {
			return getToken(XQueryOptParser.Variable, i);
		}
		public List<TerminalNode> StringConstant() { return getTokens(XQueryOptParser.StringConstant); }
		public TerminalNode StringConstant(int i) {
			return getToken(XQueryOptParser.StringConstant, i);
		}
		public CondEqualContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).enterCondEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryOptListener ) ((XQueryOptListener)listener).exitCondEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryOptVisitor ) return ((XQueryOptVisitor<? extends T>)visitor).visitCondEqual(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		return cond(0);
	}

	private CondContext cond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondContext _localctx = new CondContext(_ctx, _parentState);
		CondContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_cond, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new CondEqualContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(89);
			_la = _input.LA(1);
			if ( !(_la==StringConstant || _la==Variable) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(90);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__17) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(91);
			_la = _input.LA(1);
			if ( !(_la==StringConstant || _la==Variable) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(98);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CondAndContext(new CondContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_cond);
					setState(93);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(94);
					match(T__18);
					setState(95);
					cond(2);
					}
					} 
				}
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return returnClause_sempred((ReturnClauseContext)_localctx, predIndex);
		case 4:
			return cond_sempred((CondContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean returnClause_sempred(ReturnClauseContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean cond_sempred(CondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31h\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\3"+
		"\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\3\3\3\3\3\3\3\3\3\5\3\63\n\3\3\3\3"+
		"\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\3\3\3\3\3\5\3@\n\3\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5Q\n\5\3\5\3\5\3\5\7\5V\n"+
		"\5\f\5\16\5Y\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6c\n\6\f\6\16\6f\13"+
		"\6\3\6\2\4\b\n\7\2\4\6\b\n\2\5\3\2\f\r\3\2\27\30\3\2\23\24\2l\2\f\3\2"+
		"\2\2\4?\3\2\2\2\6A\3\2\2\2\bP\3\2\2\2\nZ\3\2\2\2\f\r\7\3\2\2\r\16\7\30"+
		"\2\2\16\17\7\4\2\2\17\26\5\4\3\2\20\21\7\5\2\2\21\22\7\30\2\2\22\23\7"+
		"\4\2\2\23\25\5\4\3\2\24\20\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3"+
		"\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\6\2\2\32\33\5\n\6\2\33\34\7"+
		"\7\2\2\34\35\5\b\5\2\35\3\3\2\2\2\36\37\7\b\2\2\37 \7\t\2\2 !\7\27\2\2"+
		"!$\7\n\2\2\"$\7\30\2\2#\36\3\2\2\2#\"\3\2\2\2$*\3\2\2\2%&\5\6\4\2&\'\7"+
		"\26\2\2\')\3\2\2\2(%\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+@\3\2\2\2,"+
		"*\3\2\2\2-.\7\b\2\2./\7\t\2\2/\60\7\27\2\2\60\63\7\n\2\2\61\63\7\30\2"+
		"\2\62-\3\2\2\2\62\61\3\2\2\2\639\3\2\2\2\64\65\5\6\4\2\65\66\7\26\2\2"+
		"\668\3\2\2\2\67\64\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;"+
		"9\3\2\2\2<=\5\6\4\2=>\7\13\2\2>@\3\2\2\2?#\3\2\2\2?\62\3\2\2\2@\5\3\2"+
		"\2\2AB\t\2\2\2B\7\3\2\2\2CD\b\5\1\2DQ\7\30\2\2EF\7\16\2\2FG\7\26\2\2G"+
		"H\7\17\2\2HI\7\20\2\2IJ\5\b\5\2JK\7\21\2\2KL\7\22\2\2LM\7\26\2\2MN\7\17"+
		"\2\2NQ\3\2\2\2OQ\5\4\3\2PC\3\2\2\2PE\3\2\2\2PO\3\2\2\2QW\3\2\2\2RS\f\5"+
		"\2\2ST\7\5\2\2TV\5\b\5\6UR\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\t\3"+
		"\2\2\2YW\3\2\2\2Z[\b\6\1\2[\\\t\3\2\2\\]\t\4\2\2]^\t\3\2\2^d\3\2\2\2_"+
		"`\f\3\2\2`a\7\25\2\2ac\5\n\6\4b_\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2"+
		"e\13\3\2\2\2fd\3\2\2\2\13\26#*\629?PWd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}