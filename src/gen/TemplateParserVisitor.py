# Generated from grammar/TemplateParser.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .TemplateParser import TemplateParser
else:
    from TemplateParser import TemplateParser

# This class defines a complete generic visitor for a parse tree produced by TemplateParser.

class TemplateParserVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by TemplateParser#document.
    def visitDocument(self, ctx:TemplateParser.DocumentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#block.
    def visitBlock(self, ctx:TemplateParser.BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#node.
    def visitNode(self, ctx:TemplateParser.NodeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#htmlElement.
    def visitHtmlElement(self, ctx:TemplateParser.HtmlElementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#openTag.
    def visitOpenTag(self, ctx:TemplateParser.OpenTagContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#closeTag.
    def visitCloseTag(self, ctx:TemplateParser.CloseTagContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#htmlContent.
    def visitHtmlContent(self, ctx:TemplateParser.HtmlContentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#htmlNode.
    def visitHtmlNode(self, ctx:TemplateParser.HtmlNodeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#jinjaPrint.
    def visitJinjaPrint(self, ctx:TemplateParser.JinjaPrintContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#jinjaStmt.
    def visitJinjaStmt(self, ctx:TemplateParser.JinjaStmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#jinjaIf.
    def visitJinjaIf(self, ctx:TemplateParser.JinjaIfContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#jinjaFor.
    def visitJinjaFor(self, ctx:TemplateParser.JinjaForContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#jinjaSet.
    def visitJinjaSet(self, ctx:TemplateParser.JinjaSetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#cssBlock.
    def visitCssBlock(self, ctx:TemplateParser.CssBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#cssRule.
    def visitCssRule(self, ctx:TemplateParser.CssRuleContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#selector.
    def visitSelector(self, ctx:TemplateParser.SelectorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#cssDecl.
    def visitCssDecl(self, ctx:TemplateParser.CssDeclContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#cssProp.
    def visitCssProp(self, ctx:TemplateParser.CssPropContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#cssValue.
    def visitCssValue(self, ctx:TemplateParser.CssValueContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#expr.
    def visitExpr(self, ctx:TemplateParser.ExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#orExpr.
    def visitOrExpr(self, ctx:TemplateParser.OrExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#andExpr.
    def visitAndExpr(self, ctx:TemplateParser.AndExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#eqExpr.
    def visitEqExpr(self, ctx:TemplateParser.EqExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#relExpr.
    def visitRelExpr(self, ctx:TemplateParser.RelExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#addExpr.
    def visitAddExpr(self, ctx:TemplateParser.AddExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#mulExpr.
    def visitMulExpr(self, ctx:TemplateParser.MulExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#unaryExpr.
    def visitUnaryExpr(self, ctx:TemplateParser.UnaryExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by TemplateParser#primary.
    def visitPrimary(self, ctx:TemplateParser.PrimaryContext):
        return self.visitChildren(ctx)



del TemplateParser