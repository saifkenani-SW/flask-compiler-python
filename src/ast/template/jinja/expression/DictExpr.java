package ast.template.jinja.expression;

import java.util.List;
//{"a": 1, "b": x}


public final class DictExpr implements Expr {

    public static final class Entry {
        private final String key;
        private final Expr value;

        public Entry(String key, Expr value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Expr getValue() {
            return value;
        }
    }

    private final List<Entry> entries;

    public DictExpr(List<Entry> entries) {
        this.entries = List.copyOf(entries);
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
