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
attrJinjaExprContent
    : (.)*?
    ;


attrJinjaBlock
    : ATTR_JINJA_BLOCK_START jinjaBlockStatement BLOCK_END
    ;


htmlText: HTML_TEXT;

// Jinja2 Blocks
jinjaBlock:
    TEMPLATE_JINJA_BLOCK_START jinjaBlockStatement BLOCK_END  #jinjaBlockNode
    ;

jinjaBlockStatement:
    BLOCK_EXTENDS BLOCK_STRING                                        # extendsBlock
    | BLOCK_BLOCK BLOCK_ID                                            # blockStart
    | BLOCK_ENDBLOCK                                                  # blockEnd
    | BLOCK_IF blockExpression                                        # ifStart
    | BLOCK_ELIF blockExpression                                      # elifBlock
    | BLOCK_ELSE                                                      # elseBlock
    | BLOCK_ENDIF                                                     # ifEnd
    | BLOCK_FOR BLOCK_ID BLOCK_IN blockExpression                     # forStart
    | BLOCK_ENDFOR                                                    # forEnd
    | BLOCK_SET BLOCK_ID BLOCK_EQ blockExpression                     # setBlock
    | BLOCK_INCLUDE BLOCK_STRING                                      # includeBlock
    | BLOCK_IMPORT BLOCK_STRING (BLOCK_AS BLOCK_ID)?                  # importBlock
    | BLOCK_FROM BLOCK_STRING BLOCK_IMPORT importList                 # fromImportBlock
    | BLOCK_WITH blockExpression                                      # withStart
    | BLOCK_ENDWITH                                                   # withEnd
    | BLOCK_ID (BLOCK_EQ blockExpression)?                            # genericBlock
    ;

blockExpression
    : blockLogicalOrExpression
    ;

blockLogicalOrExpression
    : blockLogicalAndExpression (BLOCK_OR blockLogicalAndExpression)*
    ;

blockLogicalAndExpression
    : blockEqualityExpression (BLOCK_AND blockEqualityExpression)*
    ;

blockEqualityExpression
    : blockComparisonExpression ((BLOCK_EQEQ | BLOCK_NEQ) blockComparisonExpression)*
    ;

blockComparisonExpression
    : blockAdditiveExpression ((BLOCK_LT | BLOCK_LTE | BLOCK_GT | BLOCK_GTE | BLOCK_IN | BLOCK_IS) blockAdditiveExpression)*
    ;

blockAdditiveExpression
    : blockMultiplicativeExpression ((BLOCK_PLUS | BLOCK_MINUS) blockMultiplicativeExpression)*
    ;

blockMultiplicativeExpression
    : blockUnaryExpression ((BLOCK_STAR | BLOCK_SLASH | BLOCK_PERCENT) blockUnaryExpression)*
    ;

blockUnaryExpression
    : (BLOCK_PLUS | BLOCK_MINUS | BLOCK_NOT) blockUnaryExpression #blockUnaryOp
    | blockPrimaryExpression                                      #blockUnaryBase
    ;


blockPrimaryExpression
    : blockAtom (blockPostfix)*  #blockPrimary
    ;

blockAtom
    : BLOCK_LPAREN blockExpression BLOCK_RPAREN         #blockParenExpr
    | BLOCK_ID                                         #blockIdentifier
    | BLOCK_STRING                                     #blockStringLiteral
    | BLOCK_NUMBER                                     #blockNumberLiteral
    | BLOCK_TRUE                                       #blockTrueLiteral
    | BLOCK_FALSE                                      #blockFalseLiteral
    | BLOCK_NONE                                       #blockNoneLiteral
    | BLOCK_LBRACK blockExpressionList? BLOCK_RBRACK   #blockListLiteral
    | BLOCK_LBRACE blockDictPairList? BLOCK_RBRACE     #blockDictLiteral
    ;

blockPostfix
    : BLOCK_PIPE BLOCK_ID blockArgumentList?  #blockFilterOp
    | BLOCK_LPAREN blockArgumentList? BLOCK_RPAREN   #blockCallOp
    | BLOCK_DOT BLOCK_ID                           #blockMemberOp
    ;

blockArgumentList
    : blockExpression (BLOCK_COMMA blockExpression)*
    ;

blockExpressionList
    : blockExpression (BLOCK_COMMA blockExpression)*
    ;

blockDictPairList
    : blockDictPair (BLOCK_COMMA blockDictPair)*
    ;

blockDictPair
    : BLOCK_STRING BLOCK_COLON blockExpression
    ;

importList: BLOCK_ID (BLOCK_COMMA BLOCK_ID)*;

// Jinja2 Expressions
jinjaExpr:
    TEMPLATE_JINJA_EXPR_START jinjaExpression EXPR_END
    ;
