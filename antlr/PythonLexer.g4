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
ELSE     : 'else' ;
FOR      : 'for' ;
WHILE    : 'while' ;
RETURN   : 'return' ;
BREAK    : 'break' ;
GLOBAL   : 'global' ;
WITH     : 'with' ;
AS       : 'as' ;


// Logical Operators
AND      : 'and';
OR       : 'or';
NOT      : 'not';
IN       : 'in';
IS       : 'is';
LAMBDA   : 'lambda';


// Bool Values
TRUE     : 'True' ;
FALSE    : 'False' ;
NONE     : 'None' ;


//  Double-Character Operators
POWER      : '**'; // value = 2 ** 2
FLOORDIV   : '//'; //  value = 2 // 2
WALRUS     : ':='; // if (n := len(data)) > 10




// Identifiers ( app, Flask, next_id, allowed_file, __name__)
ID       : [a-zA-Z_][a-zA-Z_0-9]* ;



//String
STRING
    : '"'  ( ~["\\\r\n] | '\\' . )* '"'
    | '\'' ( ~['\\\r\n] | '\\' . )* '\''
    | '"""' ( . | '\r' | '\n' )*? '"""'
    | '\'\'\'' ( . | '\r' | '\n' )*? '\'\'\''
    ;

// Numbers
FLOAT    : [0-9]+ '.' [0-9]+ ;
INT      : [0-9]+ ;

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

// Comparison Operators
EQEQ     : '==' ;
GT       : '>' ;
LT       : '<' ;
GTE      : '>=' ;
LTE      : '<=' ;
NOTEQ    : '!=';

// Punctuation and decorators
DOT      : '.' ;
COMMA    : ',' ;
COLON    : ':' ;
AT       : '@';

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

// NEWLINE
NEWLINE  : '\r'? '\n' ;

// Whitespace and Comments
WS       : [ \t]+ -> skip;
COMMENT  : '#' ~[\r\n]* -> skip;