package ast.template.css.selector;

import java.util.List;

public final class CssSelector {
    private final List<String>values;

    public CssSelector(List<String> values) {
        this.values = List.copyOf(values);
    }

    public List<String> getValues() {
        return values;
    }
}
