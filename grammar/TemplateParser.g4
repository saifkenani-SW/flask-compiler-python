parser grammar TemplateParser;

options { tokenVocab = TemplateLexer; }


template
  : templateElement* EOF
  ;

templateElement
  : cssBlock
  | htmlElement
  | jinjaPrint
  | jinjaSetStmt
  | jinjaIfBlock
  | jinjaForBlock
  | HTML_TEXT
  | NEWLINE
  ;


htmlElement
  : LT TAG_NAME TAG_CLOSE
      templateElement*
    LT_SLASH TAG_NAME TAG_CLOSE
  ;


jinjaPrint
  : JPRINT_OPEN exprPrint JPRINT_CLOSE
  ;

exprPrint
  : EXPR_ID
  | EXPR_NUMBER
  | EXPR_STRING
  ;

jinjaSetStmt
  : JSTMT_OPEN BLOCK_SET BLOCK_ID BLOCK_EQ blockExpr JSTMT_CLOSE
  ;


jinjaIfBlock
  : JSTMT_OPEN BLOCK_IF blockExpr JSTMT_CLOSE
      templateElement*
    JSTMT_OPEN BLOCK_ENDIF JSTMT_CLOSE
  ;


jinjaForBlock
  : JSTMT_OPEN BLOCK_FOR BLOCK_ID BLOCK_IN blockExpr JSTMT_CLOSE
      templateElement*
    JSTMT_OPEN BLOCK_ENDFOR JSTMT_CLOSE
  ;

blockExpr
  : blockRel
  ;

blockRel
  : blockAtom ( (BLOCK_EQEQ | BLOCK_NEQ | BLOCK_LT | BLOCK_GT | BLOCK_LTE | BLOCK_GTE) blockAtom )?
  ;

blockAtom
  : BLOCK_ID
  | BLOCK_NUMBER
  | BLOCK_STRING
  | BLOCK_TRUE
  | BLOCK_FALSE
  | BLOCK_NONE
  ;


cssBlock
  : CSS_BLOCK_START cssRule* CSS_BLOCK_END_INNER
  ;

cssRule
  : cssSelector CSS_LBRACE cssDeclaration* CSS_RBRACE
  ;

cssSelector
  : (CSS_DOT | CSS_HASH)? CSS_NAME
  ;


cssDeclaration
  : cssProp CSS_COLON cssValue CSS_SEMI
  ;

cssProp
  : CSS_NAME (CSS_MINUS CSS_NAME)*
  ;

cssValue
  : (CSS_NAME | CSS_NUMBER | CSS_STRING)+
  ;
