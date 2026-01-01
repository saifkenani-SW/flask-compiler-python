package ast.template.html;

import ast.template.NodeKind;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract sealed class HTMLElementNode extends HTMLNode permits HTMLNormalElementNode, HTMLSelfClosingElementNode, HTMLVoidElementNode {
    private String tagName;
    protected List<HTMLAttributeNode> attributes;

    public HTMLElementNode(NodeKind nodeKind, int line, int column, String tagName) {
        super(nodeKind, line, column);
        this.tagName = tagName;
        this.attributes = new ArrayList<>();
    }

    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }

    public List<HTMLAttributeNode> getAttributes() { return attributes; }
    public void addAttribute(HTMLAttributeNode attribute) {
        attributes.add(attribute);
    }

}

