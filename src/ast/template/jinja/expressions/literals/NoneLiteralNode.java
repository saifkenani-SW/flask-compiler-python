package ast.template.jinja.expressions.literals;

public class NoneLiteralNode extends LiteralNode {
    public NoneLiteralNode(int line, int column) {
        super("None", line, column);
    }

    public Object getValue() { return null; }
}
