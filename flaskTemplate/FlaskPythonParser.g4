parser grammar FlaskPythonParser;

options { tokenVocab = FlaskLexer; }

@header { package gen; }


//   Program

program
    : programItem* EOF #programRoot
    ;
programItem
    : importStatement   #importItem
    | declaration       #declarationItem
    | functionDecl      #functionItem
    | statement         #statementItem
    | NEWLINE           #newlineItem
    ;




importStatement
    : IMPORT dottedName (COMMA dottedName)* NEWLINE*  #importModule
    | FROM dottedName IMPORT (STAR | dottedName (COMMA dottedName)*) NEWLINE*  #fromImport
    ;

declaration
    : assignment #declarationStmt
    ;

//  Functions

functionDecl
    : decorator* DEF ID LPAREN paramList? RPAREN COLON suite #functionDecleration
    ;


decorator
    : AT dottedName LPAREN argumentList? RPAREN NEWLINE #decoratorRule
    ;

dottedName
    : ID (DOT ID)*
    ;
