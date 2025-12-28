package ast.template.css;

public class CSSSelectorNode extends CSSNode {
    private String selector;

    public CSSSelectorNode(int line, int column, String selector) {
        super("Selector", line, column);
        this.selector = selector;
    }

    public String getSelector() { return selector; }
    public void setSelector(String selector) { this.selector = selector; }
}
