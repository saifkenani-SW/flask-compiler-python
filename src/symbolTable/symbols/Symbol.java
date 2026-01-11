package symbolTable.symbols;

import symbolTable.scopes.Scope;
import java.util.HashMap;
import java.util.Map;

public class Symbol {
    protected String name;
    protected int line;
    protected int column;
    protected Scope scope;
    protected SymbolKind kind;
    protected SymbolType type;
    protected Object value;
    protected Map<String, Object> attributes;

    public Symbol(String name, int line, int column, SymbolKind kind, SymbolType type) {
        this(name, line, column, kind, type, null);
    }

    public Symbol(String name, int line, int column, SymbolKind kind, SymbolType type, Object value) {
        this.name = name;
        this.line = line;
        this.column = column;
        this.kind = kind;
        this.type = type;
        this.value = value;
        this.attributes = new HashMap<>();
    }

    public String getName() { return name; }
    public int getLine() { return line; }
    public int getColumn() { return column; }
    public Scope getScope() { return scope; }
    public SymbolKind getKind() { return kind; }
    public SymbolType getType() { return type; }
    public Object getValue() { return value; }

    public void setScope(Scope scope) { this.scope = scope; }
    public void setType(SymbolType type) { this.type = type; }
    public void setValue(Object value) { this.value = value; }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    public boolean isVariable() { return kind == SymbolKind.VARIABLE; }
    public boolean isFunction() { return kind == SymbolKind.FUNCTION; }
    public boolean isParameter() { return kind == SymbolKind.PARAMETER; }

    public String getValueString() {
        if (value == null) return "null";
        if (value instanceof String) return "\"" + value + "\"";
        return value.toString();
    }

    @Override
    public String toString() {
        String result = String.format("%s: %s [%s]", kind, name, type);
        if (value != null) {
            result += " = " + getValueString();
        }
        return result;
    }
}