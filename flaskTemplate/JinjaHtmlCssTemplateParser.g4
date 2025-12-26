parser grammar JinjaHtmlCssTemplateParser;
options { tokenVocab=JinjaHtmlCssTemplateLexer; }
@header{
package gen;
}

template
    : element*  EOF
    ;

element
    : doctype
    | html
    | jinja2
    ;

jinja2
    :  jinja_statement
    | jinja_block
    ;

jinja_statement
    : TEMPLATE_JINJA_BLOCK_START jinja_stmt_keyword jinja_args* BLOCK_END
    ;
jinja_stmt_keyword
    : BLOCK_EXTENDS
    | BLOCK_INCLUDE
    | BLOCK_IMPORT
    | BLOCK_FROM
    | BLOCK_SET
    ;

jinja_block
    : jinja_if_block
    | jinja_other_block
    | jinja_expr_template
    ;

jinja_if_block
    : jinja_if_start
      jinja_content*
      (jinja_else_block jinja_content*)?
      jinja_if_end
    ;

jinja_else_block
    : TEMPLATE_JINJA_BLOCK_START BLOCK_ELSE BLOCK_END
    ;

jinja_if_start
    : TEMPLATE_JINJA_BLOCK_START BLOCK_IF jinja_args* BLOCK_END
    ;

jinja_if_end
    : TEMPLATE_JINJA_BLOCK_START BLOCK_ENDIF BLOCK_END
    ;

jinja_other_block
    : jinja_block_start jinja_content* jinja_block_end
    ;

jinja_expr_template
    : TEMPLATE_JINJA_EXPR_START jinja_expr* EXPR_END
    ;

jinja_block_start
    : TEMPLATE_JINJA_BLOCK_START jinja_block_keyword jinja_args* BLOCK_END
    ;

jinja_block_keyword
    : BLOCK_BLOCK
    | BLOCK_IF
    | BLOCK_FOR
    | BLOCK_WITH
    ;

jinja_block_end
    : TEMPLATE_JINJA_BLOCK_START jinja_end_keyword BLOCK_END
    ;

jinja_end_keyword
    : BLOCK_ENDBLOCK
    | BLOCK_ENDIF
    | BLOCK_ENDFOR
    | BLOCK_ENDWITH
    ;

jinja_content
    : HTML_TEXT
    | normal_tag
    | self_closing_tag
    | jinja_statement
    | jinja_block
    | jinja_expr_template
    ;

jinja_args
    : BLOCK_ID
    | BLOCK_STRING
    | BLOCK_NUMBER
    | BLOCK_DOT
    | EXPR_STRING
    | EXPR_NUMBER
    | HTML_TEXT
    ;

doctype
    : HTML_DOCTYPE
    ;

html
    : HTML_TAG_OPEN HTML_ID (assignment | HTML_ID)* TAG_CLOSE
      (statments | jinja2 | HTML_TEXT )*
      TEMPLATE_END
    ;

normal_tag
    : HTML_TAG_OPEN HTML_ID (assignment | HTML_ID)* TAG_CLOSE
      (statments* HTML_TAG_OPEN_SELF (assignment | HTML_ID)* TAG_CLOSE)?  // optional

    ;

self_closing_tag
    : HTML_TAG_OPEN VOID_TAG (assignment | HTML_ID)* (SELF_CLOSE_TAG | TAG_CLOSE)
    ;

css_tag
    : CSS_START css_assignment* CSS_TAG_CLOSE css_statements* STYLE_TAG_END
    ;

css_assignment
    : CSS_TAG_ATTR CSS_TAG_EQ css_value*
    ;

css_value
    : CSS_TAG_STRING
    ;

css_statements
    : CSS_CONTENT+ CSS_LBRACE css_statements_values* CSS_RBRACE
    ;

css_statements_values
    : CSS_PROPERTY CSS_COLON (CSS_STRING | CSS_COLOR | CSS_NUMERIC | CSS_KEYWORD)+ CSS_SEMICOLON;

statments
    : self_closing_tag
    | normal_tag
    | css_tag
    | jinja_block
    | HTML_ID
    | HTML_TEXT
    | assignment
    ;

assignment
    : HTML_ID HTML_EQ attribute_value
    | HTML_ID HTML_EQ HTML_QUOTE attr_value_content* ATTR_JINJA_EXPR_START assignment_args* EXPR_END ATTR_VALUE_QUOTE
    ;

assignment_args
    : jinja_args
    | EXPR_LPAREN EXPR_STRING EXPR_RPAREN
    | jinja_expr
    | jinja_expr_template
    | EXPR_EQ
    ;

jinja_expr
    : EXPR_DOT
    | EXPR_ID
    |EXPR_LPAREN
    |EXPR_RPAREN
    |EXPR_LBRACK
    |EXPR_RBRACK
    |EXPR_COMMA
    |EXPR_COLON
    |EXPR_PIPE
    |EXPR_TILDE
    |EXPR_QUESTION
    ;

attribute_value
    : HTML_QUOTE attr_value_content* ATTR_VALUE_QUOTE
    | HTML_APOSTROPHE attr_value_content* ATTR_VALUE_APOSTROPHE
    ;
attr_value_content
    : ATTR_VALUE_ID
    ;
