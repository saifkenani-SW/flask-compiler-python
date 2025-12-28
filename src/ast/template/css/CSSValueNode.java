package ast.template.css;

public abstract class CSSValueNode extends CSSNode {
    private String value;

    public CSSValueNode(String nodeType, int line, int column, String value) {
        super("Value:" + nodeType, line, column);
        this.value = value;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
