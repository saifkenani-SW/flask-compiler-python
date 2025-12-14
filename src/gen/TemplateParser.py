# Generated from grammar/TemplateParser.g4 by ANTLR 4.13.2
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO

def serializedATN():
    return [
        4,1,81,245,2,0,7,0,2,1,7,1,2,2,7,2,2,3,7,3,2,4,7,4,2,5,7,5,2,6,7,
        6,2,7,7,7,2,8,7,8,2,9,7,9,2,10,7,10,2,11,7,11,2,12,7,12,2,13,7,13,
        2,14,7,14,2,15,7,15,2,16,7,16,2,17,7,17,2,18,7,18,2,19,7,19,2,20,
        7,20,2,21,7,21,2,22,7,22,2,23,7,23,2,24,7,24,2,25,7,25,2,26,7,26,
        2,27,7,27,1,0,1,0,1,0,1,1,5,1,61,8,1,10,1,12,1,64,9,1,1,2,1,2,1,
        2,1,2,1,2,3,2,71,8,2,1,3,1,3,1,3,1,3,1,4,1,4,1,4,1,4,1,5,1,5,1,5,
        1,5,1,6,1,6,5,6,87,8,6,10,6,12,6,90,9,6,1,7,1,7,1,7,1,7,3,7,96,8,
        7,1,8,1,8,1,8,1,8,1,9,1,9,1,9,3,9,105,8,9,1,10,1,10,1,10,1,10,1,
        10,1,10,1,10,1,10,1,10,1,11,1,11,1,11,1,11,1,11,1,11,1,11,1,11,1,
        11,1,11,1,11,1,12,1,12,1,12,1,12,1,12,1,12,1,12,1,13,1,13,5,13,136,
        8,13,10,13,12,13,139,9,13,1,13,1,13,1,14,1,14,1,14,5,14,146,8,14,
        10,14,12,14,149,9,14,1,14,1,14,1,15,1,15,1,15,1,15,1,15,3,15,158,
        8,15,1,16,1,16,1,16,1,16,1,16,1,17,1,17,1,17,5,17,168,8,17,10,17,
        12,17,171,9,17,1,18,1,18,3,18,175,8,18,1,18,1,18,3,18,179,8,18,1,
        19,1,19,1,20,1,20,1,20,5,20,186,8,20,10,20,12,20,189,9,20,1,21,1,
        21,1,21,5,21,194,8,21,10,21,12,21,197,9,21,1,22,1,22,1,22,5,22,202,
        8,22,10,22,12,22,205,9,22,1,23,1,23,1,23,5,23,210,8,23,10,23,12,
        23,213,9,23,1,24,1,24,1,24,5,24,218,8,24,10,24,12,24,221,9,24,1,
        25,1,25,1,25,5,25,226,8,25,10,25,12,25,229,9,25,1,26,1,26,1,26,3,
        26,234,8,26,1,27,1,27,1,27,1,27,1,27,1,27,1,27,3,27,243,8,27,1,27,
        0,0,28,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,
        42,44,46,48,50,52,54,0,5,1,0,18,19,1,0,20,23,1,0,24,25,1,0,26,27,
        2,0,25,25,28,28,245,0,56,1,0,0,0,2,62,1,0,0,0,4,70,1,0,0,0,6,72,
        1,0,0,0,8,76,1,0,0,0,10,80,1,0,0,0,12,88,1,0,0,0,14,95,1,0,0,0,16,
        97,1,0,0,0,18,104,1,0,0,0,20,106,1,0,0,0,22,115,1,0,0,0,24,126,1,
        0,0,0,26,133,1,0,0,0,28,142,1,0,0,0,30,157,1,0,0,0,32,159,1,0,0,
        0,34,164,1,0,0,0,36,178,1,0,0,0,38,180,1,0,0,0,40,182,1,0,0,0,42,
        190,1,0,0,0,44,198,1,0,0,0,46,206,1,0,0,0,48,214,1,0,0,0,50,222,
        1,0,0,0,52,233,1,0,0,0,54,242,1,0,0,0,56,57,3,2,1,0,57,58,5,0,0,
        1,58,1,1,0,0,0,59,61,3,4,2,0,60,59,1,0,0,0,61,64,1,0,0,0,62,60,1,
        0,0,0,62,63,1,0,0,0,63,3,1,0,0,0,64,62,1,0,0,0,65,71,3,6,3,0,66,
        71,3,16,8,0,67,71,3,18,9,0,68,71,3,26,13,0,69,71,5,42,0,0,70,65,
        1,0,0,0,70,66,1,0,0,0,70,67,1,0,0,0,70,68,1,0,0,0,70,69,1,0,0,0,
        71,5,1,0,0,0,72,73,3,8,4,0,73,74,3,12,6,0,74,75,3,10,5,0,75,7,1,
        0,0,0,76,77,5,7,0,0,77,78,5,39,0,0,78,79,5,9,0,0,79,9,1,0,0,0,80,
        81,5,8,0,0,81,82,5,39,0,0,82,83,5,9,0,0,83,11,1,0,0,0,84,85,4,6,
        0,0,85,87,3,14,7,0,86,84,1,0,0,0,87,90,1,0,0,0,88,86,1,0,0,0,88,
        89,1,0,0,0,89,13,1,0,0,0,90,88,1,0,0,0,91,96,3,6,3,0,92,96,3,16,
        8,0,93,96,3,18,9,0,94,96,5,42,0,0,95,91,1,0,0,0,95,92,1,0,0,0,95,
        93,1,0,0,0,95,94,1,0,0,0,96,15,1,0,0,0,97,98,5,1,0,0,98,99,3,38,
        19,0,99,100,5,2,0,0,100,17,1,0,0,0,101,105,3,20,10,0,102,105,3,22,
        11,0,103,105,3,24,12,0,104,101,1,0,0,0,104,102,1,0,0,0,104,103,1,
        0,0,0,105,19,1,0,0,0,106,107,5,3,0,0,107,108,5,11,0,0,108,109,3,
        38,19,0,109,110,5,4,0,0,110,111,3,2,1,0,111,112,5,3,0,0,112,113,
        5,12,0,0,113,114,5,4,0,0,114,21,1,0,0,0,115,116,5,3,0,0,116,117,
        5,13,0,0,117,118,5,39,0,0,118,119,5,15,0,0,119,120,3,38,19,0,120,
        121,5,4,0,0,121,122,3,2,1,0,122,123,5,3,0,0,123,124,5,14,0,0,124,
        125,5,4,0,0,125,23,1,0,0,0,126,127,5,3,0,0,127,128,5,16,0,0,128,
        129,5,39,0,0,129,130,5,17,0,0,130,131,3,38,19,0,131,132,5,4,0,0,
        132,25,1,0,0,0,133,137,5,5,0,0,134,136,3,28,14,0,135,134,1,0,0,0,
        136,139,1,0,0,0,137,135,1,0,0,0,137,138,1,0,0,0,138,140,1,0,0,0,
        139,137,1,0,0,0,140,141,5,6,0,0,141,27,1,0,0,0,142,143,3,30,15,0,
        143,147,5,31,0,0,144,146,3,32,16,0,145,144,1,0,0,0,146,149,1,0,0,
        0,147,145,1,0,0,0,147,148,1,0,0,0,148,150,1,0,0,0,149,147,1,0,0,
        0,150,151,5,32,0,0,151,29,1,0,0,0,152,153,5,35,0,0,153,158,5,39,
        0,0,154,155,5,36,0,0,155,158,5,39,0,0,156,158,5,39,0,0,157,152,1,
        0,0,0,157,154,1,0,0,0,157,156,1,0,0,0,158,31,1,0,0,0,159,160,3,34,
        17,0,160,161,5,33,0,0,161,162,3,36,18,0,162,163,5,34,0,0,163,33,
        1,0,0,0,164,169,5,39,0,0,165,166,5,25,0,0,166,168,5,39,0,0,167,165,
        1,0,0,0,168,171,1,0,0,0,169,167,1,0,0,0,169,170,1,0,0,0,170,35,1,
        0,0,0,171,169,1,0,0,0,172,174,5,40,0,0,173,175,5,39,0,0,174,173,
        1,0,0,0,174,175,1,0,0,0,175,179,1,0,0,0,176,179,5,39,0,0,177,179,
        5,41,0,0,178,172,1,0,0,0,178,176,1,0,0,0,178,177,1,0,0,0,179,37,
        1,0,0,0,180,181,3,40,20,0,181,39,1,0,0,0,182,187,3,42,21,0,183,184,
        5,30,0,0,184,186,3,42,21,0,185,183,1,0,0,0,186,189,1,0,0,0,187,185,
        1,0,0,0,187,188,1,0,0,0,188,41,1,0,0,0,189,187,1,0,0,0,190,195,3,
        44,22,0,191,192,5,29,0,0,192,194,3,44,22,0,193,191,1,0,0,0,194,197,
        1,0,0,0,195,193,1,0,0,0,195,196,1,0,0,0,196,43,1,0,0,0,197,195,1,
        0,0,0,198,203,3,46,23,0,199,200,7,0,0,0,200,202,3,46,23,0,201,199,
        1,0,0,0,202,205,1,0,0,0,203,201,1,0,0,0,203,204,1,0,0,0,204,45,1,
        0,0,0,205,203,1,0,0,0,206,211,3,48,24,0,207,208,7,1,0,0,208,210,
        3,48,24,0,209,207,1,0,0,0,210,213,1,0,0,0,211,209,1,0,0,0,211,212,
        1,0,0,0,212,47,1,0,0,0,213,211,1,0,0,0,214,219,3,50,25,0,215,216,
        7,2,0,0,216,218,3,50,25,0,217,215,1,0,0,0,218,221,1,0,0,0,219,217,
        1,0,0,0,219,220,1,0,0,0,220,49,1,0,0,0,221,219,1,0,0,0,222,227,3,
        52,26,0,223,224,7,3,0,0,224,226,3,52,26,0,225,223,1,0,0,0,226,229,
        1,0,0,0,227,225,1,0,0,0,227,228,1,0,0,0,228,51,1,0,0,0,229,227,1,
        0,0,0,230,231,7,4,0,0,231,234,3,52,26,0,232,234,3,54,27,0,233,230,
        1,0,0,0,233,232,1,0,0,0,234,53,1,0,0,0,235,243,5,40,0,0,236,243,
        5,41,0,0,237,243,5,39,0,0,238,239,5,37,0,0,239,240,3,38,19,0,240,
        241,5,38,0,0,241,243,1,0,0,0,242,235,1,0,0,0,242,236,1,0,0,0,242,
        237,1,0,0,0,242,238,1,0,0,0,243,55,1,0,0,0,19,62,70,88,95,104,137,
        147,157,169,174,178,187,195,203,211,219,227,233,242
    ]

