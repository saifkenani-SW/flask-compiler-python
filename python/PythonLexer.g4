lexer grammar PythonLexer;

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


HTML_START: '<!' [ \t\r\n>] -> pushMode(TEMPLATE_MODE);

//  For TEMPLATE  (html , jinja2)
mode TEMPLATE_MODE;
//TEMPLATE_DOCTYPE: '<!DOCTYPE' ~[>]* '>' ;


// HTML
HTML_TAG_OPEN : '<';  // exp = < ID  (ID=ID)* > (exp | ID)*  </ID>
HTML_TAG_CLOSE : '>';
HTML_TAG_OPEN_SELF : '</';
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
HTML_EQ : '=' ;


// Jinja2
TEMPLATE_JINJA_BLOCK_START: '{%' -> pushMode(JINJA_BLOCK_MODE);
TEMPLATE_JINJA_EXPR_START: '{{' -> pushMode(JINJA_EXPR_MODE);
TEMPLATE_JINJA_COMMENT_START: '{#' -> pushMode(JINJA_COMMENT_MODE);

HTML_ID : [a-zA-Z][a-zA-Z0-9-]* ;
TEMPLATE_WS: [ \t\r\n]+ -> skip ;

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

BLOCK_ID: [a-zA-Z_][a-zA-Z_0-9]* ;
BLOCK_STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'' ;
BLOCK_NUMBER: [0-9]+ ('.' [0-9]+)? ;



BLOCK_END: '%}' -> popMode ;

mode JINJA_EXPR_MODE;
EXPR_END: '}}' -> popMode ;

mode JINJA_COMMENT_MODE;

COMMENT_END: '#}' -> popMode ;












ERROR_CHAR : . ;