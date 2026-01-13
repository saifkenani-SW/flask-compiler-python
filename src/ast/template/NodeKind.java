package ast.template;

public enum NodeKind {
    TEMPLATE_ROOT, //done

    // HTML
    DOCTYPE, //done
    HTML_DOCUMENT, //done
    HTML_ATTRIBUTE, //done
    HTML_ELEMENT, //done
    HTML_ATTRIBUTE_TEXT,

//    HTML_OPENING_TAG,

    HTML_VOID_ELEMENT, // done
    HTML_SELF_CLOSING_ELEMENT, //done
    HTML_CLOSING_TAG, //done
    HTML_TEXT, //done

    // CSS
    CSS_STYLE, //done
    CSS_ATTRIBUTE, //done
    CSS_RULE, //done
    CSS_DECLARATION, //done
    CSS_SELECTOR, //done
    CSS_COLOR, //done
    CSS_STRING, //done
    CSS_NUMERIC, //done
    CSS_KEYWORD, //done


    // Jinja
    JINJA_ELIF_BLOCK, //done
    JINJA_BLOCK_BLOCK, //done
    JINJA_ELSE_BLOCK, //done
    JINJA_EXTENDS_BLOCK, //done
    JINJA_FOR_BLOCK, //done
    JINJA_FROM_IMPORT_BLOCK, //DONE
    JINJA_GENERIC_BLOCK, //done
    JINJA_IF_BLOCK, //done
    JINJA_IMPORT_BLOCK, //done
    JINJA_INCLUDE_BLOCK, //done
    JINJA_WITH_BLOCK, //done
    JINJA_SET_BLOCK, //done

    JINJA_EXPR_ATTRIBUTE_ACCESS, //done
    JINJA_EXPR_BINARY, //done
    JINJA_EXPR_CALL, //done
    JINJA_EXPR_DICT, //done
    JINJA_EXPR_FILTER, //done
    JINJA_EXPR_EXPRESSION, //done
    JINJA_EXPR_LIST, //done
    JINJA_EXPR_UNARY, //done
    JINJA_EXPR_VARIABLE, //done

    JINJA_LITERAL_BOOLEAN, //done
    JINJA_LITERAL_NONE, //done
    JINJA_LITERAL_NUMBER, //done
    JINJA_LITERAL_STRING, //done
}
