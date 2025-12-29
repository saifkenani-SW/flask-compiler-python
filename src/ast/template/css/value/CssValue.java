package ast.template.css.value;

public abstract class CssValue {

    protected final String value;

    protected CssValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
