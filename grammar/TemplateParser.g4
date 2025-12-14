parser grammar TemplateParser;

options { tokenVocab=TemplateLexer; }


document
  : block EOF
  ;

block
  : node*
  ;

node
  : htmlElement
  | jinjaPrint
  | jinjaStmt
  | cssBlock
  | TEXT
  ;

htmlElement
  : openTag htmlContent closeTag
  ;

openTag
  : LT NAME GT
  ;

closeTag
  : LT_SLASH NAME GT
  ;


htmlContent
  : ( {self._input.LA(1) != self.LT_SLASH}? htmlNode )*
  ;

htmlNode
  : htmlElement
  | jinjaPrint
  | jinjaStmt
  | TEXT
  ;


jinjaPrint
  : JPRINT_OPEN expr JPRINT_CLOSE
  ;


jinjaStmt
  : jinjaIf
  | jinjaFor
  | jinjaSet
  ;

jinjaIf
  : JSTMT_OPEN IF expr JSTMT_CLOSE
    block
    JSTMT_OPEN ENDIF JSTMT_CLOSE
  ;

jinjaFor
  : JSTMT_OPEN FOR NAME IN expr JSTMT_CLOSE
    block
    JSTMT_OPEN ENDFOR JSTMT_CLOSE
  ;

jinjaSet
  : JSTMT_OPEN SET NAME ASSIGN expr JSTMT_CLOSE
  ;


cssBlock
  : CSS_BLOCK_START
    cssRule*
    CSS_BLOCK_END
  ;

cssRule
  : selector LBRACE cssDecl* RBRACE
  ;

selector
  : DOT NAME
  | HASH NAME
  | NAME
  ;

cssDecl
  : cssProp COLON cssValue SEMI
  ;

cssProp
  : NAME (MINUS NAME)*
  ;

cssValue
  : NUMBER NAME?   // 20px أو 20
  | NAME
  | STRING
  ;


expr : orExpr ;

orExpr  : andExpr (OR andExpr)* ;
andExpr : eqExpr (AND eqExpr)* ;
eqExpr  : relExpr ((EQ | NEQ) relExpr)* ;
relExpr : addExpr ((LT_OP | GT_OP | LE | GE) addExpr)* ;
addExpr : mulExpr ((PLUS | MINUS) mulExpr)* ;
mulExpr : unaryExpr ((STAR | DIV) unaryExpr)* ;

unaryExpr
  : (NOT | MINUS) unaryExpr
  | primary
  ;

primary
  : NUMBER
  | STRING
  | NAME
  | LPAREN expr RPAREN
  ;
