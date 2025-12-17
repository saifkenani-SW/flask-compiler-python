lexer grammar FlaskTemplateLexer;

@header{
package gen;
}
@lexer::superClass {
    PythonBasicLexerBase
}

tokens { INDENT, DEDENT }

// KeyWord
DEF      : 'def' ;
IF       : 'if' ;
ELIF     : 'elif' ;
ELSE     : 'else' ;
FOR      : 'for' ;
WHILE    : 'while' ;
RETURN   : 'return' ;
BREAK    : 'break' ;
CONTINUE : 'continue' ;
PASS     : 'pass' ;
GLOBAL   : 'global' ;
WITH     : 'with' ;
AS       : 'as' ;

FROM     : 'from' ;
IMPORT   : 'import' ;
CLASS    : 'class' ;
TRY      : 'try' ;
EXCEPT   : 'except' ;
FINALLY  : 'finally' ;
RAISE    : 'raise' ;
ASSERT   : 'assert' ;


// Logical Operators
AND      : 'and';
OR       : 'or';
IN       : 'in';
//ISNOT   : 'is not';
IS      : 'is';
NOT     : 'not';
LAMBDA   : 'lambda';


// Bool Values
TRUE     : 'True' ;
FALSE    : 'False' ;
NONE     : 'None' ;

// Punctuation and decorators
AT       : '@';
DOT      : '.' ;
COMMA    : ',' ;
COLON    : ':' ;
SEMICOLON: ';' ;
ELLIPSIS : '...' ;
ARROW    : '->' ;


//  Double-Character Operators
POWER      : '**'; // value = 2 ** 2
FLOORDIV   : '//'; //  value = 2 // 2
WALRUS     : ':='; // if (n := len(data)) > 10







