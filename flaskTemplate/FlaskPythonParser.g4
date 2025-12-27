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

argumentList
    : NEWLINE* argument NEWLINE*  (NEWLINE* COMMA NEWLINE* argument NEWLINE* )*
    ;

argument
    : ID EQ expression  #keywordArgument
    | expression        #positionalArgument
    ;

paramList
    : ID (COMMA ID)*
    ;

// Expressions


/*
expression
    : expression OR expression
    | expression AND expression
    | NOT expression
    | expression (EQEQ | NOTEQ | GT | LT | LTEQ | GTEQ | IN | IS) expression
    | expression (PLUS | MINUS) expression
    | expression (STAR | SLASH | PERCENT) expression
    | primary
    ;*/

//
expression
    : logicalOrExpression  #expressionRoot
    ;

logicalOrExpression
    : logicalAndExpression (OR logicalAndExpression)*
    ;

logicalAndExpression
    : equalityExpression (AND equalityExpression)*
    ;

equalityExpression
    : comparisonExpression ((EQEQ | NOTEQ) comparisonExpression)*
    ;

comparisonExpression
    : additiveExpression ((LT | LTEQ | GT | GTEQ | IN | IS) additiveExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((PLUS | MINUS) multiplicativeExpression)*
    ;

multiplicativeExpression
    : unaryExpression ((STAR | SLASH | PERCENT) unaryExpression)*
    ;

/*unaryExpression
    : (PLUS | MINUS | NOT)+ primaryExpression   #multipleUnary
    | primaryExpression                         #simplePrimary
    ;*/
unaryExpression
    : (PLUS | MINUS | NOT) unaryExpression    #unaryOp
    | primaryExpression                       #simplePrimary
    ;

primaryExpression
    : atom (postfix)*
    ;



postfix
    : LBRACK expression RBRACK     #indexExpr
    | DOT ID                       #attrExpr
    | LPAREN argumentList? RPAREN  #callExpr
    ;

// Atom
atom
    : ID                       #idAtom
    | STRING                   #stringAtom
    | INT                      #intAtom
    | FLOAT                    #floatAtom
    | TRUE                     #trueAtom
    | FALSE                    #falseAtom
    | NONE                     #noneAtom
    | listLiteral              #listAtom
    | dictLiteral              #dictAtom
    | setLiteral               #setAtom
    | LPAREN expression RPAREN #parenAtom
    ;


// ----------- list / dict ------------------------

listLiteral
  : LBRACK NEWLINE*
      (expression (COMMA NEWLINE* expression)*)?
    NEWLINE* RBRACK
  ;

dictLiteral
  : LBRACE NEWLINE*
       (dictEntry (COMMA NEWLINE* dictEntry)*)?
    NEWLINE* RBRACE
  ;


dictEntry: expression COLON expression;

setLiteral
  : LBRACE NEWLINE*
        (expression NEWLINE* (COMMA NEWLINE* expression)*)?
    NEWLINE* RBRACE
  ;


//    Statements

statement
    : assignment        #assignmentStmt
    | exprStatement     #exprStmt
    | ifStatement       #ifStmt
    | forStatement      #forStmt
    | returnStatement   #returnStmt
    | passStatement     #passStmt
    | breakStatement    #breakStmt
    | continueStatement #continueStmt
    | globalStatement   #globalStmt
    | withStatement     #withStmt
    ;

breakStatement
    : BREAK NEWLINE?
    ;
continueStatement
    : CONTINUE NEWLINE?
    ;



assignment
    : leftHandSide NEWLINE* assignOp NEWLINE* expression NEWLINE* #assignmentRule
    ;


assignOp
    : EQ
    | PLUSEQ
    | MINUSEQ
    | STAREQ
    | SLASHEQ
    | POWEREQ
    | FLOORDIVEQ
    | BITANDEQ
    | BITOREQ
    ;

leftHandSide
    : primaryExpression
    ;

exprStatement
    : expression
    ;

ifStatement
    : IF expression COLON suite
      (ELIF expression COLON suite)*
      (ELSE COLON suite)?
      #ifStatementRule
    ;
