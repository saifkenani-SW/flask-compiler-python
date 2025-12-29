package ast.template.css.declaration;

import ast.template.css.value.CssValues;

public final class CssDeclaration {

    private final String property;
    private final CssValues cssValues;

    public CssDeclaration(String property, CssValues cssValues) {
        this.property = property;
        this.cssValues = cssValues;
    }

    public String getProperty() {
        return property;
    }

    public CssValues getCssValues() {
        return cssValues;
    }
}
