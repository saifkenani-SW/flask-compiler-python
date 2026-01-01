package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class HTMLDocumentNode extends HTMLNode {
    private List<HTMLAttributeNode> attributes;
    private List<TemplateNode> content;

    public HTMLDocumentNode(int line, int column) {
        super(NodeKind.HTML_DOCUMENT, line, column);
        this.attributes = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public List<HTMLAttributeNode> getAttributes() { return attributes; }
    public void addAttribute(HTMLAttributeNode attribute) {
        attributes.add(attribute);
    }

    public List<TemplateNode> getContent() { return content; }
    public void addContent(TemplateNode contentItem) {
        content.add(contentItem);
    }

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
