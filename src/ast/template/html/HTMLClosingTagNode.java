package ast.template.html;

public class HTMLClosingTagNode extends HTMLNode {
    private String tagName;

    public HTMLClosingTagNode(int line, int column, String tagName) {
        super("ClosingTag", line, column);
        this.tagName = tagName;
    }

    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }
}
