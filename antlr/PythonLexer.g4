lexer grammar PythonLexer;

@header{
package gen;
}
@lexer::superClass {
    PythonBasicLexerBase
}

tokens { INDENT, DEDENT }


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
LAMBDA   : 'lambda'; // Comprehensive coverage



