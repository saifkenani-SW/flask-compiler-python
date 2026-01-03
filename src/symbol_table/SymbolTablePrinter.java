package symbol_table;

import java.util.Map;

public class SymbolTablePrinter {

    public static void printSymbolTable(SymbolTable table) {
        printSymbolTable(table, 0);
    }

    private static void printSymbolTable(SymbolTable table, int indent) {
        String padding = "  ".repeat(indent);

        System.out.println(padding + "┌─ " + table.getScopeName() +
                " (Level: " + table.getScopeLevel() + ")");

        for (Map.Entry<String, Symbol> entry : table.getSymbols().entrySet()) {
            Symbol symbol = entry.getValue();
            System.out.println(padding + "│  " + symbol);
        }

        // لا يمكننا الوصول مباشرة إلى الجداول الفرعية
        // لأن SymbolTable لا تخزنها
        System.out.println(padding + "└─");
    }

    public static void printAllScopes(SymbolTable globalScope) {
        System.out.println("=== SYMBOL TABLE HIERARCHY ===");
        printScopeRecursive(globalScope, 0);
    }

    private static void printScopeRecursive(SymbolTable scope, int level) {
        printSymbolTable(scope, level);

        // في التنفيذ الفعلي، ستحتاج لتخزين الجداول الفرعية
        // في فئة SymbolTable
    }
}