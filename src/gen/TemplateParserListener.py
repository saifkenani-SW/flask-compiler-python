# Generated from grammar/TemplateParser.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .TemplateParser import TemplateParser
else:
    from TemplateParser import TemplateParser

# This class defines a complete listener for a parse tree produced by TemplateParser.
class TemplateParserListener(ParseTreeListener):

    # Enter a parse tree produced by TemplateParser#document.
    def enterDocument(self, ctx:TemplateParser.DocumentContext):
        pass

    # Exit a parse tree produced by TemplateParser#document.
    def exitDocument(self, ctx:TemplateParser.DocumentContext):
        pass


    # Enter a parse tree produced by TemplateParser#block.
    def enterBlock(self, ctx:TemplateParser.BlockContext):
        pass

    # Exit a parse tree produced by TemplateParser#block.
    def exitBlock(self, ctx:TemplateParser.BlockContext):
        pass


    # Enter a parse tree produced by TemplateParser#node.
    def enterNode(self, ctx:TemplateParser.NodeContext):
        pass

    # Exit a parse tree produced by TemplateParser#node.
    def exitNode(self, ctx:TemplateParser.NodeContext):
        pass


    # Enter a parse tree produced by TemplateParser#htmlElement.
    def enterHtmlElement(self, ctx:TemplateParser.HtmlElementContext):
        pass

    # Exit a parse tree produced by TemplateParser#htmlElement.
    def exitHtmlElement(self, ctx:TemplateParser.HtmlElementContext):
        pass


    # Enter a parse tree produced by TemplateParser#openTag.
    def enterOpenTag(self, ctx:TemplateParser.OpenTagContext):
        pass

    # Exit a parse tree produced by TemplateParser#openTag.
    def exitOpenTag(self, ctx:TemplateParser.OpenTagContext):
        pass


    # Enter a parse tree produced by TemplateParser#closeTag.
    def enterCloseTag(self, ctx:TemplateParser.CloseTagContext):
        pass

    # Exit a parse tree produced by TemplateParser#closeTag.
    def exitCloseTag(self, ctx:TemplateParser.CloseTagContext):
        pass


    # Enter a parse tree produced by TemplateParser#htmlContent.
    def enterHtmlContent(self, ctx:TemplateParser.HtmlContentContext):
        pass

    # Exit a parse tree produced by TemplateParser#htmlContent.
    def exitHtmlContent(self, ctx:TemplateParser.HtmlContentContext):
        pass


    # Enter a parse tree produced by TemplateParser#htmlNode.
    def enterHtmlNode(self, ctx:TemplateParser.HtmlNodeContext):
        pass

    # Exit a parse tree produced by TemplateParser#htmlNode.
    def exitHtmlNode(self, ctx:TemplateParser.HtmlNodeContext):
        pass


    # Enter a parse tree produced by TemplateParser#jinjaPrint.
    def enterJinjaPrint(self, ctx:TemplateParser.JinjaPrintContext):
        pass

    # Exit a parse tree produced by TemplateParser#jinjaPrint.
    def exitJinjaPrint(self, ctx:TemplateParser.JinjaPrintContext):
        pass


    # Enter a parse tree produced by TemplateParser#jinjaStmt.
    def enterJinjaStmt(self, ctx:TemplateParser.JinjaStmtContext):
        pass

    # Exit a parse tree produced by TemplateParser#jinjaStmt.
    def exitJinjaStmt(self, ctx:TemplateParser.JinjaStmtContext):
        pass


    # Enter a parse tree produced by TemplateParser#jinjaIf.
    def enterJinjaIf(self, ctx:TemplateParser.JinjaIfContext):
        pass

    # Exit a parse tree produced by TemplateParser#jinjaIf.
    def exitJinjaIf(self, ctx:TemplateParser.JinjaIfContext):
        pass


    # Enter a parse tree produced by TemplateParser#jinjaFor.
    def enterJinjaFor(self, ctx:TemplateParser.JinjaForContext):
        pass

    # Exit a parse tree produced by TemplateParser#jinjaFor.
    def exitJinjaFor(self, ctx:TemplateParser.JinjaForContext):
        pass


    # Enter a parse tree produced by TemplateParser#jinjaSet.
    def enterJinjaSet(self, ctx:TemplateParser.JinjaSetContext):
        pass

    # Exit a parse tree produced by TemplateParser#jinjaSet.
    def exitJinjaSet(self, ctx:TemplateParser.JinjaSetContext):
        pass


    # Enter a parse tree produced by TemplateParser#cssBlock.
    def enterCssBlock(self, ctx:TemplateParser.CssBlockContext):
        pass

    # Exit a parse tree produced by TemplateParser#cssBlock.
    def exitCssBlock(self, ctx:TemplateParser.CssBlockContext):
        pass


    # Enter a parse tree produced by TemplateParser#cssRule.
    def enterCssRule(self, ctx:TemplateParser.CssRuleContext):
        pass

    # Exit a parse tree produced by TemplateParser#cssRule.
    def exitCssRule(self, ctx:TemplateParser.CssRuleContext):
        pass


    # Enter a parse tree produced by TemplateParser#selector.
    def enterSelector(self, ctx:TemplateParser.SelectorContext):
        pass

    # Exit a parse tree produced by TemplateParser#selector.
    def exitSelector(self, ctx:TemplateParser.SelectorContext):
        pass


    # Enter a parse tree produced by TemplateParser#cssDecl.
    def enterCssDecl(self, ctx:TemplateParser.CssDeclContext):
        pass

    # Exit a parse tree produced by TemplateParser#cssDecl.
    def exitCssDecl(self, ctx:TemplateParser.CssDeclContext):
        pass


    # Enter a parse tree produced by TemplateParser#cssProp.
    def enterCssProp(self, ctx:TemplateParser.CssPropContext):
        pass

    # Exit a parse tree produced by TemplateParser#cssProp.
    def exitCssProp(self, ctx:TemplateParser.CssPropContext):
        pass


    # Enter a parse tree produced by TemplateParser#cssValue.
    def enterCssValue(self, ctx:TemplateParser.CssValueContext):
        pass

    # Exit a parse tree produced by TemplateParser#cssValue.
    def exitCssValue(self, ctx:TemplateParser.CssValueContext):
        pass


    # Enter a parse tree produced by TemplateParser#expr.
    def enterExpr(self, ctx:TemplateParser.ExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#expr.
    def exitExpr(self, ctx:TemplateParser.ExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#orExpr.
    def enterOrExpr(self, ctx:TemplateParser.OrExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#orExpr.
    def exitOrExpr(self, ctx:TemplateParser.OrExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#andExpr.
    def enterAndExpr(self, ctx:TemplateParser.AndExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#andExpr.
    def exitAndExpr(self, ctx:TemplateParser.AndExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#eqExpr.
    def enterEqExpr(self, ctx:TemplateParser.EqExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#eqExpr.
    def exitEqExpr(self, ctx:TemplateParser.EqExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#relExpr.
    def enterRelExpr(self, ctx:TemplateParser.RelExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#relExpr.
    def exitRelExpr(self, ctx:TemplateParser.RelExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#addExpr.
    def enterAddExpr(self, ctx:TemplateParser.AddExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#addExpr.
    def exitAddExpr(self, ctx:TemplateParser.AddExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#mulExpr.
    def enterMulExpr(self, ctx:TemplateParser.MulExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#mulExpr.
    def exitMulExpr(self, ctx:TemplateParser.MulExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#unaryExpr.
    def enterUnaryExpr(self, ctx:TemplateParser.UnaryExprContext):
        pass

    # Exit a parse tree produced by TemplateParser#unaryExpr.
    def exitUnaryExpr(self, ctx:TemplateParser.UnaryExprContext):
        pass


    # Enter a parse tree produced by TemplateParser#primary.
    def enterPrimary(self, ctx:TemplateParser.PrimaryContext):
        pass

    # Exit a parse tree produced by TemplateParser#primary.
    def exitPrimary(self, ctx:TemplateParser.PrimaryContext):
        pass



del TemplateParser