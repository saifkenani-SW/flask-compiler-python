package ast.visitors;

import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import ast.template.css.*;
import ast.template.html.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;

public interface TemplateASTVisitor<T> {
    // HTML Nodes
    T visit(TemplateRootNode node);
    T visit(DoctypeNode node);
    T visit(HTMLDocumentNode node);
    T visit(HTMLNormalElementNode node);
    T visit(HTMLSelfClosingElementNode node);
    T visit(HTMLVoidElementNode node);
    T visit(HTMLAttributeNode node);
    T visit(HTMLTextNode node);
    T visit(HTMLAttributeTextNode node);

    // CSS Nodes
    T visit(CSSRuleNode node);
    T visit(CSSSelectorNode node);
    T visit(CSSDeclarationNode node);
    T visit(CSSStringValueNode node);
    T visit(CSSNumericValueNode node);
    T visit(CSSKeywordValueNode node);
    T visit(CSSColorValueNode node);
    T visit(CSSAttributeNode node);

    // Jinja Expression Nodes
    T visit(JinjaExpressionNode node);
    T visit(VariableNode node);
    T visit(StringLiteralNode node);
    T visit(NumberLiteralNode node);
    T visit(BooleanLiteralNode node);
    T visit(NoneLiteralNode node);
    T visit(UnaryExpressionNode node);
    T visit(BinaryExpressionNode node);
    T visit(CallExpressionNode node);
    T visit(AttributeAccessNode node);
    T visit(FilterExpressionNode node);
    T visit(ListExpressionNode node);
    T visit(DictExpressionNode node);

    // Jinja Block Nodes
    T visit(IfBlockNode node);
    T visit(ElifBlockNode node);
    T visit(ElseBlockNode node);
    T visit(ForBlockNode node);
    T visit(BlockBlockNode node);
    T visit(SetBlockNode node);
    T visit(IncludeBlockNode node);
    T visit(ImportBlockNode node);
    T visit(FromImportBlockNode node);
    T visit(WithBlockNode node);
    T visit(ExtendsBlockNode node);
    T visit(GenericBlockNode node);
}