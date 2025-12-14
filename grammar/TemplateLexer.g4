lexer grammar TemplateLexer;

tokens {
  JPRINT_OPEN, JPRINT_CLOSE,
  JSTMT_OPEN, JSTMT_CLOSE,
  CSS_BLOCK_START, CSS_BLOCK_END,

  LT, LT_SLASH, GT, SLASH,

  IF, ENDIF, FOR, ENDFOR, IN, SET,

  ASSIGN, EQ, NEQ, LE, GE, LT_OP, GT_OP,
  PLUS, MINUS, STAR, DIV, NOT, AND, OR,

  LBRACE, RBRACE, COLON, SEMI, DOT, HASH,
  LPAREN, RPAREN,

  NAME, NUMBER, STRING,
  TEXT
}

fragment ID_START : [a-zA-Z_] ;
fragment ID_CONT  : [a-zA-Z0-9_.$-] ;
fragment ID       : ID_START ID_CONT* ;

fragment DIGIT    : [0-9] ;
fragment NUM      : DIGIT+ ('.' DIGIT+)? ;

fragment STR_DQ   : '"' (~["\\] | '\\' .)* '"' ;
fragment STR_SQ   : '\'' (~['\\] | '\\' .)* '\'' ;
fragment STR      : STR_DQ | STR_SQ ;

fragment WS_CH    : [ \t\r\n]+ ;


JPRINT_OPEN_R
  : '{{' -> type(JPRINT_OPEN), pushMode(JINJA_EXPR)
  ;


CSS_BLOCK_START_R
  : '{%' WS_CH* 'css' WS_CH* '%}'
    -> type(CSS_BLOCK_START), pushMode(CSS_MODE)
  ;


JSTMT_OPEN_R
  : '{%' -> type(JSTMT_OPEN), pushMode(JINJA_STMT)
  ;


LT_SLASH_R
  : '</' -> type(LT_SLASH), pushMode(HTML_TAG)
  ;


LT_R
  : '<' -> type(LT), pushMode(HTML_TAG)
  ;


TEXT_R
  : ( ~[<{] | '{' ~[{%] )+ -> type(TEXT)
  ;

WS_R : WS_CH -> skip ;


mode HTML_TAG;

TAG_WS : WS_CH -> skip ;

GT_R    : '>' -> type(GT), popMode ;
SLASH_R : '/' -> type(SLASH) ;

NAME_TAG
  : ID -> type(NAME)
  ;

ERR_TAG : . -> skip ;


mode JINJA_EXPR;

JPRINT_CLOSE_R
  : '}}' -> type(JPRINT_CLOSE), popMode
  ;

EXPR_WS : WS_CH -> skip ;

LPAREN_R : '(' -> type(LPAREN) ;
RPAREN_R : ')' -> type(RPAREN) ;

EQ_R   : '==' -> type(EQ) ;
NEQ_R  : '!=' -> type(NEQ) ;
LE_R   : '<=' -> type(LE) ;
GE_R   : '>=' -> type(GE) ;
LTOP_R : '<'  -> type(LT_OP) ;
GTOP_R : '>'  -> type(GT_OP) ;

PLUS_R  : '+' -> type(PLUS) ;
MINUS_R : '-' -> type(MINUS) ;
STAR_R  : '*' -> type(STAR) ;
DIV_R   : '/' -> type(DIV) ;

NOT_R : 'not' -> type(NOT) ;
AND_R : 'and' -> type(AND) ;
OR_R  : 'or'  -> type(OR) ;

NUMBER_R : NUM -> type(NUMBER) ;
STRING_R : STR -> type(STRING) ;
NAME_R   : ID  -> type(NAME) ;

ERR_EXPR : . -> skip ;


mode JINJA_STMT;

JSTMT_CLOSE_R
  : '%}' -> type(JSTMT_CLOSE), popMode
  ;

STMT_WS : WS_CH -> skip ;

/* Keywords */
IF_R     : 'if'     -> type(IF) ;
ENDIF_R  : 'endif'  -> type(ENDIF) ;
FOR_R    : 'for'    -> type(FOR) ;
ENDFOR_R : 'endfor' -> type(ENDFOR) ;
IN_R     : 'in'     -> type(IN) ;
SET_R    : 'set'    -> type(SET) ;

//exp
ASSIGN_R : '=' -> type(ASSIGN) ;

EQ_S  : '==' -> type(EQ) ;
NEQ_S : '!=' -> type(NEQ) ;
LE_S  : '<=' -> type(LE) ;
GE_S  : '>=' -> type(GE) ;
LT_S  : '<'  -> type(LT_OP) ;
GT_S  : '>'  -> type(GT_OP) ;

PLUS_S  : '+' -> type(PLUS) ;
MINUS_S : '-' -> type(MINUS) ;
STAR_S  : '*' -> type(STAR) ;
DIV_S   : '/' -> type(DIV) ;

NOT_S : 'not' -> type(NOT) ;
AND_S : 'and' -> type(AND) ;
OR_S  : 'or'  -> type(OR) ;

LP_S : '(' -> type(LPAREN) ;
RP_S : ')' -> type(RPAREN) ;

NUMBER_S : NUM -> type(NUMBER) ;
STRING_S : STR -> type(STRING) ;
NAME_S   : ID  -> type(NAME) ;

ERR_STMT : . -> skip ;

//CSS
mode CSS_MODE;

CSS_BLOCK_END_R
  : '{%' WS_CH* 'endcss' WS_CH* '%}'
    -> type(CSS_BLOCK_END), popMode
  ;

CSS_WS : WS_CH -> skip ;

DOT_R    : '.' -> type(DOT) ;
HASH_R   : '#' -> type(HASH) ;
LBRACE_R : '{' -> type(LBRACE) ;
RBRACE_R : '}' -> type(RBRACE) ;
COLON_R  : ':' -> type(COLON) ;
SEMI_R   : ';' -> type(SEMI) ;

DASH_R : '-' -> type(MINUS) ;

NUMBER_C : NUM -> type(NUMBER) ;
STRING_C : STR -> type(STRING) ;
NAME_C   : ID  -> type(NAME) ;

ERR_CSS : . -> skip ;
