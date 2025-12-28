package ast.template.jinja.expressions.literals;

public class NumberLiteralNode extends LiteralNode {
    private Number value;

    public NumberLiteralNode(int line, int column, Number value) {
        super("Number", line, column);
        this.value = value;
    }

    public Number getValue() { return value; }
    public void setValue(Number value) { this.value = value; }
}
