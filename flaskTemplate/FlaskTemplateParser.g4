parser grammar FlaskTemplateParser;
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