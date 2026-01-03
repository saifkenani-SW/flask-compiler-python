package ast.visitors;

import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import ast.template.css.*;
import ast.template.html.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;

public class PrintASTVisitor implements TemplateASTVisitor<String> {

    private int indentLevel = 0;
    private final StringBuilder output = new StringBuilder();

    private void indent() {
        for (int i = 0; i < indentLevel; i++) {
            output.append("  ");
        }
    }

    @Override
    public String visit(TemplateRootNode node) {
        indent();
        output.append("TemplateRootNode\n");
        indentLevel++;
        for (TemplateNode child : node.getChildren()) {
            child.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(DoctypeNode node) {
        indent();
        output.append("DoctypeNode: ").append(node.getDoctype()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(HTMLDocumentNode node) {
        indent();
        output.append("HTMLDocumentNode");
        if (!node.getAttributes().isEmpty()) {
            output.append(" [attributes: ").append(node.getAttributes().size()).append("]");
        }
        output.append("\n");

        indentLevel++;
        for (HTMLAttributeNode attr : node.getAttributes()) {
            attr.accept(this);
        }

        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;

        return output.toString();
    }

    @Override
    public String visit(HTMLNormalElementNode node) {
        indent();
        output.append("HTMLNormalElementNode: <").append(node.getTagName()).append(">");
        if (!node.getAttributes().isEmpty()) {
            output.append(" [attributes: ").append(node.getAttributes().size()).append("]");
        }
        if (!node.getContent().isEmpty()) {
            output.append(" [children: ").append(node.getContent().size()).append("]");
        }
        output.append("\n");

        indentLevel++;
        for (HTMLAttributeNode attr : node.getAttributes()) {
            attr.accept(this);
        }

        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;

        return output.toString();
    }

    @Override
    public String visit(HTMLSelfClosingElementNode node) {
        indent();
        output.append("HTMLSelfClosingElementNode: <").append(node.getTagName()).append("/>");
        if (!node.getAttributes().isEmpty()) {
            output.append(" [attributes: ").append(node.getAttributes().size()).append("]");
        }
        output.append("\n");

        indentLevel++;
        for (HTMLAttributeNode attr : node.getAttributes()) {
            attr.accept(this);
        }
        indentLevel--;

        return output.toString();
    }

    @Override
    public String visit(HTMLVoidElementNode node) {
        indent();
        output.append("HTMLVoidElementNode: <").append(node.getTagName()).append(">");
        if (!node.getAttributes().isEmpty()) {
            output.append(" [attributes: ").append(node.getAttributes().size()).append("]");
        }
        output.append("\n");

        indentLevel++;
        for (HTMLAttributeNode attr : node.getAttributes()) {
            attr.accept(this);
        }
        indentLevel--;

        return output.toString();
    }

    @Override
    public String visit(HTMLAttributeNode node) {
        indent();
        output.append("HTMLAttributeNode: ").append(node.getName());

        if (node.isBoolean()) {
            output.append(" (boolean)");
        } else if (!node.getValueParts().isEmpty()) {
            output.append(" = ");
            for (TemplateNode part : node.getValueParts()) {
                if (part instanceof HTMLAttributeTextNode) {
                    output.append("'").append(((HTMLAttributeTextNode) part).getText()).append("' ");
                } else if (part instanceof JinjaExpressionNode) {
                    output.append("{{").append(((JinjaExpressionNode) part).getExpression()).append("}} ");
                }
            }
        }
        output.append("\n");

        return output.toString();
    }

    @Override
    public String visit(HTMLTextNode node) {
        indent();
        String text = node.getText();
        if (text.length() > 30) {
            text = text.substring(0, 27) + "...";
        }
        output.append("HTMLTextNode: \"").append(text.replace("\n", "\\n")).append("\"\n");
        return output.toString();
    }

    @Override
    public String visit(HTMLAttributeTextNode node) {
        indent();
        output.append("HTMLAttributeTextNode: '").append(node.getText()).append("'\n");
        return output.toString();
    }

    @Override
    public String visit(JinjaExpressionNode node) {
        indent();
        output.append("JinjaExpressionNode: {{ ");
        ExpressionNode expr = node.getExpression();
        if (expr != null) {
            expr.accept(this);
        }
        output.append(" }}\n");
        return output.toString();
    }

    @Override
    public String visit(VariableNode node) {
        indent();
        output.append("VariableNode: ").append(String.join(".", node.getPath())).append("\n");
        return output.toString();
    }

    @Override
    public String visit(StringLiteralNode node) {
        indent();
        output.append("StringLiteralNode: \"").append(node.getValue()).append("\"\n");
        return output.toString();
    }

    @Override
    public String visit(NumberLiteralNode node) {
        indent();
        output.append("NumberLiteralNode: ").append(node.getValue()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(BooleanLiteralNode node) {
        indent();
        output.append("BooleanLiteralNode: ").append(node.getValue()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(NoneLiteralNode node) {
        indent();
        output.append("NoneLiteralNode: None\n");
        return output.toString();
    }

    @Override
    public String visit(UnaryExpressionNode node) {
        indent();
        output.append("UnaryExpressionNode: ").append(node.getOperator()).append("\n");
        indentLevel++;
        node.getOperand().accept(this);
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(BinaryExpressionNode node) {
        indent();
        output.append("BinaryExpressionNode: ").append(node.getOperator()).append("\n");
        indentLevel++;
        node.getLeft().accept(this);
        node.getRight().accept(this);
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(CallExpressionNode node) {
        indent();
        output.append("CallExpressionNode\n");
        indentLevel++;
        node.getCallee().accept(this);
        for (ExpressionNode arg : node.getArguments()) {
            arg.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(AttributeAccessNode node) {
        indent();
        output.append("AttributeAccessNode: ").append(node.getAttribute()).append("\n");
        indentLevel++;
        node.getObject().accept(this);
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(FilterExpressionNode node) {
        indent();
        output.append("FilterExpressionNode: |").append(node.getFilterName()).append("\n");
        indentLevel++;
        node.getInput().accept(this);
        for (ExpressionNode arg : node.getArguments()) {
            arg.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(ListExpressionNode node) {
        indent();
        output.append("ListExpressionNode [size: ").append(node.getElements().size()).append("]\n");
        indentLevel++;
        for (ExpressionNode elem : node.getElements()) {
            elem.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(DictExpressionNode node) {
        indent();
        output.append("DictExpressionNode [size: ").append(node.getPairs().size()).append("]\n");
        indentLevel++;
        for (var entry : node.getPairs().entrySet()) {
            entry.getKey().accept(this);
            entry.getValue().accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(IfBlockNode node) {
        indent();
        output.append("IfBlockNode\n");
        indentLevel++;
        node.getCondition().accept(this);
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }

        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(ElifBlockNode node) {
        indent();
        output.append("ElifBlockNode\n");
        indentLevel++;
        node.getCondition().accept(this);
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(ElseBlockNode node) {
        indent();
        output.append("ElseBlockNode\n");
        indentLevel++;
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(ForBlockNode node) {
        indent();
        output.append("ForBlockNode: ").append(node.getVariable()).append(" in\n");
        indentLevel++;
        node.getIterable().accept(this);
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        if (node.getElseBlock() != null) {
            node.getElseBlock().accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(BlockBlockNode node) {
        indent();
        output.append("BlockBlockNode: ").append(node.getBlockName()).append("\n");
        indentLevel++;
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(SetBlockNode node) {
        indent();
        output.append("SetBlockNode: ").append(node.getVariable()).append(" =\n");
        indentLevel++;
        node.getExpression().accept(this);
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(IncludeBlockNode node) {
        indent();
        output.append("IncludeBlockNode: ").append(node.getTemplateName()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(ImportBlockNode node) {
        indent();
        output.append("ImportBlockNode: ").append(node.getTemplateName());
        if (node.getAlias() != null) {
            output.append(" as ").append(node.getAlias());
        }
        output.append("\n");
        return output.toString();
    }

    @Override
    public String visit(FromImportBlockNode node) {
        indent();
        output.append("FromImportBlockNode: from ").append(node.getTemplateName()).append(" import ");
        output.append(String.join(", ", node.getImports())).append("\n");
        return output.toString();
    }

    @Override
    public String visit(WithBlockNode node) {
        indent();
        output.append("WithBlockNode\n");
        indentLevel++;
        node.getExpression().accept(this);
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;
        return output.toString();
    }

    @Override
    public String visit(ExtendsBlockNode node) {
        indent();
        output.append("ExtendsBlockNode: ").append(node.getTemplateName()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(GenericBlockNode node) {
        indent();
        output.append("GenericBlockNode: ").append(node.getBlockName());
        if (node.getExpression() != null) {
            output.append(" = ");
        }
        output.append("\n");

        indentLevel++;
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        }
        for (TemplateNode child : node.getContent()) {
            child.accept(this);
        }
        indentLevel--;

        return output.toString();
    }



    @Override
    public String visit(CSSRuleNode node) {
        indent();
        output.append("CSSRuleNode [selectors: ").append(node.getSelectors().size());
        output.append(", declarations: ").append(node.getDeclarations().size()).append("]\n");

        indentLevel++;
        for (CSSSelectorNode selector : node.getSelectors()) {
            selector.accept(this);
        }
        for (CSSDeclarationNode decl : node.getDeclarations()) {
            decl.accept(this);
        }
        indentLevel--;

        return output.toString();
    }

    @Override
    public String visit(CSSSelectorNode node) {
        indent();
        output.append("CSSSelectorNode: ").append(node.getSelector()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(CSSDeclarationNode node) {
        indent();
        output.append("CSSDeclarationNode: ").append(node.getProperty()).append("\n");

        indentLevel++;
        for (CSSValueNode value : node.getValues()) {
            value.accept(this);
        }
        indentLevel--;

        return output.toString();
    }

    @Override
    public String visit(CSSStringValueNode node) {
        indent();
        output.append("CSSStringValueNode: \"").append(node.getValue()).append("\"\n");
        return output.toString();
    }

    @Override
    public String visit(CSSNumericValueNode node) {
        indent();
        output.append("CSSNumericValueNode: ").append(node.getValue()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(CSSKeywordValueNode node) {
        indent();
        output.append("CSSKeywordValueNode: ").append(node.getValue()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(CSSColorValueNode node) {
        indent();
        output.append("CSSColorValueNode: ").append(node.getValue()).append("\n");
        return output.toString();
    }

    @Override
    public String visit(CSSAttributeNode node) {
        indent();
        output.append("CSSAttributeNode: ").append(node.getName()).append(" = ").append(node.getValue()).append("\n");
        return output.toString();
    }



    public String getOutput() {
        return output.toString();
    }

    public void clear() {
        output.setLength(0);
        indentLevel = 0;
    }
}