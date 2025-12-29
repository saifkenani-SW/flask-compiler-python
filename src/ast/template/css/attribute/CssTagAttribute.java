package ast.template.css.attribute;

public final class CssTagAttribute {
    private final String attribute;
    private final String value;

    public CssTagAttribute(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }
}
