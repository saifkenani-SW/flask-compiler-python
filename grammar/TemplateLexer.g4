lexer grammar TemplateLexer;

/* =========================
   Helpers
========================= */
fragment WS_CH    : [ \t]+ ;
fragment NL_CH    : '\r'? '\n' ;

fragment ID_START : [a-zA-Z_] ;
fragment ID_CONT  : [a-zA-Z0-9_]* ;
fragment ID       : ID_START ID_CONT ;

fragment NUM      : [0-9]+ ('.' [0-9]+)? ;

fragment STR_DQ   : '"'  ( ~["\\\r\n] | '\\' . )* '"' ;
fragment STR_SQ   : '\'' ( ~['\\\r\n] | '\\' . )* '\'' ;
fragment STR      : STR_DQ | STR_SQ ;

/* =========================
   TEMPLATE (DEFAULT) MODE
========================= */

/* --- CSS block start: {% css %} --- */
CSS_BLOCK_START
  : '{%' WS_CH* 'css' WS_CH* '%}' -> pushMode(CSS_MODE)
  ;

/* --- Jinja statement start: {% ... %} --- */
JSTMT_OPEN
  : '{%' -> pushMode(JINJA_BLOCK_MODE)
  ;

/* --- Jinja print start: {{ ... }} --- */
JPRINT_OPEN
  : '{{' -> pushMode(JINJA_EXPR_MODE)
  ;

/* --- HTML tags: < ... > and </ ... > --- */
LT_SLASH
  : '</' -> pushMode(TAG_MODE)
  ;

LT
  : '<'  -> pushMode(TAG_MODE)
  ;

/* --- New lines as tokens (helpful for parser output readability) --- */
NEWLINE
  : NL_CH
  ;

/* --- Any other plain text (outside tags/jinja/css) --- */
HTML_TEXT
  : (~[<{ \r\n] | '{' ~[%#{])+    // stop at < or { or newline
  ;

/* --- skip spaces/tabs in template body --- */
TEMPLATE_WS
  : WS_CH -> skip
  ;

/* =========================
   TAG MODE  (after < or </)
========================= */
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

/* =========================
   JINJA EXPR MODE  {{ ... }}
========================= */
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

/* =========================
   JINJA BLOCK MODE  {% ... %}
========================= */
mode JINJA_BLOCK_MODE;

JSTMT_CLOSE
  : '%}' -> popMode
  ;

BLOCK_WS
  : [ \t\r\n]+ -> skip
  ;

/* keywords */
BLOCK_IF     : 'if' ;
BLOCK_ELIF   : 'elif' ;
BLOCK_ELSE   : 'else' ;
BLOCK_ENDIF  : 'endif' ;

BLOCK_FOR    : 'for' ;
BLOCK_IN     : 'in' ;
BLOCK_ENDFOR : 'endfor' ;

BLOCK_SET    : 'set' ;

/* literals */
BLOCK_TRUE  : 'True' ;
BLOCK_FALSE : 'False' ;
BLOCK_NONE  : 'None' ;

/* operators */
BLOCK_EQ    : '=' ;

BLOCK_EQEQ  : '==' ;
BLOCK_NEQ   : '!=' ;
BLOCK_LTE   : '<=' ;
BLOCK_GTE   : '>=' ;
BLOCK_LT    : '<' ;
BLOCK_GT    : '>' ;

/* values */
BLOCK_NUMBER : NUM ;
BLOCK_STRING : STR ;
BLOCK_ID     : [a-zA-Z_][a-zA-Z0-9_]* ;

BLOCK_OTHER
  : . -> skip
  ;

/* =========================
   CSS MODE (between {% css %} ... {% endcss %})
========================= */
mode CSS_MODE;

/* endcss */
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
