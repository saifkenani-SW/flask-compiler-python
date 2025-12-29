package ast.template.css.selector;

import java.util.List;

public final class CssSelectors {
    private final List<CssSelector> selectors;

    public CssSelectors(List<CssSelector> selectors) {
        this.selectors = List.copyOf(selectors);
    }

    public List<CssSelector> getCssSelectors() {
        return selectors;
    }
}
