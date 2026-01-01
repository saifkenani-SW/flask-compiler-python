package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class HTMLClosingTagNode extends HTMLNode {
    private String tagName;

    public HTMLClosingTagNode(int line, int column, String tagName) {
        super(NodeKind.HTML_Closing_Tag, line, column);
        this.tagName = tagName;
    }

    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
