package ast.template.jinja.expressions.literals;

public class BooleanLiteralNode extends LiteralNode {
    private boolean value;

    public BooleanLiteralNode(int line, int column, boolean value) {
        super("Boolean", line, column);
        this.value = value;
    }

    public boolean getValue() { return value; }
    public void setValue(boolean value) { this.value = value; }
}
