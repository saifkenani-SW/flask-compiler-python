package ast.visitors;

import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import ast.template.css.*;
import ast.template.html.DoctypeNode;
import ast.template.html.*;
import ast.template.html.HTMLDocumentNode;
import ast.template.html.HTMLNormalElementNode;
import ast.template.jinja.blocks.ElifBlockNode;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;

import gen.FlaskTemplateParser;
import gen.FlaskTemplateParserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;
import java.util.Stack;


public class TemplateASTBuilder extends FlaskTemplateParserBaseVisitor<TemplateNode> {

    @Override
    public TemplateNode visitTemplateRoot(FlaskTemplateParser.TemplateRootContext ctx) {

        TemplateRootNode root = new TemplateRootNode(ctx.start.getLine(), ctx.start.getCharPositionInLine());

        if (ctx.doctype() != null) {
            root.addDocument(visit(ctx.doctype()));
        }

        root.addDocument(visit(ctx.html()));

        return root;
    }


    @Override
    public TemplateNode visitDoctype(FlaskTemplateParser.DoctypeContext ctx) {


        return new DoctypeNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.HTML_DOCTYPE().getText());
    }

    @Override
    public TemplateNode visitHtmlDocument(FlaskTemplateParser.HtmlDocumentContext ctx) {

        HTMLDocumentNode node = new HTMLDocumentNode(ctx.start.getLine(), ctx.start.getCharPositionInLine());

        // attributes
        if (ctx.htmlAttributes() != null) {
            node.addAllAttributes(visitHtmlAttributeList(ctx.htmlAttributes()));
        }

        // المحتوى الداخلي
        if (ctx.templateContent() != null) {
            node.addAllContent(visitTemplateContentList(ctx.templateContent()));
        }

        return node;
    }


    private List<TemplateNode> visitTemplateContentList(FlaskTemplateParser.TemplateContentContext ctx) {
        List<TemplateNode> list = new ArrayList<>();
        for (var node : ctx.contentItem()) {
            TemplateNode child = visit(node);
            if (child != null) { // ← هذا يمنع null
                list.add(child);
            }
        }
        return list;
    }


    @Override
    public TemplateNode visitHtmlContent(FlaskTemplateParser.HtmlContentContext ctx) {
        return visit(ctx.htmlElement());
    }

    @Override
    public TemplateNode visitNormalElement(FlaskTemplateParser.NormalElementContext ctx) {
        HTMLNormalElementNode htmlNormalElementNode = (HTMLNormalElementNode) visitOpeningTagNode((FlaskTemplateParser.OpeningTagNodeContext) ctx.openingTag());

        htmlNormalElementNode.addAllContent(visitTemplateContentList(ctx.templateContent()));

        return htmlNormalElementNode;
    }


    @Override
    public TemplateNode visitHtmlTextContent(FlaskTemplateParser.HtmlTextContentContext ctx) {

        return visitHtmlText(ctx.htmlText());
    }

    @Override
    public TemplateNode visitHtmlText(FlaskTemplateParser.HtmlTextContext ctx) {

        return new HTMLTextNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.HTML_TEXT().toString()
        );
    }

    @Override
    public TemplateNode visitOpeningTagNode(FlaskTemplateParser.OpeningTagNodeContext ctx) {
        HTMLNormalElementNode htmlElementNode = new HTMLNormalElementNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.HTML_ID().getText());

        htmlElementNode.addAllAttributes(visitHtmlAttributeList(ctx.htmlAttributes()));

        return htmlElementNode;
    }

    @Override
    public TemplateNode visitSelfClosingElementTag(FlaskTemplateParser.SelfClosingElementTagContext ctx) {


        return visitSelfClosingTagNode((FlaskTemplateParser.SelfClosingTagNodeContext) ctx.selfClosingTag());
    }

