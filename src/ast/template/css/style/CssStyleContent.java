package ast.template.css.style;

import java.util.List;

public final class CssStyleContent {
    private final List<CssRule> rules;

    public CssStyleContent(List<CssRule> rules) {
        this.rules = List.copyOf(rules);
    }

    public List<CssRule> getRules() {
        return rules;
    }
}
