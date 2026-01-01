package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;

public final class HTMLNormalElementNode extends HTMLElementNode {
    private List<TemplateNode> content;

    public HTMLNormalElementNode(int line, int column, String tagName) {
        super(NodeKind.HTML_ELEMENT, line, column, tagName); // Normal element
        this.content = new ArrayList<>();
    }



    public List<TemplateNode> getContent() { return content; }
    public void addContent(TemplateNode contentItem) { content.add(contentItem); }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        children.addAll(attributes);
        children.addAll(content);
        return children;
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

