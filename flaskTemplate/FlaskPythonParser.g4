parser grammar FlaskPythonParser;

options { tokenVocab = FlaskLexer; }

@header { package gen; }


//       Program (Root)

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


//      Imports & Declarations

importStatement
    : IMPORT dottedName (COMMA dottedName)* NEWLINE*                 #importModule
    | FROM dottedName IMPORT (STAR | dottedName (COMMA dottedName)*) NEWLINE* #fromImport
    ;

declaration
    : assignment #declarationStmt
    ;


//          Functions & Decorators

functionDecl
    : decorator* DEF ID LPAREN paramList? RPAREN COLON suite #functionDecleration
    ;

decorator
    : AT dottedName LPAREN argumentList? RPAREN NEWLINE #decoratorRule
    ;

paramList
    : ID (COMMA ID)*
    ;

dottedName
    : ID (DOT ID)*
    ;

argumentList
    : NEWLINE* argument NEWLINE*
      (NEWLINE* COMMA NEWLINE* argument NEWLINE*)*
    ;

argument
    : ID EQ expression  #keywordArgument
    | expression        #positionalArgument
    ;


//           Statements

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
    | NEWLINE           #newLine
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

forStatement
    : FOR ID IN expression COLON suite #forStatementRule
    ;

withStatement
    : WITH expression (AS ID)? COLON suite #withStatementRule
    ;

returnStatement
    : RETURN expression?
    ;

passStatement
    : PASS
    ;

breakStatement
    : BREAK NEWLINE?
    ;

continueStatement
    : CONTINUE NEWLINE?
    ;

globalStatement
    : GLOBAL ID (COMMA ID)* NEWLINE?
    ;


//           Blocks | Suites (Scopes)

suite
    : braceBlock
    | indentBlock
    ;

braceBlock
    : LBRACE NEWLINE* block NEWLINE* RBRACE
    ;

indentBlock
    : NEWLINE* INDENT NEWLINE* block NEWLINE* DEDENT
    ;

block
    : statement+
    ;


//               Expressions
expression
    : logicalOrExpression #expressionRoot
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

unaryExpression
    : (PLUS | MINUS | NOT) unaryExpression #unaryOp
    | primaryExpression                   #simplePrimary
    ;

primaryExpression
    : atom (postfix)*
    ;

postfix
    : LBRACK expression RBRACK     #indexExpr
    | DOT ID                       #attrExpr
    | LPAREN argumentList? RPAREN  #callExpr
    ;


//           Literals

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

dictEntry
    : expression COLON expression
    ;

setLiteral
    : LBRACE NEWLINE*
      (expression NEWLINE* (COMMA NEWLINE* expression)*)?
      NEWLINE* RBRACE
    ;

//      Atom (Leaves)
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
