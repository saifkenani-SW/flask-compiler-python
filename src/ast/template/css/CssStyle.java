package ast.template.css;

import ast.template.css.attribute.CssTagAttributes;
import ast.template.css.style.CssStyleContent;

public final class CssStyle {
    private final CssTagAttributes attributes;
    private final CssStyleContent content;

    public CssStyle(CssTagAttributes attributes, CssStyleContent content) {
        this.attributes = attributes;
        this.content = content;
    }

    public CssTagAttributes getAttributes() {
        return attributes;
    }

    public CssStyleContent getContent() {
        return content;
    }


}
