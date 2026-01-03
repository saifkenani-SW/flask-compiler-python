parser grammar FlaskTemplateParser;

options {
    tokenVocab = FlaskLexer;
}

@header { package gen; }

template
    : doctype? html NEWLINE* EOF #templateRoot
    ;


doctype: HTML_DOCTYPE;

html: HTML_TAG_OPEN HTML_ID htmlAttributes TAG_CLOSE templateContent TEMPLATE_END #htmlDocument
    ;

htmlElement
    : openingTag (templateContent)? closingTag      # normalElement
    | voidTag                                     # voidElementTag
    | selfClosingTag                              # selfClosingElementTag
    ;

templateContent
    : contentItem*
    ;

contentItem
    : htmlElement   # htmlContent
    | jinjaBlock    # jinjaBlockContent
    | jinjaExpr     # jinjaExprContent
    | htmlText      # htmlTextContent
    | cssStyle      # cssContent
    | NEWLINE       # newlineContent
    ;

// ============ HTML ============
openingTag: HTML_TAG_OPEN HTML_ID htmlAttributes TAG_CLOSE #openingTagNode
          ;

closingTag: HTML_TAG_OPEN_SELF HTML_ID TAG_CLOSE #closingTagNode
          ;

selfClosingTag: HTML_TAG_OPEN HTML_ID htmlAttributes SELF_CLOSE_TAG #selfClosingTagNode;

voidTag: HTML_TAG_OPEN VOID_TAG htmlAttributes TAG_CLOSE #voidTagNode;

htmlAttributes
    : htmlAttribute*  #htmlAttributeList
    ;

htmlAttribute
    : HTML_ID HTML_EQ htmlAttributeValue     # attributeWithValue
    | HTML_BOOLEAN_ATTR                      # booleanAttribute
    ;

htmlAttributeValue
    : HTML_QUOTE attrValueContent? ATTR_VALUE_QUOTE # doubleQuotedValue
    ;

attrValueContent
    : attrValueItem+
    ;

attrValueItem
    : ATTR_VALUE_ID       # attrText
    | attrJinjaExpr       # attrJinjaExprItem
    | attrJinjaBlock      # attrJinjaBlockItem
    ;

attrJinjaExpr
    : ATTR_JINJA_EXPR_START attrJinjaExprContent EXPR_END
    ;

attrJinjaExprContent
    : jinjaExpression
    ;

attrJinjaBlock
    : ATTR_JINJA_BLOCK_START jinjaBlockStatement BLOCK_END
    ;

htmlText
    : HTML_TEXT+
    ;

// ============ JINJA2 BLOCKS ============
jinjaBlock
    : TEMPLATE_JINJA_BLOCK_START jinjaBlockStatement BLOCK_END #jinjaBlockNode
    ;

jinjaBlockStatement
    : BLOCK_IF blockExpression templateContent?             # ifStart
    | BLOCK_ELIF blockExpression templateContent? BLOCK_ENDIF                      # elifBlock
    | BLOCK_ELSE templateContent?                                      # elseBlock
    | BLOCK_FOR BLOCK_ID BLOCK_IN blockExpression templateContent?  # forStart
    | BLOCK_BLOCK BLOCK_ID templateContent?             # blockStart
    | BLOCK_SET BLOCK_ID BLOCK_EQ blockExpression                     # setBlock
    | BLOCK_INCLUDE BLOCK_STRING                                      # includeBlock
    | BLOCK_IMPORT BLOCK_STRING (BLOCK_AS BLOCK_ID)?                  # importBlock
    | BLOCK_FROM BLOCK_STRING BLOCK_IMPORT importList                 # fromImportBlock
    | BLOCK_WITH blockExpression templateContent? BLOCK_ENDWITH       # withStart
    | BLOCK_EXTENDS BLOCK_STRING                                      # extendsBlock
    | BLOCK_ID (BLOCK_EQ blockExpression)? templateContent?           # genericBlock
    | BLOCK_MACRO BLOCK_ID BLOCK_LPAREN macroParameters? BLOCK_RPAREN templateContent? BLOCK_ENDMACRO  # macroBlock
    | BLOCK_ENDBLOCK  #endblock
    | BLOCK_ENDIF  #endIf
    | BLOCK_ENDFOR #endFor
    ;

    macroParameters
        : BLOCK_ID (BLOCK_COMMA BLOCK_ID)*
        ;

