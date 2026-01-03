package symbol_table;


import java.util.*;

public class SymbolTable {
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final SymbolTable parent;
    private final String scopeName;
    private final int scopeLevel;

    public SymbolTable(String scopeName) {
        this(scopeName, null);
    }

    public SymbolTable(String scopeName, SymbolTable parent) {
        this.scopeName = scopeName;
        this.parent = parent;
        this.scopeLevel = parent == null ? 0 : parent.scopeLevel + 1;
    }

    public void define(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }

    public Symbol lookup(String name) {
        Symbol symbol = symbols.get(name);
        if (symbol != null) {
            return symbol;
        }
        if (parent != null) {
            return parent.lookup(name);
        }
        return null;
    }

    public Symbol lookupLocal(String name) {
        return symbols.get(name);
    }

    public Map<String, Symbol> getSymbols() {
        return Collections.unmodifiableMap(symbols);
    }

    public SymbolTable getParent() {
        return parent;
    }

    public String getScopeName() {
        return scopeName;
    }

    public int getScopeLevel() {
        return scopeLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SymbolTable [")
                .append(scopeName)
                .append("] Level: ")
                .append(scopeLevel)
                .append("\n");

        for (Symbol symbol : symbols.values()) {
            sb.append("  ").append(symbol).append("\n");
        }

        return sb.toString();
    }
}