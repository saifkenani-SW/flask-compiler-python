package ast.template.html;

import ast.template.TemplateNode;

import java.util.ArrayList;
import java.util.List;

public class HTMLDocumentNode extends HTMLNode {
    private String htmlId;
    private List<HTMLAttributeNode> attributes;
    private List<TemplateNode> content;

    public HTMLDocumentNode(int line, int column) {
        super("Document", line, column);
        this.attributes = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public String getHtmlId() { return htmlId; }
    public void setHtmlId(String htmlId) { this.htmlId = htmlId; }

    public List<HTMLAttributeNode> getAttributes() { return attributes; }
    public void addAttribute(HTMLAttributeNode attribute) {
        attributes.add(attribute);
    }

    public List<TemplateNode> getContent() { return content; }
    public void addContent(TemplateNode contentItem) {
        content.add(contentItem);
    }
}
