grammar XQuery;


// ###########################################################
// XPath defined in Milestone1  XPath.g4
// ###########################################################
ap
	: doc '/' rp                  # ApPath
	| doc '//' rp                 # ApPath
	;

doc
	: 'doc' '(' StringConstant ')'     #ApDoc
	;

/*  relative path
    rp -> tagName| * | .| ..| text()| @attName
        | (rp) | rp1/rp2 | rp1//rp2 | rp[f] | rp1,rp2  */
rp
	: NAME                         # TagName
	| '.'                          # Current
	| '..'                         # Parent
	| '*'                          # AllChildren
	| 'text()'                     # Txt
	| '@' NAME                     # Attribute
	| '(' rp ')'                   # RpwithP
	| rp '/' rp                    # RpChildren
	| rp '//' rp                   # RpAll
	| rp '[' filter ']'            # RpFilter
	| rp ',' rp                    # TwoRp
	;

/*  path filter
    f → rp| rp1 =rp2 | rp1 eq rp2 |rp1 ==rp2 |rp1 is rp2
        | (f)| f1 and f2 | f1 or f2| not f
*/
filter
	: rp                           # FltRp
	| rp '=' rp                    # FltEqual
	| rp 'eq' rp                   # FltEqual
	| rp '==' rp                   # FltIs
	| rp 'is' rp                   # FltIs
	| '(' filter ')'               # FltwithP
	| filter 'and' filter          # FltAnd
	| filter 'or' filter           # FltOr
	| 'not' filter                 # FltNot
	;


DOC: 'doc' ;
NAME: [a-zA-Z0-9_-]+;

// ###########################################################
// XQuery   in Milestone2
// ###########################################################
xq
    : Variable                                                                 # XqVariable
    | StringConstant                                                           # XqConstant
    | ap                                                                       # XqAp
    | '(' xq ')'                                                               # XqwithP
    | xq '/' rp                                                                # XqRp
    | xq '//' rp                                                               # XqAll
    | xq ',' xq                                                                # XqTwoXq
    | '<' NAME '>' '{' xq '}' '</' NAME '>'                                    # XqTag
    | letClause xq                                                             # XqLet
    | forClause letClause? whereClause? returnClause                           # XqFLWR
    | 'join' '(' xq ',' xq ',' tList ',' tList ')'                             # XqJoin  // MS3 join operation
    ;

/*  forClause
        for V ar1 in XQ1, V ar2 in XQ2, . . . ,V arn in XQn */
forClause
    : 'for' Variable 'in' xq (',' Variable 'in' xq)*        //( )* means for many var in xq
    ;

/*  letClause
        let V arn+1 := XQn+1, . . . ,V arn+k := XQn+k   */
letClause
    : 'let' Variable ':=' xq (',' Variable ':=' xq)*
    ;

/*  whereClause
        where Cond  */
whereClause
    : 'where' cond
    ;

/*  returnClause
        return XQ1  */
returnClause
    : 'return' xq
    ;

/*  Condition
        ! XQ1 = XQ2 | XQ1 eq XQ2
        | XQ1 == XQ2 | XQ1 is XQ2
        | empty(XQ1)
        | some V ar1 in XQ1, . . . ,V arn in XQn satisfies Cond
        | (Cond1) | Cond1 and Cond2 | Cond1 or Cond2 | not Cond1
*/
cond
    : xq ('=' | 'eq') xq                                                       # CondValueEqual
    | xq ('==' | 'is') xq                                                      # CondIdentityEqual
    | 'empty' '(' xq ')'                                                       # CondEmpty
    | 'some' Variable 'in' xq (',' Variable 'in' xq)* 'satisfies' cond         # CondSome
    | '(' cond ')'                                                             # CondWithP
    | cond 'and' cond                                                          # CondAnd
    | cond 'or' cond                                                           # CondOr
    | 'not' cond                                                               # CondNot
    ;

tList : '[' NAME (',' NAME)* ']' ;  // MS3 join operation

StringConstant: '"' (~'"')* '"';
Variable: '$' NAME;
WhiteSpace: [ \n\t\r]+ -> skip;
