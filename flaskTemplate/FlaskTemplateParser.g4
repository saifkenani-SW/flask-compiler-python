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
        | global_stmt   // جديد
        | funcCall
        | with_stmt
        | route_stmt
//        | logical_stmt

    ;

//    logical_stmt
//        :
//
//        ;



import_stmt
    : IMPORT ID      #importSimple
    | FROM dotted_ID IMPORT import_list    #importFrom
    ;

dotted_ID
    : ID (DOT ID)*      #dottedID
    ;

    assign_stmt
        : target EQ expr    #assignStatement
            | target PLUSEQ expr                 #plusAssignStatement
            | target MINUSEQ expr                #minusAssignStatement
            | target STAREQ expr                 #timesAssignStatement
            | target SLASHEQ expr                #divideAssignStatement
            | target POWEREQ expr                 #powerAssignStatement
            | target FLOORDIVEQ expr             #floorDivAssignStatement
            | target BITANDEQ expr               #bitAndAssignStatement
            | target BITOREQ expr                #bitOrAssignStatement
        | target  #var
        | target EQ funcCall #funcAssignStatement
        | target EQ funcc #funccAssignStatement

    //    | target EQ comprehension_expr #comprehensionExpr
        ;

comprehension_expr
    : LPAREN comp_for RPAREN COMMA*
    ;

comp_for
    : ID FOR ID IN expr (IF expr)?
    ;

    funcc
        : dotted_ID LPAREN (arglist? | comprehension_expr*) RPAREN;


route_stmt:
    AT funcCall;



    funcCall:
        dotted_ID LPAREN arglist? RPAREN;

        arglist
            : argument (COMMA argument)*     #argumentList
            ;

            argument
                : expr    #positionalArg
                | ID EQ expr    #keywordArg
            //    | ID EQ target  #kdf
                ;
with_stmt
    : WITH funcCall (AS ID)? COLON suite
    ;

import_list
    : ID (COMMA ID)*    #importList
    ;



    global_stmt
        : GLOBAL ID (COMMA ID)*  #globalVariableDeclaration
        ;


target
    : ID   #idTarget
    | ID DOT ID   #attrTarget
    | ID LBRACK expr RBRACK      #subscriptTarget
        | ID DOT ID LBRACK expr RBRACK   #attrSubscriptTarget   // الحل

    | GLOBAL ID   #globalTarget  // omar
    ;
//operation
//    :
//    ;



expr
    : call_expr     #callExpr
    | atom   #atomExpr
    | target #targetExpr
    | expr EQEQ expr #equalOperation
          | expr GT expr #greaterThanOperation
          | expr LT expr #lesserThanOperation
          | expr GTE expr #greaterOrEqualOperation
          | expr LTE expr #lesserOrEqualOperation
          | expr NOTEQ expr #notEqualOperation
          | expr PLUS expr       #addOperation
              | expr MINUS expr      #subOperation
              | expr STAR expr       #mulOperation
              | expr SLASH expr      #divOperation
              | expr PERCENT expr    #modOperation
              | expr AND expr #l
                      | expr OR expr #h
                      | expr IN expr #hgg
                      | expr IS expr #hf
                      | expr NOT expr #hr
                      | expr IS NOT expr #hjj
              | NONE #noneExpr

    ;



call_expr
    : ID LPAREN arglist? RPAREN     #functionCall
    ;





atom
    : ID    #idAtom
    | STRING    #stringAtom
    | (INT|FLOAT)     #numberAtom
    | list_expr  #listAtom
    | dict_expr    #dictAtom
    | set_expr #setAtom
    ;


////////////////////////////////////
// List with suite-like blocks
list_expr
    : LBRACK NEWLINE*
      (expr (COMMA NEWLINE* expr)* COMMA?)?
      NEWLINE* RBRACK
    ;


////////////////////////////////////
// Set with suite-like blocks
////////////////////////////////////


set_expr
    : LBRACE expr (COMMA expr)+ RBRACE
    ;


////////////////////////////////////
// Dict with suite-like blocks
////////////////////////////////////

dict_expr
    : LBRACE NEWLINE*
      (dict_element (COMMA NEWLINE* dict_element)* COMMA?)?
      NEWLINE* RBRACE
    ;


dict_element
    : expr COLON expr
    ;


////////////////////////////////////
// Suite-like block for nested expressions
////////////////////////////////////



funcdef
    : DEF ID LPAREN paramlist? RPAREN COLON suite  #functionDef
    ;
paramlist
    : ID* (COMMA ID)*   #parameterList
    ;

if_stmt
    : IF expr COLON suite elif_clause* else_clause?  #ifStatement
    ;

elif_clause
    : ELIF expr COLON suite  #elseIfStatement
    ;

else_clause
    : ELSE COLON suite  #elseStatement
    ;

for_stmt
    : FOR ID IN expr COLON suite      #forStatement
    ;

suite
    : NEWLINE INDENT statement+ NEWLINE DEDENT      #blockSuite
    ;
return_stmt
    : RETURN expr?    #returnStatement
    ;
expr_stmt
    : call_expr    #expressionStatement
    ;





