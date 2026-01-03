package ast.template.html;

import ast.template.NodeKind;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract sealed class HTMLElementNode extends HTMLNode
        permits HTMLNormalElementNode, HTMLSelfClosingElementNode, HTMLVoidElementNode {
    private String tagName;
    protected List<HTMLAttributeNode> attributes;

    // Constructor
    public HTMLElementNode(NodeKind nodeKind, int line, int column, String tagName) {
        super(nodeKind, line, column);
        this.tagName = tagName;
        this.attributes = new ArrayList<>();
    }

    // Getters
    public String getTagName() {
        return tagName;
    }

    public List<HTMLAttributeNode> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public Optional<HTMLAttributeNode> getAttribute(String name) {
        return attributes.stream()
                .filter(attr -> attr.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // Setters
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    // Add methods
    public void addAttribute(HTMLAttributeNode attribute) {
        if (attribute != null) {
            this.attributes.add(attribute);
        }
    }

    public void addAllAttributes(List<HTMLAttributeNode> attributes) {
        if (attributes != null) {
            this.attributes.addAll(attributes);
        }
    }

    // Remove methods
    public boolean removeAttribute(HTMLAttributeNode attribute) {
        return this.attributes.remove(attribute);
    }

    public boolean removeAttribute(String name) {
        return this.attributes.removeIf(attr -> attr.getName().equalsIgnoreCase(name));
    }

    // Utility methods
    public boolean hasAttribute(String name) {
        return attributes.stream()
                .anyMatch(attr -> attr.getName().equalsIgnoreCase(name));
    }

    public boolean hasAttributes() {
        return !this.attributes.isEmpty();
    }

    public void clearAttributes() {
        this.attributes.clear();
    }



    @Override
    public String toString() {
        return String.format("%s{tag='%s', line=%d, column=%d, attributes=%d}",
                getClass().getSimpleName(), tagName, getLine(), getColumn(), attributes.size());
    }
}

