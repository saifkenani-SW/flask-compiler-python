package ast.template.jinja.expression;

public final class NumberLiteral implements LiteralExpr {

    private final String value;

    public NumberLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
