package ast.template.css.attribute;

import java.util.List;

public final class CssTagAttributes {
    private final List<CssTagAttribute> attributes;

    public CssTagAttributes(List<CssTagAttribute> attributes) {
        this.attributes = List.copyOf(attributes);
    }

    public List<CssTagAttribute> getAttributes() {
        return attributes;
    }
}
