package ast.template.html;


import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class HTMLTextNode extends HTMLNode {
    private String text;

    public HTMLTextNode(int line, int column, String text) {
        super(NodeKind.HTML_TEXT, line, column);
        this.text = text;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }


    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
