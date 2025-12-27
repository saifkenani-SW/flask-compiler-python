parser grammar FlaskPythonParser;

options { tokenVocab = FlaskLexer; }

@header { package gen; }


//   Program

program
    : programItem* EOF #programRoot
    ;
