package ast.template.css.style;

import ast.template.css.declaration.CssDeclarations;
import ast.template.css.selector.CssSelectors;

public final class CssRule {
    private final CssSelectors selectors;
    private final CssDeclarations declarations;


    public CssRule(CssSelectors selectors, CssDeclarations declarations) {
        this.selectors = selectors;
        this.declarations = declarations;
    }

    public CssSelectors getSelectors() {
        return selectors;
    }

    public CssDeclarations getDeclarations() {
        return declarations;
    }
}
