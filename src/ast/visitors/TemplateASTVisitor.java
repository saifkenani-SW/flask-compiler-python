package ast.visitors;

import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import ast.template.css.*;
import ast.template.html.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;

public interface TemplateASTVisitor<T> {

    // ================= HTML =================
    T visit(TemplateRootNode node);

    T visit(HTMLDocumentNode node);
    T visit(HTMLNormalElementNode node);
    T visit(HTMLVoidElementNode node);
    T visit(HTMLSelfClosingElementNode node);
    T visit(HTMLTextNode node);
    T visit(HTMLAttributeNode node);

    // ================= Jinja Expressions =================
    T visit(JinjaExpressionNode node);
    T visit(VariableNode node);
    T visit(NumberLiteralNode node);
    T visit(StringLiteralNode node);
    T visit(BooleanLiteralNode node);
    T visit(NoneLiteralNode node);
    T visit(ListExpressionNode node);
    T visit(DictExpressionNode node);
    T visit(BinaryExpressionNode node);
    T visit(UnaryExpressionNode node);
    T visit(CallExpressionNode node);
    T visit(FilterExpressionNode node);
    T visit(AttributeAccessNode node);

    // ================= Jinja Blocks =================
    T visit(SetBlockNode node);
    T visit(WithBlockNode node);
    T visit(IncludeBlockNode node);
    T visit(ImportBlockNode node);
    T visit(FromImportBlockNode node);
    T visit(IfBlockNode node);
    T visit(ElifBlockNode node);
    T visit(ElseBlockNode node);
    T visit(ForBlockNode node);
    T visit(ExtendsBlockNode node);
    T visit(BlockBlockNode node);
    T visit(GenericBlockNode node);


    // ================= CSS =================
    T visit(CSSStyleNode node);
    T visit(CSSRuleNode node);
    T visit(CSSSelectorNode node);
    T visit(CSSDeclarationNode node);

    T visit(CSSColorValueNode node);
    T visit(CSSNumericValueNode node);
    T visit(CSSStringValueNode node);
    T visit(CSSKeywordValueNode node);


    T visit(CSSAttributeNode cssAttributeNode);

    T visit(HTMLClosingTagNode htmlClosingTagNode);

    T visit(DoctypeNode doctypeNode);
}
