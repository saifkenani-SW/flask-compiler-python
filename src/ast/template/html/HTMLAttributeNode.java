package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.expressions.JinjaExpressionNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HTMLAttributeNode extends HTMLNode {

    private final String name;
    private boolean isBoolean;

    // القيمة دائمًا قائمة عقد
    private final List<TemplateNode> valueParts = new ArrayList<>();

    public HTMLAttributeNode(int line, int column, String name) {
        super(NodeKind.HTML_ATTRIBUTE, line, column);
        this.name = name;
    }

    // --------- API نظيف ---------

    public void addValuePart(TemplateNode node) {
        if (node != null) {
            valueParts.add(node);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" = [");
        for (int i = 0; i < valueParts.size(); i++) {
            TemplateNode node = valueParts.get(i);
            if (node instanceof HTMLAttributeTextNode textNode) {
                sb.append(textNode.getText());
            } else if (node instanceof JinjaExpressionNode jinjaNode) {
                sb.append("{{ ").append(jinjaNode.getExpression()).append(" }}");
            } else {
                sb.append(node.toString());
            }
            if (i < valueParts.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }



    public void addAllValueParts(List<TemplateNode> nodes) {
        if (nodes != null) {
            nodes.forEach(this::addValuePart);
        }
    }

    public List<TemplateNode> getValueParts() {
        return Collections.unmodifiableList(valueParts);
    }

    public String getName() {
        return name;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    public void setBoolean(boolean isBoolean) {
        this.isBoolean = isBoolean;
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return null;
    }
}
