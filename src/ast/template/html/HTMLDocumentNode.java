package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HTMLDocumentNode extends HTMLNode {
    private List<HTMLAttributeNode> attributes;
    private List<TemplateNode> content;

    // Constructors
    public HTMLDocumentNode(int line, int column) {
        super(NodeKind.HTML_DOCUMENT, line, column);
        this.attributes = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    // Getters
//    public DoctypeNode getDoctype() {
//        return doctype;
//    }

    public List<HTMLAttributeNode> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public List<TemplateNode> getContent() {
        return Collections.unmodifiableList(content);
    }

    // Setters
//    public void setDoctype(DoctypeNode doctype) {
//        this.doctype = doctype;
//    }

    // Add methods
    public void addAttribute(HTMLAttributeNode attribute) {
        if (attribute != null) {
            this.attributes.add(attribute);
        }
    }

    public void addContent(TemplateNode content) {
        if (content != null) {
            this.content.add(content);
        }
    }

    public void addAllAttributes(List<HTMLAttributeNode> attributes) {
        if (attributes != null) {
            this.attributes.addAll(attributes);
        }
    }

    public void addAllContent(List<TemplateNode> content) {
        if (content != null) {
            this.content.addAll(content);
        }
    }

    // Remove methods
    public boolean removeAttribute(HTMLAttributeNode attribute) {
        return this.attributes.remove(attribute);
    }

    public boolean removeContent(TemplateNode content) {
        return this.content.remove(content);
    }

    // Utility methods
    public boolean hasAttributes() {
        return !this.attributes.isEmpty();
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

//    public boolean hasDoctype() {
//        return this.doctype != null;
//    }

    public void clearAttributes() {
        this.attributes.clear();
    }

    public void clearContent() {
        this.content.clear();
    }

//    @Override
//    public String toString() {
//        return String.format("HTMLDocumentNode{line=%d, column=%d, doctype=%s, attributes=%d, content=%d}",
//                getLine(), getColumn(),
//                doctype != null ? "present" : "absent",
//                attributes.size(), content.size());
//    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return null;
    }
}