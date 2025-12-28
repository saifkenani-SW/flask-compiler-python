package ast.template.jinja.expressions.literals;

public class StringLiteralNode extends LiteralNode {
    private String value;

    public StringLiteralNode(int line, int column, String value) {
        super("String", line, column);
        this.value = value;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
