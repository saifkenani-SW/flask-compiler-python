package ast.visitors;

import ast.template.TemplateNode;
import ast.template.html.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;
import gen.FlaskTemplateParser;
import gen.FlaskTemplateParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TemplateASTBuilder extends FlaskTemplateParserBaseVisitor<TemplateNode> {

    // ================= HTML Document =================
    @Override
    public TemplateNode visitHtmlDocument(FlaskTemplateParser.HtmlDocumentContext ctx) {
        HTMLDocumentNode docNode = new HTMLDocumentNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine()
        );

        if (ctx.templateContent() != null) {
            ctx.templateContent().contentItem().forEach(childCtx -> {
                TemplateNode child = visit(childCtx);
                if (child != null) docNode.addContent(child);
            });
        }

        return docNode;
    }

    // ================= Normal HTML Element =================
    @Override
    public TemplateNode visitNormalElement(FlaskTemplateParser.NormalElementContext ctx) {
        String tagName = "<unknown>";
        if (ctx.openingTag() != null && ctx.openingTag().getChildCount() > 0) {
            tagName = ctx.openingTag().getChild(0).getText();
        }

        HTMLNormalElementNode node = new HTMLNormalElementNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                tagName
        );

        // attributes
        if (ctx.openingTag() != null) {
            for (int i = 0; i < ctx.openingTag().getChildCount(); i++) {
                if (ctx.openingTag().getChild(i) instanceof FlaskTemplateParser.HtmlAttributeContext) {
                    TemplateNode attr = visit(ctx.openingTag().getChild(i));
                    if (attr instanceof HTMLAttributeNode) {
                        node.addAttribute((HTMLAttributeNode) attr);
                    }
                }
            }
        }

        if (ctx.templateContent() != null) {
            ctx.templateContent().contentItem().forEach(c -> {
                TemplateNode child = visit(c);
                if (child != null) node.addContent(child);
            });
        }

        return node;
    }


    // ================= Void / Self-closing HTML Elements =================
    @Override
    public TemplateNode visitVoidElementTag(FlaskTemplateParser.VoidElementTagContext ctx) {
        String tagName = ctx.getChild(0).getText();

        return new HTMLVoidElementNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                tagName
        );
    }


    @Override
    public TemplateNode visitSelfClosingElementTag(FlaskTemplateParser.SelfClosingElementTagContext ctx) {
        String tagName = ctx.getChild(0).getText(); // خذ النص مباشرة
        return new HTMLSelfClosingElementNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                tagName
        );
    }


    // ================= HTML Attributes =================
    @Override
    public TemplateNode visitAttributeWithValue(FlaskTemplateParser.AttributeWithValueContext ctx) {
        String name = ctx.HTML_ID().getText();
        String value = ctx.htmlAttributeValue() != null ? ctx.htmlAttributeValue().getText() : null;
        HTMLAttributeNode node = new HTMLAttributeNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), name);
        node.setValue(value);
        return node;
    }

    @Override
    public TemplateNode visitBooleanAttribute(FlaskTemplateParser.BooleanAttributeContext ctx) {
        String name = ctx.getChild(0).getText();

        HTMLAttributeNode node = new HTMLAttributeNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                name
        );
        node.setBoolean(true);
        return node;
    }


    @Override
    public TemplateNode visitHtmlTextContent(FlaskTemplateParser.HtmlTextContentContext ctx) {
        return new HTMLTextNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }

    // ================= Jinja Expressions =================
    @Override
    public TemplateNode visitJinjaExpr(FlaskTemplateParser.JinjaExprContext ctx) {
        ExpressionNode expr = (ExpressionNode) visit(ctx.jinjaExpression());
        return new JinjaExpressionNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expr);
    }

    @Override
    public TemplateNode visitJinjaExpression(FlaskTemplateParser.JinjaExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public TemplateNode visitIdentifierExpr(FlaskTemplateParser.IdentifierExprContext ctx) {
        VariableNode var = new VariableNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.EXPR_ID(0).getText());
        for (int i = 1; i < ctx.EXPR_ID().size(); i++) {
            var.getPath().add(ctx.EXPR_ID(i).getText());
        }
        return var;
    }

    @Override
    public TemplateNode visitNumberExpr(FlaskTemplateParser.NumberExprContext ctx) {
        return new NumberLiteralNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Double.parseDouble(ctx.getText()));
    }

    @Override
    public TemplateNode visitStringExpr(FlaskTemplateParser.StringExprContext ctx) {
        return new StringLiteralNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }
    @Override
    public TemplateNode visitTemplateRoot(FlaskTemplateParser.TemplateRootContext ctx) {
        HTMLDocumentNode docNode = new HTMLDocumentNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine()
        );

        for (FlaskTemplateParser.HtmlContext htmlCtx : ctx.html()) {
            TemplateNode child = visit(htmlCtx);
            if (child != null) docNode.addContent(child);
        }

        return docNode;
    }

    @Override
    public TemplateNode visitTrueExpr(FlaskTemplateParser.TrueExprContext ctx) {
        return new BooleanLiteralNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), true);
    }

    @Override
    public TemplateNode visitFalseExpr(FlaskTemplateParser.FalseExprContext ctx) {
        return new BooleanLiteralNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), false);
    }

    @Override
    public TemplateNode visitNoneExpr(FlaskTemplateParser.NoneExprContext ctx) {
        return new NoneLiteralNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public TemplateNode visitBlockUnaryOp(FlaskTemplateParser.BlockUnaryOpContext ctx) {
        ExpressionNode operand = (ExpressionNode) visit(ctx.blockUnaryExpression());
        return new UnaryExpressionNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getChild(0).getText(), operand);
    }
    @Override
    public TemplateNode visitCallExpr(FlaskTemplateParser.CallExprContext ctx) {
        VariableNode callee = new VariableNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.EXPR_ID().getText()
        );

        CallExpressionNode node = new CallExpressionNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                callee
        );

        if (ctx.argumentList() != null) {
            for (int i = 0; i < ctx.argumentList().getChildCount(); i++) {
                var child = ctx.argumentList().getChild(i);
                if (child instanceof FlaskTemplateParser.JinjaExpressionContext) {
                    node.getArguments().add((ExpressionNode) visit(child));
                }
            }
        }

        return node;
    }






    @Override
    public TemplateNode visitFilterExpr(FlaskTemplateParser.FilterExprContext ctx) {
        ExpressionNode input = (ExpressionNode) visit(ctx.primaryExpression());

        FilterExpressionNode node = new FilterExpressionNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                input,
                ctx.EXPR_ID().getText()
        );

        if (ctx.argumentList() != null) {
            for (int i = 0; i < ctx.argumentList().getChildCount(); i++) {
                var child = ctx.argumentList().getChild(i);
                if (child instanceof FlaskTemplateParser.JinjaExpressionContext) {
                    node.getArguments().add((ExpressionNode) visit(child));
                }
            }
        }

        return node;
    }


    @Override
    public TemplateNode visitListExpr(FlaskTemplateParser.ListExprContext ctx) {
        ListExpressionNode node = new ListExpressionNode(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine()
        );

        if (ctx.expressionList() != null) {
            for (int i = 0; i < ctx.expressionList().getChildCount(); i++) {
                var child = ctx.expressionList().getChild(i);
                if (child instanceof FlaskTemplateParser.JinjaExpressionContext) {
                    node.getElements().add((ExpressionNode) visit(child));
                }
            }
        }

        return node;
    }





    public TemplateNode visitBlockDictLiteral(FlaskTemplateParser.BlockDictLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int col = ctx.getStart().getCharPositionInLine();

        DictExpressionNode node = new DictExpressionNode(line, col);

        if (ctx.blockDictPairList() != null) {
            FlaskTemplateParser.BlockDictPairListContext listCtx = ctx.blockDictPairList();

            for (FlaskTemplateParser.BlockDictPairContext pairCtx : listCtx.blockDictPair()) {
                TerminalNode keyNode = pairCtx.BLOCK_STRING();
                ExpressionNode key = new StringLiteralNode(
                        keyNode.getSymbol().getLine(),
                        keyNode.getSymbol().getCharPositionInLine(),
                        keyNode.getText()
                );

                ExpressionNode value = (ExpressionNode) visit(pairCtx.blockExpression());

                node.getPairs().put(key, value);
            }
        }

        return node;
    }




    @Override
    public TemplateNode visitAdditiveExpression(FlaskTemplateParser.AdditiveExpressionContext ctx) {
        ExpressionNode left = (ExpressionNode) visit(ctx.multiplicativeExpression(0));
        for (int i = 1; i < ctx.multiplicativeExpression().size(); i++) {
            ExpressionNode right = (ExpressionNode) visit(ctx.multiplicativeExpression(i));
            String op = ctx.getChild(2 * i - 1).getText();
            left = new BinaryExpressionNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), op, left, right);
        }
        return left;
    }

    // ================= Jinja Blocks =================
    @Override
    public TemplateNode visitIfStart(FlaskTemplateParser.IfStartContext ctx) {
        ExpressionNode cond = (ExpressionNode) visit(ctx.blockExpression());
        IfBlockNode node = new IfBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), cond);
        node.addContent(cond);
        return node;
    }

    @Override
    public TemplateNode visitElifBlock(FlaskTemplateParser.ElifBlockContext ctx) {
        ExpressionNode cond = (ExpressionNode) visit(ctx.blockExpression());
        ElifBlockNode node = new ElifBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), cond);
        node.addContent(cond);
        return node;
    }

    @Override
    public TemplateNode visitElseBlock(FlaskTemplateParser.ElseBlockContext ctx) {
        ElseBlockNode node = new ElseBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
        return node;
    }

    @Override
    public TemplateNode visitForStart(FlaskTemplateParser.ForStartContext ctx) {
        ExpressionNode iterable = (ExpressionNode) visit(ctx.blockExpression());
        ForBlockNode node = new ForBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_ID().getText(), iterable);
        node.addContent(iterable);
        return node;
    }

    @Override
    public TemplateNode visitBlockStart(FlaskTemplateParser.BlockStartContext ctx) {
        return new BlockBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_ID().getText());
    }

    @Override
    public TemplateNode visitGenericBlock(FlaskTemplateParser.GenericBlockContext ctx) {
        ExpressionNode expr = ctx.blockExpression() != null ? (ExpressionNode) visit(ctx.blockExpression()) : null;
        GenericBlockNode node = new GenericBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_ID().getText());
        if (expr != null) node.addContent(expr);
        return node;
    }

    @Override
    public TemplateNode visitSetBlock(FlaskTemplateParser.SetBlockContext ctx) {
        ExpressionNode expr = (ExpressionNode) visit(ctx.blockExpression());
        SetBlockNode node = new SetBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_ID().getText(), expr);
        node.addContent(expr);
        return node;
    }

    @Override
    public TemplateNode visitWithStart(FlaskTemplateParser.WithStartContext ctx) {
        ExpressionNode expr = (ExpressionNode) visit(ctx.blockExpression());
        WithBlockNode node = new WithBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expr);
        node.addContent(expr);
        return node;
    }

    @Override
    public TemplateNode visitIncludeBlock(FlaskTemplateParser.IncludeBlockContext ctx) {
        return new IncludeBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_STRING().getText());
    }

    @Override
    public TemplateNode visitImportBlock(FlaskTemplateParser.ImportBlockContext ctx) {
        String alias = ctx.BLOCK_ID() != null ? ctx.BLOCK_ID().getText() : null;
        return new ImportBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_STRING().getText(), alias);
    }

    @Override
    public TemplateNode visitFromImportBlock(FlaskTemplateParser.FromImportBlockContext ctx) {
        FromImportBlockNode node = new FromImportBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_STRING().getText());
        if (ctx.importList() != null) {
            ctx.importList().BLOCK_ID().forEach(id -> node.getImports().add(id.getText()));
        }
        return node;
    }

    @Override
    public TemplateNode visitExtendsBlock(FlaskTemplateParser.ExtendsBlockContext ctx) {
        return new ExtendsBlockNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.BLOCK_STRING().getText());
    }

}
