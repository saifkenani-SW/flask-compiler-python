package ast.template.css.value;

import java.util.List;

public final class CssValues {

    private final List<CssValue> cssValues;

    public CssValues(List<CssValue> cssValues) {
        this.cssValues = List.copyOf(cssValues);
    }

    public List<CssValue> getCssValues() {
        return cssValues;
    }
}
