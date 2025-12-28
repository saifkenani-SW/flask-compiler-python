package ast.template.html;

import java.util.ArrayList;
import java.util.List;

public class HTMLOpeningTagNode extends HTMLNode {
    private String tagName;
    private List<HTMLAttributeNode> attributes;

    public HTMLOpeningTagNode(int line, int column, String tagName) {
        super("OpeningTag", line, column);
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
