lexer grammar TemplateLexer;

fragment WS_CH    : [ \t]+ ;
fragment NL_CH    : '\r'? '\n' ;
fragment ID_START : [a-zA-Z_] ;
fragment ID_CONT  : [a-zA-Z0-9_]* ;
fragment ID       : ID_START ID_CONT ;
fragment NUM      : [0-9]+ ('.' [0-9]+)? ;
fragment STR_DQ   : '"'  ( ~["\\\r\n] | '\\' . )* '"' ;
fragment STR_SQ   : '\'' ( ~['\\\r\n] | '\\' . )* '\'' ;
fragment STR      : STR_DQ | STR_SQ ;
CSS_BLOCK_START
  : '{%' WS_CH* 'css' WS_CH* '%}' -> pushMode(CSS_MODE)
  ;
JSTMT_OPEN
  : '{%' -> pushMode(JINJA_BLOCK_MODE)
  ;
JPRINT_OPEN
  : '{{' -> pushMode(JINJA_EXPR_MODE)
  ;
LT_SLASH
  : '</' -> pushMode(TAG_MODE)
  ;
LT
  : '<'  -> pushMode(TAG_MODE)
  ;

NEWLINE
  : NL_CH
  ;

HTML_TEXT
  : (~[<{ \r\n] | '{' ~[%#{])+
  ;

TEMPLATE_WS
  : WS_CH -> skip
  ;

mode TAG_MODE;

TAG_CLOSE
  : '>' -> popMode
  ;

SELF_CLOSE_TAG
  : '/>' -> popMode
  ;

HTML_EQ
  : '='
  ;

TAG_NAME
  : [a-zA-Z][a-zA-Z0-9-]*
  ;

TAG_WS
  : [ \t\r\n]+ -> skip
  ;

TAG_STRING
  : STR
  ;
TAG_OTHER
  : . -> skip
  ;
mode JINJA_EXPR_MODE;
JPRINT_CLOSE
  : '}}' -> popMode
  ;
EXPR_WS
  : [ \t\r\n]+ -> skip
  ;
EXPR_NUMBER
  : NUM
  ;
EXPR_STRING
  : STR
  ;
EXPR_ID
  : [a-zA-Z_][a-zA-Z0-9_]*
  ;

EXPR_OP
  : . -> skip
  ;

mode JINJA_BLOCK_MODE;

JSTMT_CLOSE
  : '%}' -> popMode
  ;
BLOCK_WS
  : [ \t\r\n]+ -> skip
  ;


BLOCK_IF     : 'if' ;
BLOCK_ELIF   : 'elif' ;
BLOCK_ELSE   : 'else' ;
BLOCK_ENDIF  : 'endif' ;

BLOCK_FOR    : 'for' ;
BLOCK_IN     : 'in' ;
BLOCK_ENDFOR : 'endfor' ;

BLOCK_SET    : 'set' ;


BLOCK_TRUE  : 'True' ;
BLOCK_FALSE : 'False' ;
BLOCK_NONE  : 'None' ;


BLOCK_EQ    : '=' ;

BLOCK_EQEQ  : '==' ;
BLOCK_NEQ   : '!=' ;
BLOCK_LTE   : '<=' ;
BLOCK_GTE   : '>=' ;
BLOCK_LT    : '<' ;
BLOCK_GT    : '>' ;

BLOCK_NUMBER : NUM ;
BLOCK_STRING : STR ;
BLOCK_ID     : [a-zA-Z_][a-zA-Z0-9_]* ;

BLOCK_OTHER
  : . -> skip
  ;


mode CSS_MODE;


CSS_BLOCK_END_INNER
  : '{%' WS_CH* 'endcss' WS_CH* '%}' -> popMode
  ;

CSS_WS
  : [ \t\r\n]+ -> skip
  ;

CSS_DOT    : '.' ;
CSS_HASH   : '#' ;
CSS_LBRACE : '{' ;
CSS_RBRACE : '}' ;
CSS_COLON  : ':' ;
CSS_SEMI   : ';' ;
CSS_MINUS  : '-' ;

CSS_NUMBER : NUM ;
CSS_STRING : STR ;
CSS_NAME   : [a-zA-Z_][a-zA-Z0-9_]* ;

CSS_OTHER
  : . -> skip
  ;
