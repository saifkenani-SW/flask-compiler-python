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
