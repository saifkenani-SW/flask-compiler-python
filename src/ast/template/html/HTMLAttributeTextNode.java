package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class HTMLAttributeTextNode extends HTMLNode {

    private final String text;

    public HTMLAttributeTextNode(int line, int column, String text) {
        super(NodeKind.HTML_ATTRIBUTE_TEXT, line, column);
        this.text = text;
    }

    public String getText() {
        return text;
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
