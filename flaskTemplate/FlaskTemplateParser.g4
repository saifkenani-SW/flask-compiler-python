parser grammar FlaskTemplateParser;

options {
    tokenVocab = FlaskLexer;
}

template
        : (doctype? html NEWLINE*)+ EOF #templateRoot
        ;

doctype: HTML_DOCTYPE;

html: HTML_TAG_OPEN HTML_ID htmlAttributes TAG_CLOSE templateContent TEMPLATE_END       #htmlDocument
    ;

htmlElement
    : openingTag (templateContent)? closingTag      # normalElement
    | voidTag                                     # voidElementTag
    | selfClosingTag                              # selfClosingElementTag
    ;

templateContent
    : contentItem+
    ;
contentItem
    : htmlElement   # htmlContent
    | jinjaBlock    # jinjaBlockContent
    | jinjaExpr     # jinjaExprContent
    | htmlText      # htmlTextContent
    | cssStyle      # cssContent
    | NEWLINE       # newlineContent
    ;




openingTag: HTML_TAG_OPEN HTML_ID htmlAttributes TAG_CLOSE #openingTagNode
          ;

closingTag: HTML_TAG_OPEN_SELF HTML_ID TAG_CLOSE #closingTagNode
          ;

selfClosingTag: HTML_TAG_OPEN HTML_ID htmlAttributes SELF_CLOSE_TAG;

voidTag: HTML_TAG_OPEN VOID_TAG htmlAttributes TAG_CLOSE;

htmlAttributes
            : htmlAttribute*  #htmlAttributeList
            ;

htmlAttribute
    : HTML_ID HTML_EQ htmlAttributeValue     # attributeWithValue
    | HTML_BOOLEAN_ATTR                         # booleanAttribute
    ;


htmlAttributeValue
    : HTML_QUOTE    attrValueContent? ATTR_VALUE_QUOTE     # doubleQuotedValue
    | HTML_APOSTROPHE attrValueContent? ATTR_VALUE_APOSTROPHE  # singleQuotedValue
    ;


attrValueContent
    : attrValueItem+
    ;

attrValueItem
    : ATTR_VALUE_ID   # attrText
    | attrJinjaExpr   # attrJinjaExprItem
    | attrJinjaBlock  # attrJinjaBlockItem
    ;


/*
attrJinjaExpr : ATTR_JINJA_EXPR_START expression EXPR_END ;
*/


attrJinjaExpr
    : ATTR_JINJA_EXPR_START attrJinjaExprContent /*jinjaExpression*/ EXPR_END
    ;