// ============ BLOCK EXPRESSIONS ============
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
    : blockAtom (blockPostfix)* #blockPrimary
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
    : BLOCK_PIPE BLOCK_ID blockArgumentList? #blockFilterOp
    | BLOCK_LPAREN blockArgumentList? BLOCK_RPAREN #blockCallOp
    | BLOCK_DOT BLOCK_ID #blockMemberOp
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
    : blockExpression BLOCK_COLON blockExpression
    ;

importList: BLOCK_ID (BLOCK_COMMA BLOCK_ID)*;

// ============ JINJA2 EXPRESSIONS ============
jinjaExpr
    : TEMPLATE_JINJA_EXPR_START jinjaExpression* EXPR_END #jinjaExprNode
    ;

jinjaExpression
    : expression
    ;

expression
    : logicalOrExpression #expressionRoot
    ;

logicalOrExpression
    : logicalAndExpression (EXPR_OR logicalAndExpression)*
    ;

logicalAndExpression
    : equalityExpression (EXPR_AND equalityExpression)*
    ;

equalityExpression
    : comparisonExpression ((EXPR_EQEQ | EXPR_NEQ) comparisonExpression)*
    ;

comparisonExpression
    : additiveExpression ((EXPR_LT | EXPR_LTE | EXPR_GT | EXPR_GTE | EXPR_IN | EXPR_IS) additiveExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((EXPR_PLUS | EXPR_MINUS) multiplicativeExpression)*
    ;

multiplicativeExpression
    : unaryExpression ((EXPR_STAR | EXPR_SLASH | EXPR_PERCENT | EXPR_FLOORDIV) unaryExpression)*
    ;

unaryExpression
    : (EXPR_PLUS | EXPR_MINUS | EXPR_NOT)? primaryExpression #unaryExpr
    ;

primaryExpression
    : EXPR_LPAREN expression EXPR_RPAREN                     # parenExpr
    | EXPR_ID (EXPR_DOT EXPR_ID)*                            # identifierExpr
    | EXPR_ID EXPR_LPAREN argumentList? EXPR_RPAREN          # callExpr
    | EXPR_STRING                                            # stringExpr
    | EXPR_NUMBER                                            # numberExpr
    | EXPR_TRUE                                              # trueExpr
    | EXPR_FALSE                                             # falseExpr
    | EXPR_NONE                                              # noneExpr
    | EXPR_LBRACK expressionList? EXPR_RBRACK                # listExpr
    | EXPR_LBRACE dictPairList? EXPR_RBRACE                  # dictExpr
    | primaryExpression EXPR_PIPE EXPR_ID argumentList?      # filterExpr
    ;

argumentList
    : expression (EXPR_COMMA expression)* (EXPR_EQ expression)* #argList
    ;

expressionList
    : expression (EXPR_COMMA expression)* #exprList
    ;

dictPairList
    : dictPair (EXPR_COMMA dictPair)* #dictPairListNode
    ;

dictPair
    : expression EXPR_COLON expression #dictPairNode
    ;

// ============ CSS ============
cssStyle
    : CSS_START cssTagAttributes? CSS_TAG_CLOSE cssStyleContent STYLE_TAG_END # styleWithAttributes
    ;

cssTagAttributes
    : cssTagAttribute*
    ;

cssTagAttribute
    : CSS_TAG_ATTR CSS_TAG_EQ CSS_TAG_STRING #cssTagAttrNode
    ;

cssStyleContent
    : cssRule*
    ;

cssRule
    : cssSelectors CSS_CONTENT* CSS_LBRACE cssDeclarations CSS_RBRACE #cssRuleNode
    ;

cssSelectors
    : cssSelector (CSS_COMMA cssSelector)* #cssSelectorExpr
    ;

cssSelector
    : CSS_CONTENT (CSS_DOT CSS_CONTENT)* #cssSelectorNode
    ;


cssDeclarations
    : cssDeclaration* #cssDeclarationList
    ;

cssDeclaration
    : CSS_PROPERTY CSS_COLON cssValues CSS_SEMICOLON #cssDeclarationNode
    ;

cssValues
    : cssValue (CSS_COMMA? cssValue)* #cssValueList
    ;

cssValue
    : CSS_STRING     #cssStringValue
    | CSS_NUMERIC    #cssNumericValue
    | CSS_COLOR      #cssColorValue
    | CSS_KEYWORD    #cssKeywordValue
    ;