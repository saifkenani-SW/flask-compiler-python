package ast.template.html;

public class HTMLSelfClosingElementNode extends HTMLElementNode {
    public HTMLSelfClosingElementNode(int line, int column, String tagName) {
        super(line, column, tagName);
    }
}
