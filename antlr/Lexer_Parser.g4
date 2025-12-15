grammar Lexer_Parser;

@header{
package gen;
}

//    Identifiers

ID: [a-zA-Z][a-zA-Z_0-9-.$]*;
WS: [ \t\r\n]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;
SPACE: ' ';