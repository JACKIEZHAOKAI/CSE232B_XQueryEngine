grammar XQueryOpt;

// ###########################################################
// Milestone3
// ###########################################################
/*  Defining a Subset of XQuery

    Consider the following subset of XQuery, where there are no nested FLWR expressions and hence it is easier
    to introduce join operations. One may construct a corresponding syntax:

    Queries of the XQuery subset can be rewritten to make ecient use of the join operator,
*/

/*  XQuery ::= 'for' V1 'in' Path1',' ..., Vn 'in' Pathn
    'where' Cond 'return' Return    */
xq
    : 'for' Variable 'in' path (',' Variable 'in' path)* 'where' cond 'return' returnClause   #XqFWR
    ;

/*  Path ::= ('doc('FileName')'
    |Var) Sep n1 Sep ... Sep nm
    | ('doc('FileName')'
    |Var) Sep n1 Sep ... Sep 'text()'   */
path
    : ('doc' '(' StringConstant ')'
    | Variable) (sep NAME)*
	| ('doc' '(' StringConstant ')'
	| Variable) (sep NAME)* sep 'text()'
    ;

/*  Sep ::= '/'
    | '//'  */
sep
	: '/'
	| '//'
	;

/*  Return ::= Var
    | Return ',' Return
    | '<'n'>' Return '</'n'>'
    | Path  */
returnClause
    : Variable                                                              #XqreturnVar
    | returnClause  ',' returnClause                                        #XqTworeturn
    | '<' NAME '>' '{'returnClause'}' '</' NAME '>'                         #XqTagreturn
    | path                                                                  #Xqreturnpath
    ;

/*  Cond ::= (Var|Constant) 'eq' (Var|Constant)
    | Cond 'and' Cond   */
cond
    : (Variable|StringConstant) ('=' | 'eq') (Variable|StringConstant)       # CondEqual
    | cond 'and' cond                                                        # CondAnd
    ;

// Variable Name
NAME: [a-zA-Z0-9_-]+;
StringConstant: '"' (~'"')* '"';
Variable: '$' NAME;
WhiteSpace: [ \n\t\r]+ -> skip;