//    @Override
//    public TemplateNode visitSelfClosingTagNode(FlaskTemplateParser.SelfClosingTagNodeContext ctx) {
//
//        HTMLSelfClosingElementNode htmlElementNode = new HTMLSelfClosingElementNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.HTML_ID().getText()
//
//        );
//        htmlElementNode.addAllAttributes(visitHtmlAttributeList(ctx.htmlAttributes()));
//
//        return htmlElementNode;
//    }

    @Override
    public TemplateNode visitVoidElementTag(FlaskTemplateParser.VoidElementTagContext ctx) {


        return visitVoidTagNode((FlaskTemplateParser.VoidTagNodeContext) ctx.voidTag());
    }

    @Override
    public TemplateNode visitVoidTagNode(FlaskTemplateParser.VoidTagNodeContext ctx) {
        HTMLVoidElementNode htmlElementNode = new HTMLVoidElementNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.VOID_TAG().getText()

        );

        htmlElementNode.addAllAttributes(visitHtmlAttributeList(ctx.htmlAttributes()));
        return htmlElementNode;
    }

    private List<HTMLAttributeNode> visitHtmlAttributeList(FlaskTemplateParser.HtmlAttributesContext ctx) {

        List<HTMLAttributeNode> attrs = new ArrayList<>();

        if (ctx == null) return attrs;

        FlaskTemplateParser.HtmlAttributeListContext listCtx = (FlaskTemplateParser.HtmlAttributeListContext) ctx;

        for (FlaskTemplateParser.HtmlAttributeContext attrCtx : listCtx.htmlAttribute()) {

            attrs.add((HTMLAttributeNode) visit(attrCtx));
        }

        return attrs;
    }

    @Override
    public TemplateNode visitAttributeWithValue(FlaskTemplateParser.AttributeWithValueContext ctx) {

        String name = ctx.HTML_ID().getText();
        HTMLAttributeNode node = new HTMLAttributeNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), name);
        // إذا كانت القيمة نصية بسيطة
        node.addAllValueParts(
                visitDoubleQuotedValueList((FlaskTemplateParser.DoubleQuotedValueContext) ctx.htmlAttributeValue())
        );
        return node;
    }


    @Override
    public TemplateNode visitBooleanAttribute(FlaskTemplateParser.BooleanAttributeContext ctx) {
        HTMLAttributeNode node = new HTMLAttributeNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.HTML_BOOLEAN_ATTR().getText());
        node.setBoolean(true);
        return node;
    }

    public List<TemplateNode> visitDoubleQuotedValueList(FlaskTemplateParser.DoubleQuotedValueContext ctx) {
        if (ctx == null || ctx.attrValueContent() == null) return new ArrayList<>();
        return AttrValueContentList(ctx.attrValueContent());
    }

    @Override
    public TemplateNode visitAttrText(FlaskTemplateParser.AttrTextContext ctx) {
        return new HTMLAttributeTextNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.ATTR_VALUE_ID().getText()
        );
    }


    private List<TemplateNode> AttrValueContentList(FlaskTemplateParser.AttrValueContentContext ctx) {
        List<TemplateNode> list = new ArrayList<>();
        if (ctx == null) return list;

        for (FlaskTemplateParser.AttrValueItemContext itemCtx : ctx.attrValueItem()) {
            list.add(visit(itemCtx));
        }
        return list;
    }

    @Override
    public TemplateNode visitAttrJinjaBlockItem(FlaskTemplateParser.AttrJinjaBlockItemContext ctx) {
        return visitAttrJinjaBlock(ctx.attrJinjaBlock());
    }

    @Override
    public TemplateNode visitBlockExpression(FlaskTemplateParser.BlockExpressionContext ctx) {
        return visit(ctx.blockLogicalOrExpression());
    }

    @Override
    public TemplateNode visitBlockIdentifier(FlaskTemplateParser.BlockIdentifierContext ctx) {
        return new VariableNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.BLOCK_ID().getText());
    }

    @Override
    public TemplateNode visitBlockStringLiteral(FlaskTemplateParser.BlockStringLiteralContext ctx) {
        return new StringLiteralNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.BLOCK_STRING().getText());
    }

    @Override
    public TemplateNode visitBlockNumberLiteral(FlaskTemplateParser.BlockNumberLiteralContext ctx) {
        return new NumberLiteralNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), Double.parseDouble(ctx.BLOCK_NUMBER().getText()));
    }

    @Override
    public TemplateNode visitBlockTrueLiteral(FlaskTemplateParser.BlockTrueLiteralContext ctx) {
        return new BooleanLiteralNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), true);
    }

    @Override
    public TemplateNode visitBlockFalseLiteral(FlaskTemplateParser.BlockFalseLiteralContext ctx) {
        return new BooleanLiteralNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), false);
    }

    @Override
    public TemplateNode visitBlockNoneLiteral(FlaskTemplateParser.BlockNoneLiteralContext ctx) {
        return new NoneLiteralNode(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public TemplateNode visitBlockListLiteral(FlaskTemplateParser.BlockListLiteralContext ctx) {
        ListExpressionNode node = new ListExpressionNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        // إذا كان هناك عناصر
        if (ctx.blockExpressionList() != null) {
            for (FlaskTemplateParser.BlockExpressionContext exprCtx : ctx.blockExpressionList().blockExpression()) {
                ExpressionNode child = (ExpressionNode) visitBlockExpression(exprCtx);
                node.addElement(child);
            }
        }

        return node;
    }


    @Override
    public TemplateNode visitBlockDictLiteral(FlaskTemplateParser.BlockDictLiteralContext ctx) {
        DictExpressionNode node = new DictExpressionNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        if (ctx.blockDictPairList() != null) {
            for (FlaskTemplateParser.BlockDictPairContext pairCtx : ctx.blockDictPairList().blockDictPair()) {
                ExpressionNode key = (ExpressionNode) visitBlockExpression((FlaskTemplateParser.BlockExpressionContext) pairCtx.getChild(0));
                ExpressionNode value = (ExpressionNode) visitBlockExpression((FlaskTemplateParser.BlockExpressionContext) pairCtx.getChild(2));
                node.putPair(key, value);
            }
        }

        return node;
    }



//=========================================================================================

    @Override
    public TemplateNode visitAttrJinjaBlock(FlaskTemplateParser.AttrJinjaBlockContext ctx) {
        return visit(ctx.jinjaBlockStatement());
    }


    @Override
    public TemplateNode visitIfStart(FlaskTemplateParser.IfStartContext ctx) {
        ExpressionNode condition = (ExpressionNode) visitBlockExpression(ctx.blockExpression());
        IfBlockNode ifNode = new IfBlockNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), condition);
        if (ctx.templateContent() != null) {
            ifNode.addAllContent(visitTemplateContentList(ctx.templateContent()));
        }

        return ifNode;
    }

    @Override
    public TemplateNode visitElifBlock(FlaskTemplateParser.ElifBlockContext ctx) {
        ExpressionNode condition = (ExpressionNode) visitBlockExpression(ctx.blockExpression());

        ElifBlockNode elifNode = new ElifBlockNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), condition);

        if (ctx.templateContent() != null) {
            List<TemplateNode> content = visitTemplateContentList(ctx.templateContent());
            elifNode.addAllContent(content);
        }

        return elifNode;
    }


    @Override
    public TemplateNode visitElseBlock(FlaskTemplateParser.ElseBlockContext ctx) {

        ElseBlockNode elseNode = new ElseBlockNode(ctx.start.getLine(), ctx.start.getCharPositionInLine());

        if (ctx.templateContent() != null) {
            List<TemplateNode> content = visitTemplateContentList(ctx.templateContent());
            elseNode.addAllContent(content);
        }

        return elseNode;
    }


    @Override
    public TemplateNode visitForStart(FlaskTemplateParser.ForStartContext ctx) {
        ExpressionNode condition = (ExpressionNode) visitBlockExpression(ctx.blockExpression());

        ForBlockNode forBlockNode = new ForBlockNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.BLOCK_ID().getText(), condition);

        if (ctx.templateContent() != null) {
            List<TemplateNode> content = visitTemplateContentList(ctx.templateContent());
            forBlockNode.addAllContent(content);
        }

        return forBlockNode;
    }

    @Override
    public TemplateNode visitBlockStart(FlaskTemplateParser.BlockStartContext ctx) {
        BlockBlockNode blockBlockNode = new BlockBlockNode(ctx.start.getLine(), ctx.start.getCharPositionInLine(),ctx.BLOCK_ID().getText());
        blockBlockNode.addAllContent(visitTemplateContentList(ctx.templateContent()));
        return blockBlockNode;
    }

    @Override
    public TemplateNode visitSetBlock(FlaskTemplateParser.SetBlockContext ctx) {
        ExpressionNode condition = (ExpressionNode) visitBlockExpression(ctx.blockExpression());

        SetBlockNode setBlockNode = new SetBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.BLOCK_ID().getText(),
                condition
        );

        return setBlockNode;
    }

    @Override
    public TemplateNode visitIncludeBlock(FlaskTemplateParser.IncludeBlockContext ctx) {
        IncludeBlockNode includeBlockNode = new IncludeBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.BLOCK_STRING().getText()
        );

        return includeBlockNode;
    }

    @Override
    public TemplateNode visitImportBlock(FlaskTemplateParser.ImportBlockContext ctx) {
        String templateName = ctx.BLOCK_STRING().getText();
        String alias = null;

        if (ctx.BLOCK_ID() != null) {
            alias = ctx.BLOCK_ID().getText();
        }

        ImportBlockNode importBlockNode = new ImportBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                templateName,
                alias
        );

        return importBlockNode;
    }


    @Override
    public TemplateNode visitFromImportBlock(FlaskTemplateParser.FromImportBlockContext ctx) {

        FromImportBlockNode fromImportBlockNode = new FromImportBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.BLOCK_STRING().getText()
        );

        for (var node : ctx.importList().BLOCK_ID()){
            fromImportBlockNode.addImport(node.getText());
        }
        return fromImportBlockNode;
    }


    @Override
    public TemplateNode visitWithStart(FlaskTemplateParser.WithStartContext ctx) {
        ExpressionNode condition = (ExpressionNode) visitBlockExpression(ctx.blockExpression());

        WithBlockNode withBlockNode = new WithBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                condition
        );
        return  withBlockNode;
    }

    @Override
    public TemplateNode visitExtendsBlock(FlaskTemplateParser.ExtendsBlockContext ctx) {

        ExtendsBlockNode extendsBlockNode = new ExtendsBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.BLOCK_STRING().getText()
        );
        return extendsBlockNode;
    }

    @Override
    public TemplateNode visitGenericBlock(FlaskTemplateParser.GenericBlockContext ctx) {

        GenericBlockNode genericBlockNode = new GenericBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.BLOCK_ID().getText()
        );

        genericBlockNode.setExpression((ExpressionNode) visitBlockExpression(ctx.blockExpression()));
        genericBlockNode.addAllContent(visitTemplateContentList(ctx.templateContent()));
        return genericBlockNode;
    }


    //=========================================================================================


    @Override
    public TemplateNode visitAttrJinjaExprItem(FlaskTemplateParser.AttrJinjaExprItemContext ctx) {

        ExpressionNode expressionNode = (ExpressionNode) visitAttrJinjaExpr(ctx.attrJinjaExpr());
        return new JinjaExpressionNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                expressionNode
        );
    }

    @Override
    public TemplateNode visitAttrJinjaExpr(FlaskTemplateParser.AttrJinjaExprContext ctx) {
        return visitAttrJinjaExprContent(ctx.attrJinjaExprContent());
    }

    @Override
    public TemplateNode visitAttrJinjaExprContent(FlaskTemplateParser.AttrJinjaExprContentContext ctx) {
        return visitJinjaExpression(ctx.jinjaExpression());
    }

    @Override
    public TemplateNode visitJinjaExpression(FlaskTemplateParser.JinjaExpressionContext ctx) {

        return visitExpressionRoot((FlaskTemplateParser.ExpressionRootContext) ctx.expression());
    }

    @Override
    public TemplateNode visitExpressionRoot(FlaskTemplateParser.ExpressionRootContext ctx) {
        return visit(ctx.logicalOrExpression());
    }

    @Override
    public TemplateNode visitUnaryExpr(FlaskTemplateParser.UnaryExprContext ctx) {

        // إذا لم يوجد عامل ( + - not )
        if (ctx.getChildCount() == 1) {
            return visit(ctx.primaryExpression());
        }

        String operator = ctx.getChild(0).getText();
        ExpressionNode operand =
                (ExpressionNode) visit(ctx.primaryExpression());

        return new UnaryExpressionNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                operator,
                operand
        );
    }




    @Override
    public TemplateNode visitIdentifierExpr(FlaskTemplateParser.IdentifierExprContext ctx) {

        return new VariableNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.EXPR_ID().toString()
        );
    }



    @Override
    public TemplateNode visitStringExpr(FlaskTemplateParser.StringExprContext ctx) {
        String raw = ctx.EXPR_STRING().getText();

        // إزالة علامات الاقتباس
        String value = raw.substring(1, raw.length() - 1);

        return new StringLiteralNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                value
        );
    }


    @Override
    public TemplateNode visitNumberExpr(FlaskTemplateParser.NumberExprContext ctx) {

        String text = ctx.EXPR_NUMBER().getText();
        Number value;

        if (text.contains(".")) {
            value = Double.parseDouble(text);
        } else {
            value = Integer.parseInt(text);
        }

        return new NumberLiteralNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                value
        );
    }


    @Override
    public TemplateNode visitTrueExpr(FlaskTemplateParser.TrueExprContext ctx) {
        return new BooleanLiteralNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                true
        );
    }

    @Override
    public TemplateNode visitFalseExpr(FlaskTemplateParser.FalseExprContext ctx) {
        return new BooleanLiteralNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                false
        );
    }


    @Override
    public TemplateNode visitNoneExpr(FlaskTemplateParser.NoneExprContext ctx) {
        return new NoneLiteralNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );
    }


    @Override
    public TemplateNode visitListExpr(FlaskTemplateParser.ListExprContext ctx) {

        ListExpressionNode node =
                new ListExpressionNode(ctx.start.getLine(), ctx.start.getCharPositionInLine());

        node.addAllElement(visitExprListList((FlaskTemplateParser.ExprListContext) ctx.expressionList()));

        return node;
    }

    public List<ExpressionNode> visitExprListList(FlaskTemplateParser.ExprListContext ctx) {
        List<ExpressionNode> list = new ArrayList<>();

        for (var node : ctx.expression()){
            list.add((ExpressionNode) visitExpressionRoot((FlaskTemplateParser.ExpressionRootContext) node));
        }
        return list;
    }

    @Override
    public TemplateNode visitDictExpr(FlaskTemplateParser.DictExprContext ctx) {

        return visitDictPairListNode((FlaskTemplateParser.DictPairListNodeContext) ctx.dictPairList());
    }

    @Override
    public TemplateNode visitDictPairListNode(FlaskTemplateParser.DictPairListNodeContext ctx) {

        DictExpressionNode node = new DictExpressionNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        if (ctx.dictPair() != null) {
            for (FlaskTemplateParser.DictPairContext pairCtx
                    : ctx.dictPair()) {

                List<ExpressionNode> list =
                        visitDictPairNode2((FlaskTemplateParser.DictPairNodeContext) pairCtx);

                ExpressionNode key =
                        list.get(0);

                ExpressionNode value =
                        list.get(1);

                node.putPair(key, value);
            }
        }

        return node;
    }

    public List<ExpressionNode> visitDictPairNode2(FlaskTemplateParser.DictPairNodeContext ctx) {
        List<ExpressionNode> list = new ArrayList<>();

        list.add((ExpressionNode) visitExpressionRoot((FlaskTemplateParser.ExpressionRootContext) ctx.getChild(0)));
        list.add((ExpressionNode) visitExpressionRoot((FlaskTemplateParser.ExpressionRootContext) ctx.getChild(2)));

        return list;
    }

    @Override
    public TemplateNode visitFilterExpr(FlaskTemplateParser.FilterExprContext ctx) {
        return super.visitFilterExpr(ctx);
    }

    @Override
    public TemplateNode visitCallExpr(FlaskTemplateParser.CallExprContext ctx) {
        // 1. Callee: دائماً EXPR_ID
        VariableNode callee = new VariableNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.EXPR_ID().getText()
        );

        // 2. إنشاء العقدة
        CallExpressionNode node = new CallExpressionNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                callee
        );

        node.addAllArguments(visitArgList2((FlaskTemplateParser.ArgListContext) ctx.argumentList()));

        return node;
    }


    public  List<ExpressionNode> visitArgList2(FlaskTemplateParser.ArgListContext ctx) {
        List<ExpressionNode> list = new ArrayList<>();
        for (var node : ctx.expression()){
            list.add((ExpressionNode) visitExpressionRoot((FlaskTemplateParser.ExpressionRootContext) node));
        }
        return list;
    }


    @Override
    public TemplateNode visitJinjaExprNode(FlaskTemplateParser.JinjaExprNodeContext ctx) {
        return visitJinjaExpression((FlaskTemplateParser.JinjaExpressionContext) ctx.jinjaExpression());
    }

    @Override
    public TemplateNode visitJinjaBlockNode(FlaskTemplateParser.JinjaBlockNodeContext ctx) {
        return visit(ctx.jinjaBlockStatement());
    }

    @Override
    public TemplateNode visitCssContent(FlaskTemplateParser.CssContentContext ctx) {
        return visitStyleWithAttributes((FlaskTemplateParser.StyleWithAttributesContext) ctx.cssStyle());
    }

    @Override
    public TemplateNode visitStyleWithAttributes(FlaskTemplateParser.StyleWithAttributesContext ctx) {
        CSSStyleNode cssStyleNode = new CSSStyleNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        // يجب التحقق من null
        if (ctx.cssTagAttributes() != null) {
            cssStyleNode.addAttributes(getCssTagAttributes(ctx.cssTagAttributes()));
        }

        if (ctx.cssStyleContent() != null) {
            cssStyleNode.addRules(getCssStyleContent(ctx.cssStyleContent()));
        }

        return cssStyleNode; // يجب إرجاع cssStyleNode بدلاً من super
    }

    public List<CSSAttributeNode> getCssTagAttributes(FlaskTemplateParser.CssTagAttributesContext ctx) {
        List<CSSAttributeNode> list = new ArrayList<>();

        if (ctx == null) return list;

        try {
            for (var node : ctx.cssTagAttribute()) {
                CSSAttributeNode attr = (CSSAttributeNode) visitCssTagAttrNode(
                        (FlaskTemplateParser.CssTagAttrNodeContext) node
                );
                if (attr != null) {
                    list.add(attr);
                }
            }
        } catch (Exception e) {
            // تسجيل الخطأ مع الاستمرار
            System.err.println("Error processing CSS attributes: " + e.getMessage());
        }

        return list;
    }

    @Override
    public TemplateNode visitCssTagAttrNode(FlaskTemplateParser.CssTagAttrNodeContext ctx) {
        CSSAttributeNode cssAttributeNode = new CSSAttributeNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_TAG_ATTR().getText()
        );
        cssAttributeNode.setValue(ctx.CSS_TAG_STRING().getText());
        return cssAttributeNode;
    }


    public List<CSSRuleNode> getCssStyleContent(FlaskTemplateParser.CssStyleContentContext ctx) {
        List<CSSRuleNode> list = new ArrayList<>();

        for (var node : ctx.cssRule()){
            list.add((CSSRuleNode) visitCssRuleNode((FlaskTemplateParser.CssRuleNodeContext) node));
        }
        return list;
    }

    @Override
    public TemplateNode visitCssRuleNode(FlaskTemplateParser.CssRuleNodeContext ctx) {

        CSSRuleNode cssRuleNode = new CSSRuleNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        cssRuleNode.addAllSelectors(getCssSelectorExpr((FlaskTemplateParser.CssSelectorExprContext) ctx.cssSelectors()));
        cssRuleNode.addAllDeclarations( getCssDeclarationExpr((FlaskTemplateParser.CssDeclarationListContext) ctx.cssDeclarations()));
        return cssRuleNode;
    }

    public List<CSSSelectorNode> getCssSelectorExpr(FlaskTemplateParser.CssSelectorExprContext ctx) {

        List<CSSSelectorNode> list = new ArrayList<>();

        for (var node : ctx.cssSelector()){
            list.add((CSSSelectorNode) visitCssSelectorNode((FlaskTemplateParser.CssSelectorNodeContext) node));
        }
        return list;
    }

    @Override
    public TemplateNode visitCssSelectorNode(FlaskTemplateParser.CssSelectorNodeContext ctx) {

        return new CSSSelectorNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_CONTENT().toString()
        );
    }

    public List<CSSDeclarationNode> getCssDeclarationExpr(FlaskTemplateParser.CssDeclarationListContext ctx) {
        List<CSSDeclarationNode> list = new ArrayList<>();

        for (var node : ctx.cssDeclaration()){
            list.add((CSSDeclarationNode) visitCssDeclarationNode((FlaskTemplateParser.CssDeclarationNodeContext) node));
        }
        return list;
    }



    @Override
    public TemplateNode visitCssDeclarationNode(FlaskTemplateParser.CssDeclarationNodeContext ctx) {
        CSSDeclarationNode cssDeclarationNode = new CSSDeclarationNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_PROPERTY().getText()
        );

        if (ctx.cssValues() != null) {
            cssDeclarationNode.addAllValues(
                    getCssValueList((FlaskTemplateParser.CssValueListContext) ctx.cssValues())
            );
        }

        return cssDeclarationNode; // إرجاع العقدة بدلاً من super
    }

    public List<CSSValueNode> getCssValueList(FlaskTemplateParser.CssValueListContext ctx) {
        List<CSSValueNode> list = new ArrayList<>();

        if (ctx == null) return list;

        for (var node : ctx.cssValue()) {
            CSSValueNode valueNode = (CSSValueNode) visit(node);
            if (valueNode != null) {
                list.add(valueNode);
            }
        }
        return list;
    }

    @Override
    public TemplateNode visitCssStringValue(FlaskTemplateParser.CssStringValueContext ctx) {

        return new CSSStringValueNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_STRING().getText()
        );
    }

    @Override
    public TemplateNode visitCssNumericValue(FlaskTemplateParser.CssNumericValueContext ctx) {
        return new CSSNumericValueNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_NUMERIC().getText()
        );
    }

    @Override
    public TemplateNode visitCssColorValue(FlaskTemplateParser.CssColorValueContext ctx) {
        return new CSSColorValueNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_COLOR().getText()
        );
    }

    @Override
    public TemplateNode visitCssKeywordValue(FlaskTemplateParser.CssKeywordValueContext ctx) {
        return new CSSKeywordValueNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                ctx.CSS_KEYWORD().getText()
        );
    }
}