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
