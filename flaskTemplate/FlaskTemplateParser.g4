parser grammar FlaskTemplateParser;
options { tokenVocab=FlaskTemplateLexer; }
@header{
package gen;
}

flask
    : (statement | NEWLINE)* EOF   #flaskFile
    ;



statement
    : import_stmt
    | assign_stmt
    | funcdef
    | if_stmt
    | for_stmt
    | return_stmt
    | expr_stmt
    ;

import_stmt
    : IMPORT ID      #importSimple
    | FROM dotted_ID IMPORT import_list    #importFrom
    ;


dotted_ID
    : ID (DOT ID)*      #dottedID
    ;

import_list
    : ID (COMMA ID)*    #importList
    ;

assign_stmt
    : target EQ expr    #assignStatement
    ;

target
    : ID   #idTarget
    | ID DOT ID   #attrTarget
    | ID LBRACK expr RBRACK      #subscriptTarget
    ;

expr
    : call_expr     #callExpr
    | atom   #atomExpr
    ;

call_expr
    : ID LPAREN arglist? RPAREN     #functionCall
    ;

arglist
    : argument (COMMA argument)*     #argumentList
    ;

argument
    : expr    #positionalArg
    | ID EQ expr    #keywordArg
    ;

atom
    : ID    #idAtom
    | STRING    #stringAtom
    | (INT|FLOAT)     #numberAtom
    | list_expr  #listAtom
    | dict_expr    #dictAtom
    ;

list_expr
    : LBRACK (expr (COMMA expr)*)? RBRACK       #listExpression
    ;

dict_expr
    : LBRACE (expr COLON expr (COMMA expr COLON expr)*)? RBRACE    #dictExpression
    ;

funcdef
    : DEF ID LPAREN paramlist? RPAREN COLON suite  #functionDef
    ;
paramlist
    : ID (COMMA ID)*   #parameterList
    ;

if_stmt
    : IF expr COLON suite     #ifStatement
    ;
for_stmt
    : FOR ID IN expr COLON suite      #forStatement
    ;

suite
    : NEWLINE INDENT statement+ DEDENT     #blockSuite
    ;
return_stmt
    : RETURN expr?    #returnStatement
    ;
expr_stmt
    : call_expr    #expressionStatement
    ;

