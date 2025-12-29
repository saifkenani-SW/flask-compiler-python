package ast.template.jinja.expression;

public final class BooleanLiteral implements LiteralExpr {

    private final boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