//String
STRING
    : '"'  ( ~["\\\r\n] | '\\' . )* '"'
    | '\'' ( ~['\\\r\n] | '\\' . )* '\''
    | '"""' ( . | '\r' | '\n' )*? '"""'
    | '\'\'\'' ( . | '\r' | '\n' )*? '\'\'\''
    ;

// Numbers
FLOAT    : [0-9]+ '.' [0-9]+
         | '.' [0-9]+ ;
INT      : [0-9]+
         | '0x' [0-9a-fA-F]+ // Hex
         | '0o' [0-7]+  // Oct
         | '0b' [01]+ ; // Binary

// Assignment and Arithmetic Operators
EQ       : '=' ;
PLUS     : '+' ;
MINUS    : '-' ;
STAR     : '*' ;
SLASH    : '/' ;
PERCENT  : '%' ;
STAREQ   : '*=';
SLASHEQ  : '/=';
PLUSEQ   : '+=';
MINUSEQ  : '-=';
POWEREQ  : '**=' ;
FLOORDIVEQ: '//=' ;
BITANDEQ : '&=' ;
BITOREQ  : '|=' ;

// Comparison Operators
EQEQ     : '==' ;
GT       : '>' ;
LT       : '<' ;
GTE      : '>=' ;
LTE      : '<=' ;
NOTEQ    : '!=';


// Braces
LPAREN   : '(' ;
RPAREN   : ')' ;
LBRACK   : '[' ;
RBRACK   : ']' ;
LBRACE   : '{' ;
RBRACE   : '}' ;

LINE_CONTINUATION
    : '\\' '\r'? '\n' -> skip
    ;
// Identifiers ( app, Flask, next_id, allowed_file, __name__)
ID       : [a-zA-Z_][a-zA-Z_0-9]* ;

// NEWLINE
NEWLINE  : '\r'? '\n' ;

// Whitespace and Comments
WS       : [ \t]+ -> skip;
COMMENT  : '#' ~[\r\n]* -> skip;


HTML_DOCTYPE: '<!' [dD][oO][cC][tT][yY][pP][eE] .*? '>'
    -> pushMode(TEMPLATE_MODE);

//  For TEMPLATE  (html , jinja2)
mode TEMPLATE_MODE;
HTML_COMMENT
    : '<!--' .*? '-->' -> skip
    ;

// Jinja2
TEMPLATE_JINJA_BLOCK_START: '{%' -> pushMode(JINJA_BLOCK_MODE);
TEMPLATE_JINJA_EXPR_START: '{{' -> pushMode(JINJA_EXPR_MODE);
TEMPLATE_JINJA_COMMENT_START: '{#' -> pushMode(JINJA_COMMENT_MODE);

// CSS
CSS_START:'<style' -> pushMode(CSS_TAG_MODE);




// HTML
HTML_TAG_OPEN_SELF: '</' -> pushMode(TAG_MODE);
HTML_TAG_OPEN: '<' -> pushMode(TAG_MODE);
TEMPLATE_WS: [ \t\r\n]+ -> skip;

HTML_TEXT: (~[<{] | '{' ~[%#{])+;
TEMPLATE_END: '</html>' -> popMode;

mode TAG_MODE;
TAG_CLOSE: '>' -> popMode;
SELF_CLOSE_TAG: '/>' -> popMode;
HTML_EQ : '=' ;
VOID_TAG
    : 'area'
    | 'base'
    | 'br'
    | 'col'
    | 'embed'
    | 'hr'
    | 'img'
    | 'input'
    | 'link'
    | 'meta'
    | 'param'
    | 'source'
    | 'track'
    | 'wbr'
    ;
HTML_ID
    : [a-zA-Z][a-zA-Z0-9-]*
    ;
//HTML_STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'';
HTML_QUOTE: '"' -> pushMode(HTML_ATTR_VALUE_MODE);
HTML_APOSTROPHE: '\'' -> pushMode(HTML_ATTR_VALUE_MODE);
HTML_WS: [ \t\r\n]+ -> skip;

mode HTML_ATTR_VALUE_MODE;
ATTR_VALUE_QUOTE: '"' -> popMode;
ATTR_VALUE_APOSTROPHE: '\'' -> popMode;

// jinja2 nested html
ATTR_JINJA_EXPR_START: '{{' -> pushMode(JINJA_EXPR_MODE);
ATTR_JINJA_BLOCK_START: '{%' -> pushMode(JINJA_BLOCK_MODE);
ATTR_JINJA_COMMENT_START: '{#' -> pushMode(JINJA_COMMENT_MODE);
ATTR_VALUE_ESCAPE: '\\' .;
ATTR_VALUE_ID
    : ~["'\\{]+
    ;

HTML_ATTR_VALUE_WS: [ \t\r\n]+ -> skip;

mode JINJA_BLOCK_MODE;

BLOCK_EXTENDS: 'extends' ;
BLOCK_BLOCK: 'block' ;
BLOCK_ENDBLOCK: 'endblock' ;
BLOCK_IF: 'if' ;
BLOCK_ELIF: 'elif' ;
BLOCK_ELSE: 'else' ;
BLOCK_ENDIF: 'endif' ;
BLOCK_FOR: 'for' ;
BLOCK_ENDFOR: 'endfor' ;
BLOCK_SET: 'set' ;
BLOCK_INCLUDE: 'include' ;
BLOCK_IMPORT: 'import' ;
BLOCK_FROM: 'from' ;
BLOCK_WITH: 'with' ;
BLOCK_ENDWITH: 'endwith' ;
BLOCK_DOT : '.' ;

BLOCK_ID: [a-zA-Z_][a-zA-Z_0-9]* ;
BLOCK_STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'' ;
BLOCK_NUMBER: [0-9]+ ('.' [0-9]+)? ;
BLOCK_WS : [ \t\r\n]+ -> skip ;


BLOCK_END: '%}' -> popMode ;


mode JINJA_EXPR_MODE;

//  Symbols
EXPR_LPAREN   : '(' ;
EXPR_RPAREN   : ')' ;
EXPR_LBRACK   : '[' ;
EXPR_RBRACK   : ']' ;
EXPR_COMMA    : ',' ;
EXPR_COLON    : ':' ;
EXPR_DOT      : '.' ;
EXPR_PIPE     : '|' ;
EXPR_TILDE    : '~' ;
EXPR_QUESTION : '?' ;

//   Math Operators
EXPR_PLUS     : '+' ;
EXPR_MINUS    : '-' ;
EXPR_STAR     : '*' ;
EXPR_SLASH    : '/' ;
EXPR_PERCENT  : '%' ;
EXPR_POWER    : '**' ;
EXPR_FLOORDIV : '//' ;
EXPR_EQ : '=' ;

//   Comparison Operators
EXPR_EQEQ     : '==' ;
EXPR_NEQ      : '!=' ;
EXPR_LT       : '<' ;
EXPR_LTE      : '<=' ;
EXPR_GT       : '>' ;
EXPR_GTE      : '>=' ;

//   Logical Operators
EXPR_AND      : 'and' ;
EXPR_OR       : 'or' ;
EXPR_NOT      : 'not' ;

//    Keywords
EXPR_IN       : 'in' ;
EXPR_IS       : 'is' ;
EXPR_IF       : 'if' ;
EXPR_ELSE     : 'else' ;

// Boo
EXPR_TRUE     : 'true' | 'True' ;
EXPR_FALSE    : 'false' | 'False' ;
EXPR_NONE     : 'none' | 'None' | 'null' | 'Null' ;


// Identifiers
EXPR_ID
    : [a-zA-Z_][a-zA-Z_0-9]*
    ;

//   Strings
EXPR_STRING
    : '"' (~["\\}] | '\\' .)* '"'
    | '\'' (~['\\}] | '\\' .)* '\''
    ;

//  Numbers
EXPR_NUMBER : [0-9]+ ('.' [0-9]+)?  ;

EXPR_ELLIPSIS : '...' ;
EXPR_WALRUS   : ':=' ;


//  Whitespace
EXPR_WS
    : [ \t\r\n]+ -> skip
    ;
EXPR_END: '}}' -> popMode ;

mode JINJA_COMMENT_MODE;
COMMENT_TEXT : .+? ;
COMMENT_END: '#}' -> popMode ;


mode CSS_TAG_MODE;

CSS_TAG_CLOSE: '>' -> mode(CSS_CONTENT_MODE);

CSS_TAG_ATTR: [a-zA-Z][a-zA-Z0-9-]*;
CSS_TAG_EQ: '=';
CSS_TAG_STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'';

CSS_TAG_WS: [ \t\r\n]+ -> skip;

mode CSS_CONTENT_MODE;

STYLE_TAG_END: '</style>' -> popMode;
CSS_CONTENT_COMMENT: '/*' .*? '*/' -> skip;
CSS_LBRACE: '{' -> pushMode(CSS_BLOCK_MODE);
CSS_CONTENT_WS: [ \t\r\n]+ -> skip;

CSS_CONTENT: (~[<{ \t\r\n] | '<' ~[/{ \t\r\n] | '</' ~[sS{ \t\r\n])+;



mode CSS_BLOCK_MODE;
// CSS Braces
CSS_RBRACE: '}' -> popMode;
CSS_COLON: ':';
CSS_SEMICOLON: ';';
CSS_COMMA: ',';
CSS_DOT: '.';


// CSS Properties
CSS_PROPERTY
    : 'display' | 'margin' | 'font-family' | 'padding' | 'width'
    | 'border-right' | 'overflow-y' | 'border' | 'cursor'
    | 'transition' | 'height' | 'margin-right' | 'border-solid'
    | 'color' | 'background' | 'background-color' | 'border-radius'
    | 'gap' | 'border-bottom-color' | 'margin-bottom' | 'top'
    | 'left' | 'position' | 'justify-content' | 'align-items'
    | 'max-width' | 'box-sizing' | 'margin-top' | 'object-fit'|'font-style'
    | 'z-index' | 'text-decoration' | 'font-weight' | 'resize'|'padding-right'
    | 'text-align' | 'font-size' | 'grid-template-columns'|'max-height'
    | 'box-shadow' | 'flex-direction' | 'overflow'|'direction'|'flex-wrap'
    ;

CSS_SELECTOR: [^{}:;]+;




CSS_ATTR_SELECTOR: '[' [a-zA-Z-]+ '=' CSS_STRING ']';

CSS_NUMERIC: [0-9]+ ('.' [0-9]+)? (('px'|'em'|'rem'|'%'|'vh'|'vw')?);

fragment HEX_DIGIT : [0-9a-fA-F] ;
fragment HEX_COLOR_SHORT : HEX_DIGIT HEX_DIGIT HEX_DIGIT ;
fragment HEX_COLOR_LONG  : HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT ;


CSS_COLOR
    : '#' HEX_COLOR_SHORT
    | '#' HEX_COLOR_LONG
    | 'rgb('  [0-9 ,]+ ')'
    | 'rgba(' [0-9 ,.]+ ')'
    ;

// CSS Strings
CSS_STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'';
CSS_KEYWORD
    : [a-z][a-z-]*  // فقط الكلمات بحروف صغيرة
    ;
// CSS Comments
CSS_COMMENT: '/*' .*? '*/' -> skip;

// CSS Whitespace
CSS_BLOCK_WS: [ \t\r\n]+ -> skip;

// CSS Values
CSS_VALUE: [^};{]+;






ERROR_CHAR : . ;