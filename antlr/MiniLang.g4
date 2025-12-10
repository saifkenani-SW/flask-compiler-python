grammar MiniLang;
// ---------- Lexer Rules ----------


@header{
package gen;
}

ID  : [a-zA-Z_][a-zA-Z_0-9]* ;
INT : [0-9]+ ;

WS      : [ \t\r\n]+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;

// ---------- Parser Rules ----------
program
        :state*EOF
        ;

state
     :'print'expr';'         # PrintStat
     |ID '='expr';'          # AssignStat
     ;

expr
    :expr op=('*'|'/') expr      # MulDiv
    |expr op=('+'|'-') expr     # AddSub
    |INT                        # Int
    |ID                         # Var
    |'('expr')'                  # Parens
    ;