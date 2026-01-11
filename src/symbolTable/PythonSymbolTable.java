package symbolTable;

import symbolTable.scopes.Scope;
import symbolTable.scopes.ScopeType;
import symbolTable.symbols.Symbol;
import symbolTable.symbols.SymbolKind;
import symbolTable.symbols.SymbolType;

import java.util.*;

public class PythonSymbolTable {
    private Scope globalScope;
    private Stack<Scope> scopeStack;
    private Map<Scope, List<Scope>> childScopes;
    private Map<Scope, Integer> scopeIds;
    private int nextScopeId = 0;

    public PythonSymbolTable() {
        globalScope = new Scope(ScopeType.GLOBAL, null);
        globalScope.setName("global");
        scopeStack = new Stack<>();
        scopeStack.push(globalScope);
        childScopes = new HashMap<>();
        scopeIds = new HashMap<>();

        childScopes.put(globalScope, new ArrayList<>());
        scopeIds.put(globalScope, nextScopeId++);
    }

    public void enterScope(ScopeType type) {
        Scope parent = getCurrentScope();
        Scope newScope = new Scope(type, parent);
        newScope.setName(type.toString().toLowerCase() + "#" + nextScopeId);

        scopeStack.push(newScope);
        childScopes.put(newScope, new ArrayList<>());
        scopeIds.put(newScope, nextScopeId++);

        if (parent != null) {
            childScopes.get(parent).add(newScope);
        }
    }

    public void exitScope() {
        if (scopeStack.size() > 1) {
            scopeStack.pop();
        }
    }

    public Scope getCurrentScope() {
        return scopeStack.peek();
    }

    public Scope getGlobalScope() {
        return globalScope;
    }

    public void defineSymbol(Symbol symbol) {
        Scope currentScope = getCurrentScope();

        if (currentScope.getSymbol(symbol.getName()) != null) {
            System.err.println(" Symbol '" + symbol.getName() +
                    "' already defined in " + currentScope.getName());
        }

        currentScope.define(symbol);
    }


    public Symbol resolveSymbol(String name) {
        return getCurrentScope().resolve(name);
    }

    public Symbol resolveLocal(String name) {
        return getCurrentScope().resolveLocal(name);
    }

    public void print() {

        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED SCOPE TREE");
        System.out.println("=".repeat(50));

        printScopeTreeCorrected(globalScope, 0, false);
    }

    private void printScopeHierarchy() {
        System.out.println("\nScope Hierarchy (from current):");

        if (getCurrentScope() == null) {
            System.out.println("  ↳ No active scope");
            return;
        }

        List<Scope> hierarchy = new ArrayList<>();
        Scope scope = getCurrentScope();
        while (scope != null) {
            hierarchy.add(scope);
            scope = scope.getParent();
        }

        for (int i = hierarchy.size() - 1; i >= 0; i--) {
            int level = hierarchy.size() - i - 1;
            String indent = "  ".repeat(level);
            Scope s = hierarchy.get(i);

            String scopeLabel = String.format("%s: %s #%d",
                    s.getScopeType(), s.getName(), scopeIds.get(s));

            String arrow = (i == 0) ? "↳ " : "↱ ";
            System.out.println(indent + arrow + scopeLabel);
        }
    }

    private void printScopeTreeCorrected(Scope scope, int depth, boolean isLast) {
        String indent = "  ".repeat(depth);

        String connector;
        if (depth == 0) {
            connector = "";
        } else {
            connector = isLast ? "└── " : "├── ";
        }

        System.out.print(indent + connector);

        String scopeInfo = String.format("%s #%d",
                scope.getScopeType().toString(), scopeIds.get(scope));

        if (!scope.getName().isEmpty() && !scope.getName().equals("global")) {
            scopeInfo += " \"" + scope.getName() + "\"";
        }

        System.out.println(scopeInfo);

        printSymbolsInScope(scope, depth + 1);

        List<Scope> children = getDirectChildren(scope);
        for (int i = 0; i < children.size(); i++) {
            Scope child = children.get(i);
            boolean childIsLast = (i == children.size() - 1);
            printScopeTreeCorrected(child, depth + 1, childIsLast);
        }
    }