class TemplateParser ( Parser ):

    grammarFileName = "TemplateParser.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "'}}'", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "'%}'", "'if'", 
                     "'endif'", "'for'", "'endfor'", "'in'", "'set'", "'='", 
                     "'.'", "'#'", "'{'", "'}'", "':'", "';'" ]

    symbolicNames = [ "<INVALID>", "JPRINT_OPEN", "JPRINT_CLOSE", "JSTMT_OPEN", 
                      "JSTMT_CLOSE", "CSS_BLOCK_START", "CSS_BLOCK_END", 
                      "LT", "LT_SLASH", "GT", "SLASH", "IF", "ENDIF", "FOR", 
                      "ENDFOR", "IN", "SET", "ASSIGN", "EQ", "NEQ", "LE", 
                      "GE", "LT_OP", "GT_OP", "PLUS", "MINUS", "STAR", "DIV", 
                      "NOT", "AND", "OR", "LBRACE", "RBRACE", "COLON", "SEMI", 
                      "DOT", "HASH", "LPAREN", "RPAREN", "NAME", "NUMBER", 
                      "STRING", "TEXT", "WS_R", "TAG_WS", "ERR_TAG", "EXPR_WS", 
                      "ERR_EXPR", "STMT_WS", "ERR_STMT", "CSS_WS", "ERR_CSS", 
                      "GT_R", "SLASH_R", "JPRINT_CLOSE_R", "LPAREN_R", "RPAREN_R", 
                      "EQ_R", "NEQ_R", "LE_R", "GE_R", "LTOP_R", "PLUS_R", 
                      "MINUS_R", "STAR_R", "NOT_R", "AND_R", "OR_R", "JSTMT_CLOSE_R", 
                      "IF_R", "ENDIF_R", "FOR_R", "ENDFOR_R", "IN_R", "SET_R", 
                      "ASSIGN_R", "DOT_R", "HASH_R", "LBRACE_R", "RBRACE_R", 
                      "COLON_R", "SEMI_R" ]

    RULE_document = 0
    RULE_block = 1
    RULE_node = 2
    RULE_htmlElement = 3
    RULE_openTag = 4
    RULE_closeTag = 5
    RULE_htmlContent = 6
    RULE_htmlNode = 7
    RULE_jinjaPrint = 8
    RULE_jinjaStmt = 9
    RULE_jinjaIf = 10
    RULE_jinjaFor = 11
    RULE_jinjaSet = 12
    RULE_cssBlock = 13
    RULE_cssRule = 14
    RULE_selector = 15
    RULE_cssDecl = 16
    RULE_cssProp = 17
    RULE_cssValue = 18
    RULE_expr = 19
    RULE_orExpr = 20
    RULE_andExpr = 21
    RULE_eqExpr = 22
    RULE_relExpr = 23
    RULE_addExpr = 24
    RULE_mulExpr = 25
    RULE_unaryExpr = 26
    RULE_primary = 27

    ruleNames =  [ "document", "block", "node", "htmlElement", "openTag", 
                   "closeTag", "htmlContent", "htmlNode", "jinjaPrint", 
                   "jinjaStmt", "jinjaIf", "jinjaFor", "jinjaSet", "cssBlock", 
                   "cssRule", "selector", "cssDecl", "cssProp", "cssValue", 
                   "expr", "orExpr", "andExpr", "eqExpr", "relExpr", "addExpr", 
                   "mulExpr", "unaryExpr", "primary" ]

    EOF = Token.EOF
    JPRINT_OPEN=1
    JPRINT_CLOSE=2
    JSTMT_OPEN=3
    JSTMT_CLOSE=4
    CSS_BLOCK_START=5
    CSS_BLOCK_END=6
    LT=7
    LT_SLASH=8
    GT=9
    SLASH=10
    IF=11
    ENDIF=12
    FOR=13
    ENDFOR=14
    IN=15
    SET=16
    ASSIGN=17
    EQ=18
    NEQ=19
    LE=20
    GE=21
    LT_OP=22
    GT_OP=23
    PLUS=24
    MINUS=25
    STAR=26
    DIV=27
    NOT=28
    AND=29
    OR=30
    LBRACE=31
    RBRACE=32
    COLON=33
    SEMI=34
    DOT=35
    HASH=36
    LPAREN=37
    RPAREN=38
    NAME=39
    NUMBER=40
    STRING=41
    TEXT=42
    WS_R=43
    TAG_WS=44
    ERR_TAG=45
    EXPR_WS=46
    ERR_EXPR=47
    STMT_WS=48
    ERR_STMT=49
    CSS_WS=50
    ERR_CSS=51
    GT_R=52
    SLASH_R=53
    JPRINT_CLOSE_R=54
    LPAREN_R=55
    RPAREN_R=56
    EQ_R=57
    NEQ_R=58
    LE_R=59
    GE_R=60
    LTOP_R=61
    PLUS_R=62
    MINUS_R=63
    STAR_R=64
    NOT_R=65
    AND_R=66
    OR_R=67
    JSTMT_CLOSE_R=68
    IF_R=69
    ENDIF_R=70
    FOR_R=71
    ENDFOR_R=72
    IN_R=73
    SET_R=74
    ASSIGN_R=75
    DOT_R=76
    HASH_R=77
    LBRACE_R=78
    RBRACE_R=79
    COLON_R=80
    SEMI_R=81

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.13.2")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class DocumentContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def block(self):
            return self.getTypedRuleContext(TemplateParser.BlockContext,0)


        def EOF(self):
            return self.getToken(TemplateParser.EOF, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_document

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDocument" ):
                listener.enterDocument(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDocument" ):
                listener.exitDocument(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitDocument" ):
                return visitor.visitDocument(self)
            else:
                return visitor.visitChildren(self)




    def document(self):

        localctx = TemplateParser.DocumentContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_document)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 56
            self.block()
            self.state = 57
            self.match(TemplateParser.EOF)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class BlockContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def node(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.NodeContext)
            else:
                return self.getTypedRuleContext(TemplateParser.NodeContext,i)


        def getRuleIndex(self):
            return TemplateParser.RULE_block

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterBlock" ):
                listener.enterBlock(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitBlock" ):
                listener.exitBlock(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitBlock" ):
                return visitor.visitBlock(self)
            else:
                return visitor.visitChildren(self)




    def block(self):

        localctx = TemplateParser.BlockContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_block)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 62
            self._errHandler.sync(self)
            _alt = self._interp.adaptivePredict(self._input,0,self._ctx)
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt==1:
                    self.state = 59
                    self.node() 
                self.state = 64
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,0,self._ctx)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class NodeContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def htmlElement(self):
            return self.getTypedRuleContext(TemplateParser.HtmlElementContext,0)


        def jinjaPrint(self):
            return self.getTypedRuleContext(TemplateParser.JinjaPrintContext,0)


        def jinjaStmt(self):
            return self.getTypedRuleContext(TemplateParser.JinjaStmtContext,0)


        def cssBlock(self):
            return self.getTypedRuleContext(TemplateParser.CssBlockContext,0)


        def TEXT(self):
            return self.getToken(TemplateParser.TEXT, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_node

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterNode" ):
                listener.enterNode(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitNode" ):
                listener.exitNode(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitNode" ):
                return visitor.visitNode(self)
            else:
                return visitor.visitChildren(self)




    def node(self):

        localctx = TemplateParser.NodeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_node)
        try:
            self.state = 70
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [7]:
                self.enterOuterAlt(localctx, 1)
                self.state = 65
                self.htmlElement()
                pass
            elif token in [1]:
                self.enterOuterAlt(localctx, 2)
                self.state = 66
                self.jinjaPrint()
                pass
            elif token in [3]:
                self.enterOuterAlt(localctx, 3)
                self.state = 67
                self.jinjaStmt()
                pass
            elif token in [5]:
                self.enterOuterAlt(localctx, 4)
                self.state = 68
                self.cssBlock()
                pass
            elif token in [42]:
                self.enterOuterAlt(localctx, 5)
                self.state = 69
                self.match(TemplateParser.TEXT)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class HtmlElementContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def openTag(self):
            return self.getTypedRuleContext(TemplateParser.OpenTagContext,0)


        def htmlContent(self):
            return self.getTypedRuleContext(TemplateParser.HtmlContentContext,0)


        def closeTag(self):
            return self.getTypedRuleContext(TemplateParser.CloseTagContext,0)


        def getRuleIndex(self):
            return TemplateParser.RULE_htmlElement

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterHtmlElement" ):
                listener.enterHtmlElement(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitHtmlElement" ):
                listener.exitHtmlElement(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitHtmlElement" ):
                return visitor.visitHtmlElement(self)
            else:
                return visitor.visitChildren(self)




    def htmlElement(self):

        localctx = TemplateParser.HtmlElementContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_htmlElement)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 72
            self.openTag()
            self.state = 73
            self.htmlContent()
            self.state = 74
            self.closeTag()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class OpenTagContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def LT(self):
            return self.getToken(TemplateParser.LT, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def GT(self):
            return self.getToken(TemplateParser.GT, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_openTag

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterOpenTag" ):
                listener.enterOpenTag(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitOpenTag" ):
                listener.exitOpenTag(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitOpenTag" ):
                return visitor.visitOpenTag(self)
            else:
                return visitor.visitChildren(self)




    def openTag(self):

        localctx = TemplateParser.OpenTagContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_openTag)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 76
            self.match(TemplateParser.LT)
            self.state = 77
            self.match(TemplateParser.NAME)
            self.state = 78
            self.match(TemplateParser.GT)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CloseTagContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def LT_SLASH(self):
            return self.getToken(TemplateParser.LT_SLASH, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def GT(self):
            return self.getToken(TemplateParser.GT, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_closeTag

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCloseTag" ):
                listener.enterCloseTag(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCloseTag" ):
                listener.exitCloseTag(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCloseTag" ):
                return visitor.visitCloseTag(self)
            else:
                return visitor.visitChildren(self)




    def closeTag(self):

        localctx = TemplateParser.CloseTagContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_closeTag)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 80
            self.match(TemplateParser.LT_SLASH)
            self.state = 81
            self.match(TemplateParser.NAME)
            self.state = 82
            self.match(TemplateParser.GT)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class HtmlContentContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def htmlNode(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.HtmlNodeContext)
            else:
                return self.getTypedRuleContext(TemplateParser.HtmlNodeContext,i)


        def getRuleIndex(self):
            return TemplateParser.RULE_htmlContent

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterHtmlContent" ):
                listener.enterHtmlContent(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitHtmlContent" ):
                listener.exitHtmlContent(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitHtmlContent" ):
                return visitor.visitHtmlContent(self)
            else:
                return visitor.visitChildren(self)




    def htmlContent(self):

        localctx = TemplateParser.HtmlContentContext(self, self._ctx, self.state)
        self.enterRule(localctx, 12, self.RULE_htmlContent)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 88
            self._errHandler.sync(self)
            _alt = self._interp.adaptivePredict(self._input,2,self._ctx)
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt==1:
                    self.state = 84
                    if not self._input.LA(1) != self.LT_SLASH:
                        from antlr4.error.Errors import FailedPredicateException
                        raise FailedPredicateException(self, "self._input.LA(1) != self.LT_SLASH")
                    self.state = 85
                    self.htmlNode() 
                self.state = 90
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,2,self._ctx)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class HtmlNodeContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def htmlElement(self):
            return self.getTypedRuleContext(TemplateParser.HtmlElementContext,0)


        def jinjaPrint(self):
            return self.getTypedRuleContext(TemplateParser.JinjaPrintContext,0)


        def jinjaStmt(self):
            return self.getTypedRuleContext(TemplateParser.JinjaStmtContext,0)


        def TEXT(self):
            return self.getToken(TemplateParser.TEXT, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_htmlNode

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterHtmlNode" ):
                listener.enterHtmlNode(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitHtmlNode" ):
                listener.exitHtmlNode(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitHtmlNode" ):
                return visitor.visitHtmlNode(self)
            else:
                return visitor.visitChildren(self)




    def htmlNode(self):

        localctx = TemplateParser.HtmlNodeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_htmlNode)
        try:
            self.state = 95
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [7]:
                self.enterOuterAlt(localctx, 1)
                self.state = 91
                self.htmlElement()
                pass
            elif token in [1]:
                self.enterOuterAlt(localctx, 2)
                self.state = 92
                self.jinjaPrint()
                pass
            elif token in [3]:
                self.enterOuterAlt(localctx, 3)
                self.state = 93
                self.jinjaStmt()
                pass
            elif token in [42]:
                self.enterOuterAlt(localctx, 4)
                self.state = 94
                self.match(TemplateParser.TEXT)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class JinjaPrintContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def JPRINT_OPEN(self):
            return self.getToken(TemplateParser.JPRINT_OPEN, 0)

        def expr(self):
            return self.getTypedRuleContext(TemplateParser.ExprContext,0)


        def JPRINT_CLOSE(self):
            return self.getToken(TemplateParser.JPRINT_CLOSE, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_jinjaPrint

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterJinjaPrint" ):
                listener.enterJinjaPrint(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitJinjaPrint" ):
                listener.exitJinjaPrint(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitJinjaPrint" ):
                return visitor.visitJinjaPrint(self)
            else:
                return visitor.visitChildren(self)




    def jinjaPrint(self):

        localctx = TemplateParser.JinjaPrintContext(self, self._ctx, self.state)
        self.enterRule(localctx, 16, self.RULE_jinjaPrint)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 97
            self.match(TemplateParser.JPRINT_OPEN)
            self.state = 98
            self.expr()
            self.state = 99
            self.match(TemplateParser.JPRINT_CLOSE)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class JinjaStmtContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def jinjaIf(self):
            return self.getTypedRuleContext(TemplateParser.JinjaIfContext,0)


        def jinjaFor(self):
            return self.getTypedRuleContext(TemplateParser.JinjaForContext,0)


        def jinjaSet(self):
            return self.getTypedRuleContext(TemplateParser.JinjaSetContext,0)


        def getRuleIndex(self):
            return TemplateParser.RULE_jinjaStmt

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterJinjaStmt" ):
                listener.enterJinjaStmt(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitJinjaStmt" ):
                listener.exitJinjaStmt(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitJinjaStmt" ):
                return visitor.visitJinjaStmt(self)
            else:
                return visitor.visitChildren(self)




    def jinjaStmt(self):

        localctx = TemplateParser.JinjaStmtContext(self, self._ctx, self.state)
        self.enterRule(localctx, 18, self.RULE_jinjaStmt)
        try:
            self.state = 104
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,4,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 101
                self.jinjaIf()
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 102
                self.jinjaFor()
                pass

            elif la_ == 3:
                self.enterOuterAlt(localctx, 3)
                self.state = 103
                self.jinjaSet()
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class JinjaIfContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def JSTMT_OPEN(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.JSTMT_OPEN)
            else:
                return self.getToken(TemplateParser.JSTMT_OPEN, i)

        def IF(self):
            return self.getToken(TemplateParser.IF, 0)

        def expr(self):
            return self.getTypedRuleContext(TemplateParser.ExprContext,0)


        def JSTMT_CLOSE(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.JSTMT_CLOSE)
            else:
                return self.getToken(TemplateParser.JSTMT_CLOSE, i)

        def block(self):
            return self.getTypedRuleContext(TemplateParser.BlockContext,0)


        def ENDIF(self):
            return self.getToken(TemplateParser.ENDIF, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_jinjaIf

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterJinjaIf" ):
                listener.enterJinjaIf(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitJinjaIf" ):
                listener.exitJinjaIf(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitJinjaIf" ):
                return visitor.visitJinjaIf(self)
            else:
                return visitor.visitChildren(self)




    def jinjaIf(self):

        localctx = TemplateParser.JinjaIfContext(self, self._ctx, self.state)
        self.enterRule(localctx, 20, self.RULE_jinjaIf)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 106
            self.match(TemplateParser.JSTMT_OPEN)
            self.state = 107
            self.match(TemplateParser.IF)
            self.state = 108
            self.expr()
            self.state = 109
            self.match(TemplateParser.JSTMT_CLOSE)
            self.state = 110
            self.block()
            self.state = 111
            self.match(TemplateParser.JSTMT_OPEN)
            self.state = 112
            self.match(TemplateParser.ENDIF)
            self.state = 113
            self.match(TemplateParser.JSTMT_CLOSE)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class JinjaForContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def JSTMT_OPEN(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.JSTMT_OPEN)
            else:
                return self.getToken(TemplateParser.JSTMT_OPEN, i)

        def FOR(self):
            return self.getToken(TemplateParser.FOR, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def IN(self):
            return self.getToken(TemplateParser.IN, 0)

        def expr(self):
            return self.getTypedRuleContext(TemplateParser.ExprContext,0)


        def JSTMT_CLOSE(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.JSTMT_CLOSE)
            else:
                return self.getToken(TemplateParser.JSTMT_CLOSE, i)

        def block(self):
            return self.getTypedRuleContext(TemplateParser.BlockContext,0)


        def ENDFOR(self):
            return self.getToken(TemplateParser.ENDFOR, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_jinjaFor

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterJinjaFor" ):
                listener.enterJinjaFor(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitJinjaFor" ):
                listener.exitJinjaFor(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitJinjaFor" ):
                return visitor.visitJinjaFor(self)
            else:
                return visitor.visitChildren(self)




    def jinjaFor(self):

        localctx = TemplateParser.JinjaForContext(self, self._ctx, self.state)
        self.enterRule(localctx, 22, self.RULE_jinjaFor)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 115
            self.match(TemplateParser.JSTMT_OPEN)
            self.state = 116
            self.match(TemplateParser.FOR)
            self.state = 117
            self.match(TemplateParser.NAME)
            self.state = 118
            self.match(TemplateParser.IN)
            self.state = 119
            self.expr()
            self.state = 120
            self.match(TemplateParser.JSTMT_CLOSE)
            self.state = 121
            self.block()
            self.state = 122
            self.match(TemplateParser.JSTMT_OPEN)
            self.state = 123
            self.match(TemplateParser.ENDFOR)
            self.state = 124
            self.match(TemplateParser.JSTMT_CLOSE)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class JinjaSetContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def JSTMT_OPEN(self):
            return self.getToken(TemplateParser.JSTMT_OPEN, 0)

        def SET(self):
            return self.getToken(TemplateParser.SET, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def ASSIGN(self):
            return self.getToken(TemplateParser.ASSIGN, 0)

        def expr(self):
            return self.getTypedRuleContext(TemplateParser.ExprContext,0)


        def JSTMT_CLOSE(self):
            return self.getToken(TemplateParser.JSTMT_CLOSE, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_jinjaSet

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterJinjaSet" ):
                listener.enterJinjaSet(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitJinjaSet" ):
                listener.exitJinjaSet(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitJinjaSet" ):
                return visitor.visitJinjaSet(self)
            else:
                return visitor.visitChildren(self)




    def jinjaSet(self):

        localctx = TemplateParser.JinjaSetContext(self, self._ctx, self.state)
        self.enterRule(localctx, 24, self.RULE_jinjaSet)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 126
            self.match(TemplateParser.JSTMT_OPEN)
            self.state = 127
            self.match(TemplateParser.SET)
            self.state = 128
            self.match(TemplateParser.NAME)
            self.state = 129
            self.match(TemplateParser.ASSIGN)
            self.state = 130
            self.expr()
            self.state = 131
            self.match(TemplateParser.JSTMT_CLOSE)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CssBlockContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def CSS_BLOCK_START(self):
            return self.getToken(TemplateParser.CSS_BLOCK_START, 0)

        def CSS_BLOCK_END(self):
            return self.getToken(TemplateParser.CSS_BLOCK_END, 0)

        def cssRule(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.CssRuleContext)
            else:
                return self.getTypedRuleContext(TemplateParser.CssRuleContext,i)


        def getRuleIndex(self):
            return TemplateParser.RULE_cssBlock

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCssBlock" ):
                listener.enterCssBlock(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCssBlock" ):
                listener.exitCssBlock(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCssBlock" ):
                return visitor.visitCssBlock(self)
            else:
                return visitor.visitChildren(self)




    def cssBlock(self):

        localctx = TemplateParser.CssBlockContext(self, self._ctx, self.state)
        self.enterRule(localctx, 26, self.RULE_cssBlock)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 133
            self.match(TemplateParser.CSS_BLOCK_START)
            self.state = 137
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while (((_la) & ~0x3f) == 0 and ((1 << _la) & 652835028992) != 0):
                self.state = 134
                self.cssRule()
                self.state = 139
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 140
            self.match(TemplateParser.CSS_BLOCK_END)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CssRuleContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def selector(self):
            return self.getTypedRuleContext(TemplateParser.SelectorContext,0)


        def LBRACE(self):
            return self.getToken(TemplateParser.LBRACE, 0)

        def RBRACE(self):
            return self.getToken(TemplateParser.RBRACE, 0)

        def cssDecl(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.CssDeclContext)
            else:
                return self.getTypedRuleContext(TemplateParser.CssDeclContext,i)


        def getRuleIndex(self):
            return TemplateParser.RULE_cssRule

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCssRule" ):
                listener.enterCssRule(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCssRule" ):
                listener.exitCssRule(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCssRule" ):
                return visitor.visitCssRule(self)
            else:
                return visitor.visitChildren(self)




    def cssRule(self):

        localctx = TemplateParser.CssRuleContext(self, self._ctx, self.state)
        self.enterRule(localctx, 28, self.RULE_cssRule)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 142
            self.selector()
            self.state = 143
            self.match(TemplateParser.LBRACE)
            self.state = 147
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==39:
                self.state = 144
                self.cssDecl()
                self.state = 149
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 150
            self.match(TemplateParser.RBRACE)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class SelectorContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def DOT(self):
            return self.getToken(TemplateParser.DOT, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def HASH(self):
            return self.getToken(TemplateParser.HASH, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_selector

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSelector" ):
                listener.enterSelector(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSelector" ):
                listener.exitSelector(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitSelector" ):
                return visitor.visitSelector(self)
            else:
                return visitor.visitChildren(self)




    def selector(self):

        localctx = TemplateParser.SelectorContext(self, self._ctx, self.state)
        self.enterRule(localctx, 30, self.RULE_selector)
        try:
            self.state = 157
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [35]:
                self.enterOuterAlt(localctx, 1)
                self.state = 152
                self.match(TemplateParser.DOT)
                self.state = 153
                self.match(TemplateParser.NAME)
                pass
            elif token in [36]:
                self.enterOuterAlt(localctx, 2)
                self.state = 154
                self.match(TemplateParser.HASH)
                self.state = 155
                self.match(TemplateParser.NAME)
                pass
            elif token in [39]:
                self.enterOuterAlt(localctx, 3)
                self.state = 156
                self.match(TemplateParser.NAME)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CssDeclContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def cssProp(self):
            return self.getTypedRuleContext(TemplateParser.CssPropContext,0)


        def COLON(self):
            return self.getToken(TemplateParser.COLON, 0)

        def cssValue(self):
            return self.getTypedRuleContext(TemplateParser.CssValueContext,0)


        def SEMI(self):
            return self.getToken(TemplateParser.SEMI, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_cssDecl

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCssDecl" ):
                listener.enterCssDecl(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCssDecl" ):
                listener.exitCssDecl(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCssDecl" ):
                return visitor.visitCssDecl(self)
            else:
                return visitor.visitChildren(self)




    def cssDecl(self):

        localctx = TemplateParser.CssDeclContext(self, self._ctx, self.state)
        self.enterRule(localctx, 32, self.RULE_cssDecl)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 159
            self.cssProp()
            self.state = 160
            self.match(TemplateParser.COLON)
            self.state = 161
            self.cssValue()
            self.state = 162
            self.match(TemplateParser.SEMI)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CssPropContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NAME(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.NAME)
            else:
                return self.getToken(TemplateParser.NAME, i)

        def MINUS(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.MINUS)
            else:
                return self.getToken(TemplateParser.MINUS, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_cssProp

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCssProp" ):
                listener.enterCssProp(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCssProp" ):
                listener.exitCssProp(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCssProp" ):
                return visitor.visitCssProp(self)
            else:
                return visitor.visitChildren(self)




    def cssProp(self):

        localctx = TemplateParser.CssPropContext(self, self._ctx, self.state)
        self.enterRule(localctx, 34, self.RULE_cssProp)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 164
            self.match(TemplateParser.NAME)
            self.state = 169
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==25:
                self.state = 165
                self.match(TemplateParser.MINUS)
                self.state = 166
                self.match(TemplateParser.NAME)
                self.state = 171
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CssValueContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NUMBER(self):
            return self.getToken(TemplateParser.NUMBER, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def STRING(self):
            return self.getToken(TemplateParser.STRING, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_cssValue

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCssValue" ):
                listener.enterCssValue(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCssValue" ):
                listener.exitCssValue(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCssValue" ):
                return visitor.visitCssValue(self)
            else:
                return visitor.visitChildren(self)




    def cssValue(self):

        localctx = TemplateParser.CssValueContext(self, self._ctx, self.state)
        self.enterRule(localctx, 36, self.RULE_cssValue)
        self._la = 0 # Token type
        try:
            self.state = 178
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [40]:
                self.enterOuterAlt(localctx, 1)
                self.state = 172
                self.match(TemplateParser.NUMBER)
                self.state = 174
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if _la==39:
                    self.state = 173
                    self.match(TemplateParser.NAME)


                pass
            elif token in [39]:
                self.enterOuterAlt(localctx, 2)
                self.state = 176
                self.match(TemplateParser.NAME)
                pass
            elif token in [41]:
                self.enterOuterAlt(localctx, 3)
                self.state = 177
                self.match(TemplateParser.STRING)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class ExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def orExpr(self):
            return self.getTypedRuleContext(TemplateParser.OrExprContext,0)


        def getRuleIndex(self):
            return TemplateParser.RULE_expr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterExpr" ):
                listener.enterExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitExpr" ):
                listener.exitExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitExpr" ):
                return visitor.visitExpr(self)
            else:
                return visitor.visitChildren(self)




    def expr(self):

        localctx = TemplateParser.ExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 38, self.RULE_expr)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 180
            self.orExpr()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class OrExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def andExpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.AndExprContext)
            else:
                return self.getTypedRuleContext(TemplateParser.AndExprContext,i)


        def OR(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.OR)
            else:
                return self.getToken(TemplateParser.OR, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_orExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterOrExpr" ):
                listener.enterOrExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitOrExpr" ):
                listener.exitOrExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitOrExpr" ):
                return visitor.visitOrExpr(self)
            else:
                return visitor.visitChildren(self)




    def orExpr(self):

        localctx = TemplateParser.OrExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 40, self.RULE_orExpr)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 182
            self.andExpr()
            self.state = 187
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==30:
                self.state = 183
                self.match(TemplateParser.OR)
                self.state = 184
                self.andExpr()
                self.state = 189
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AndExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def eqExpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.EqExprContext)
            else:
                return self.getTypedRuleContext(TemplateParser.EqExprContext,i)


        def AND(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.AND)
            else:
                return self.getToken(TemplateParser.AND, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_andExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAndExpr" ):
                listener.enterAndExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAndExpr" ):
                listener.exitAndExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAndExpr" ):
                return visitor.visitAndExpr(self)
            else:
                return visitor.visitChildren(self)




    def andExpr(self):

        localctx = TemplateParser.AndExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 42, self.RULE_andExpr)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 190
            self.eqExpr()
            self.state = 195
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==29:
                self.state = 191
                self.match(TemplateParser.AND)
                self.state = 192
                self.eqExpr()
                self.state = 197
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class EqExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def relExpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.RelExprContext)
            else:
                return self.getTypedRuleContext(TemplateParser.RelExprContext,i)


        def EQ(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.EQ)
            else:
                return self.getToken(TemplateParser.EQ, i)

        def NEQ(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.NEQ)
            else:
                return self.getToken(TemplateParser.NEQ, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_eqExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterEqExpr" ):
                listener.enterEqExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitEqExpr" ):
                listener.exitEqExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitEqExpr" ):
                return visitor.visitEqExpr(self)
            else:
                return visitor.visitChildren(self)




    def eqExpr(self):

        localctx = TemplateParser.EqExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 44, self.RULE_eqExpr)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 198
            self.relExpr()
            self.state = 203
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==18 or _la==19:
                self.state = 199
                _la = self._input.LA(1)
                if not(_la==18 or _la==19):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()
                self.state = 200
                self.relExpr()
                self.state = 205
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class RelExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def addExpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.AddExprContext)
            else:
                return self.getTypedRuleContext(TemplateParser.AddExprContext,i)


        def LT_OP(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.LT_OP)
            else:
                return self.getToken(TemplateParser.LT_OP, i)

        def GT_OP(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.GT_OP)
            else:
                return self.getToken(TemplateParser.GT_OP, i)

        def LE(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.LE)
            else:
                return self.getToken(TemplateParser.LE, i)

        def GE(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.GE)
            else:
                return self.getToken(TemplateParser.GE, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_relExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterRelExpr" ):
                listener.enterRelExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitRelExpr" ):
                listener.exitRelExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitRelExpr" ):
                return visitor.visitRelExpr(self)
            else:
                return visitor.visitChildren(self)




    def relExpr(self):

        localctx = TemplateParser.RelExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 46, self.RULE_relExpr)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 206
            self.addExpr()
            self.state = 211
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while (((_la) & ~0x3f) == 0 and ((1 << _la) & 15728640) != 0):
                self.state = 207
                _la = self._input.LA(1)
                if not((((_la) & ~0x3f) == 0 and ((1 << _la) & 15728640) != 0)):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()
                self.state = 208
                self.addExpr()
                self.state = 213
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AddExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def mulExpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.MulExprContext)
            else:
                return self.getTypedRuleContext(TemplateParser.MulExprContext,i)


        def PLUS(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.PLUS)
            else:
                return self.getToken(TemplateParser.PLUS, i)

        def MINUS(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.MINUS)
            else:
                return self.getToken(TemplateParser.MINUS, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_addExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAddExpr" ):
                listener.enterAddExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAddExpr" ):
                listener.exitAddExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAddExpr" ):
                return visitor.visitAddExpr(self)
            else:
                return visitor.visitChildren(self)




    def addExpr(self):

        localctx = TemplateParser.AddExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 48, self.RULE_addExpr)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 214
            self.mulExpr()
            self.state = 219
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==24 or _la==25:
                self.state = 215
                _la = self._input.LA(1)
                if not(_la==24 or _la==25):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()
                self.state = 216
                self.mulExpr()
                self.state = 221
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class MulExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def unaryExpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TemplateParser.UnaryExprContext)
            else:
                return self.getTypedRuleContext(TemplateParser.UnaryExprContext,i)


        def STAR(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.STAR)
            else:
                return self.getToken(TemplateParser.STAR, i)

        def DIV(self, i:int=None):
            if i is None:
                return self.getTokens(TemplateParser.DIV)
            else:
                return self.getToken(TemplateParser.DIV, i)

        def getRuleIndex(self):
            return TemplateParser.RULE_mulExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterMulExpr" ):
                listener.enterMulExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitMulExpr" ):
                listener.exitMulExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitMulExpr" ):
                return visitor.visitMulExpr(self)
            else:
                return visitor.visitChildren(self)




    def mulExpr(self):

        localctx = TemplateParser.MulExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 50, self.RULE_mulExpr)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 222
            self.unaryExpr()
            self.state = 227
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==26 or _la==27:
                self.state = 223
                _la = self._input.LA(1)
                if not(_la==26 or _la==27):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()
                self.state = 224
                self.unaryExpr()
                self.state = 229
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class UnaryExprContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def unaryExpr(self):
            return self.getTypedRuleContext(TemplateParser.UnaryExprContext,0)


        def NOT(self):
            return self.getToken(TemplateParser.NOT, 0)

        def MINUS(self):
            return self.getToken(TemplateParser.MINUS, 0)

        def primary(self):
            return self.getTypedRuleContext(TemplateParser.PrimaryContext,0)


        def getRuleIndex(self):
            return TemplateParser.RULE_unaryExpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterUnaryExpr" ):
                listener.enterUnaryExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitUnaryExpr" ):
                listener.exitUnaryExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitUnaryExpr" ):
                return visitor.visitUnaryExpr(self)
            else:
                return visitor.visitChildren(self)




    def unaryExpr(self):

        localctx = TemplateParser.UnaryExprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 52, self.RULE_unaryExpr)
        self._la = 0 # Token type
        try:
            self.state = 233
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [25, 28]:
                self.enterOuterAlt(localctx, 1)
                self.state = 230
                _la = self._input.LA(1)
                if not(_la==25 or _la==28):
                    self._errHandler.recoverInline(self)
                else:
                    self._errHandler.reportMatch(self)
                    self.consume()
                self.state = 231
                self.unaryExpr()
                pass
            elif token in [37, 39, 40, 41]:
                self.enterOuterAlt(localctx, 2)
                self.state = 232
                self.primary()
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class PrimaryContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NUMBER(self):
            return self.getToken(TemplateParser.NUMBER, 0)

        def STRING(self):
            return self.getToken(TemplateParser.STRING, 0)

        def NAME(self):
            return self.getToken(TemplateParser.NAME, 0)

        def LPAREN(self):
            return self.getToken(TemplateParser.LPAREN, 0)

        def expr(self):
            return self.getTypedRuleContext(TemplateParser.ExprContext,0)


        def RPAREN(self):
            return self.getToken(TemplateParser.RPAREN, 0)

        def getRuleIndex(self):
            return TemplateParser.RULE_primary

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPrimary" ):
                listener.enterPrimary(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPrimary" ):
                listener.exitPrimary(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitPrimary" ):
                return visitor.visitPrimary(self)
            else:
                return visitor.visitChildren(self)




    def primary(self):

        localctx = TemplateParser.PrimaryContext(self, self._ctx, self.state)
        self.enterRule(localctx, 54, self.RULE_primary)
        try:
            self.state = 242
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [40]:
                self.enterOuterAlt(localctx, 1)
                self.state = 235
                self.match(TemplateParser.NUMBER)
                pass
            elif token in [41]:
                self.enterOuterAlt(localctx, 2)
                self.state = 236
                self.match(TemplateParser.STRING)
                pass
            elif token in [39]:
                self.enterOuterAlt(localctx, 3)
                self.state = 237
                self.match(TemplateParser.NAME)
                pass
            elif token in [37]:
                self.enterOuterAlt(localctx, 4)
                self.state = 238
                self.match(TemplateParser.LPAREN)
                self.state = 239
                self.expr()
                self.state = 240
                self.match(TemplateParser.RPAREN)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx



    def sempred(self, localctx:RuleContext, ruleIndex:int, predIndex:int):
        if self._predicates == None:
            self._predicates = dict()
        self._predicates[6] = self.htmlContent_sempred
        pred = self._predicates.get(ruleIndex, None)
        if pred is None:
            raise Exception("No predicate with index:" + str(ruleIndex))
        else:
            return pred(localctx, predIndex)

    def htmlContent_sempred(self, localctx:HtmlContentContext, predIndex:int):
            if predIndex == 0:
                return self._input.LA(1) != self.LT_SLASH
         




