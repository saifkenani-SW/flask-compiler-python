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


// Identifiers ( app, Flask, next_id, allowed_file, __name__)
ID       : [a-zA-Z_][a-zA-Z_0-9]* ;