    private List<Scope> getDirectChildren(Scope parent) {
        List<Scope> directChildren = new ArrayList<>();

        for (Scope child : childScopes.keySet()) {
            if (child.getParent() == parent) {
                directChildren.add(child);
            }
        }

        directChildren.sort(Comparator.comparingInt(s -> scopeIds.getOrDefault(s, 0)));
        return directChildren;
    }

    private void printSymbolsInScope(Scope scope, int depth) {
        String indent = "  ".repeat(depth);
        Map<String, Symbol> symbols = scope.getSymbols();

        if (symbols.isEmpty()) {
            System.out.println(indent + "[no symbols]");
            return;
        }

        List<Symbol> sortedSymbols = new ArrayList<>(symbols.values());
        sortedSymbols.sort(Comparator.comparing(Symbol::getKind)
                .thenComparing(Symbol::getName));

        for (Symbol symbol : sortedSymbols) {
            String symbolLine = buildSymbolLine(symbol);
            System.out.println(indent + "• " + symbolLine);
        }
    }

    private String buildSymbolLine(Symbol symbol) {
        StringBuilder sb = new StringBuilder();

        String icon;
        switch (symbol.getKind().toString().toLowerCase()) {
            case "function": icon = "ƒ"; break;
            case "parameter": icon = "𝑝"; break;
            case "variable": icon = "𝑣"; break;
            case "import": icon = "📦"; break;
            case "builtin": icon = "⚙️"; break;
            default: icon = "•";
        }

        sb.append(icon).append(" ");
        sb.append(String.format("%-20s", symbol.getName()));
        sb.append(" : ");
        sb.append(String.format("%-12s", symbol.getKind()));

        if (!symbol.getType().toString().equals("UNKNOWN")) {
            sb.append(" [").append(symbol.getType()).append("]");
        }

        List<String> attributes = new ArrayList<>();
        if (symbol.hasAttribute("isImported")) attributes.add("imported");
        if (symbol.hasAttribute("isBuiltin")) attributes.add("builtin");
        if (symbol.hasAttribute("isConstant")) attributes.add("const");
        if (symbol.hasAttribute("line")) attributes.add("L:" + symbol.getAttribute("line"));

        if (!attributes.isEmpty()) {
            sb.append(" (").append(String.join(", ", attributes)).append(")");
        }

        if (symbol.getValue() != null && !symbol.getKind().equals("function")) {
            sb.append(" = ").append(formatValue(symbol.getValue()));
        }

        return sb.toString();
    }

    private String formatValue(Object value) {
        if (value == null) return "null";
        if (value instanceof String) return "\"" + value + "\"";
        return value.toString();
    }

    public int getScopeDepth() {
        return scopeStack.size() - 1;
    }

    public boolean isInGlobalScope() {
        return getCurrentScope() == globalScope;
    }

    public void enterFunctionScope() {
        enterScope(ScopeType.FUNCTION);
    }

    public void enterForLoopScope() {
        enterScope(ScopeType.FOR_LOOP);
    }

    public void enterIfScope() {
        enterScope(ScopeType.IF_BLOCK);
    }

    public void enterElifScope() {
        enterScope(ScopeType.ELIF_BLOCK);
    }

    public void enterElseScope() {
        enterScope(ScopeType.ELSE_BLOCK);
    }

    public void enterWhileScope() {
        enterScope(ScopeType.WHILE_LOOP);
    }

    public void enterWithScope() {
        enterScope(ScopeType.WITH_BLOCK);
    }

    public List<Scope> getChildScopes(Scope parent) {
        return getDirectChildren(parent);
    }

    public List<Scope> getAllScopes() {
        return new ArrayList<>(childScopes.keySet());
    }

    public int getScopeId(Scope scope) {
        return scopeIds.getOrDefault(scope, -1);
    }

}