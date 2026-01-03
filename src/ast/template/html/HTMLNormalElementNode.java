package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
import java.util.stream.Collectors;

public final class HTMLNormalElementNode extends HTMLElementNode {
    private List<TemplateNode> content;

    public HTMLNormalElementNode(int line, int column, String tagName) {
        super(NodeKind.HTML_ELEMENT, line, column, tagName);
        this.content = new ArrayList<>();
    }

    // Getters
    public List<TemplateNode> getContent() {
        return Collections.unmodifiableList(content);
    }

    // Add methods
    public void addContent(TemplateNode content) {
        if (content != null) {
            this.content.add(content);
        }
    }

    public void addAllContent(List<TemplateNode> content) {
        if (content != null) {
            this.content.addAll(content);
        }
    }

    // Remove methods
    public boolean removeContent(TemplateNode content) {
        return this.content.remove(content);
    }

    // Utility methods
    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public void clearContent() {
        this.content.clear();
    }

    public <T extends TemplateNode> List<T> getContentByType(Class<T> type) {
        return content.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("HTMLNormalElementNode{tag='%s', line=%d, column=%d, attributes=%d, content=%d}",
                getTagName(), getLine(), getColumn(), getAttributes().size(), content.size());
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

