package symbolTable.scopes;

import symbolTable.symbols.Symbol;
import java.util.HashMap;
import java.util.Map;

public class Scope {
    private static int NEXT_ID = 0;

    protected final int id;
    protected String name;
    protected ScopeType type;
    protected Scope parent;
    protected Map<String, Symbol> symbols = new HashMap<>();

    public Scope(ScopeType type, Scope parent) {
        this.id = NEXT_ID++;
        this.type = type;
        this.parent = parent;
        this.name = generateName();
    }

    private String generateName() {
        if (type == ScopeType.GLOBAL) {
            return "global";
        }
        return type.toString().toLowerCase() + "#" + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Scope getParent() {
        return parent;
    }

    public ScopeType getScopeType() {
        return type;
    }

    public void define(Symbol symbol) {
        if (symbols.containsKey(symbol.getName())) {
            System.err.println(/*"Warning: Symbol '" + symbol.getName() +
                    "' already defined in scope " +*/ name);
        }
        symbols.put(symbol.getName(), symbol);
        symbol.setScope(this);
    }

    public Symbol resolve(String name) {
        Symbol symbol = symbols.get(name);
        if (symbol != null) {
            return symbol;
        }

        if (parent != null) {
            return parent.resolve(name);
        }
        return null;
    }

    public Symbol resolveLocal(String name) {
        return symbols.get(name);
    }

    public boolean containsSymbol(String name) {
        return symbols.containsKey(name);
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public Map<String, Symbol> getSymbols() {
        return new HashMap<>(symbols);
    }

    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public int getSymbolCount() {
        return symbols.size();
    }

    @Override
    public String toString() {
        return name + " [" + type + "]";
    }
}