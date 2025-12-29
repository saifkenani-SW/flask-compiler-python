package ast.template.jinja.expression;

public final class StringLiteral implements LiteralExpr {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
