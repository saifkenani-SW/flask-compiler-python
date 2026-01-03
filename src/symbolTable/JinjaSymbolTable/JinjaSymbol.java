package symbolTable.JinjaSymbolTable;

import java.util.HashMap;
import java.util.Map;

// ===== فئة الرمز الأساسية =====
public abstract class JinjaSymbol {
    protected final String name;
    protected final JinjaSymbolType type;
    protected final int line;
    protected final int column;
    protected final Map<String, Object> metadata;

    public JinjaSymbol(String name, JinjaSymbolType type, int line, int column) {
        this.name = name;
        this.type = type;
        this.line = line;
        this.column = column;
        this.metadata = new HashMap<>();
    }

    // Getters
    public String getName() { return name; }
    public JinjaSymbolType getType() { return type; }
    public int getLine() { return line; }
    public int getColumn() { return column; }
    public Map<String, Object> getMetadata() { return metadata; }

    public void addMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) at [%d:%d]", name, type, line, column);
    }
}
