parser grammar PythonParser;
options { tokenVocab=FlaskTemplateLexer; }
@header{
package gen;
}

program:progs*EOF;

progs
     :python
     |html
     |css
     ;