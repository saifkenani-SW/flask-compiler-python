package ast.template.html;

public class HTMLTextNode extends HTMLNode {
    private String text;

    public HTMLTextNode(int line, int column, String text) {
        super("Text", line, column);
        this.text = text;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
