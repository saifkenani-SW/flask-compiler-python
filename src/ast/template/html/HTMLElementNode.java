package ast.template.html;

import ast.template.TemplateNode;

import java.util.ArrayList;
import java.util.List;

public class HTMLElementNode extends HTMLNode {
    private String tagName;
    private List<HTMLAttributeNode> attributes;
    private List<TemplateNode> content;

    public HTMLElementNode(int line, int column, String tagName) {
        super("Element", line, column);
        this.tagName = tagName;
        this.attributes = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }

    public List<HTMLAttributeNode> getAttributes() { return attributes; }
    public void addAttribute(HTMLAttributeNode attribute) {
        attributes.add(attribute);
    }

    public List<TemplateNode> getContent() { return content; }
    public void addContent(TemplateNode contentItem) {
        content.add(contentItem);
    }
}
