package symbolTable.JinjaSymbolTable;

// ===== رمز المتغير =====
public class VariableSymbol extends JinjaSymbol {
    private String valueType;  // string, number, boolean, list, dict, any
    private Object defaultValue;
    private boolean isLoopVar;

    public VariableSymbol(String name, int line, int column, String valueType) {
        super(name, JinjaSymbolType.VARIABLE, line, column);
        this.valueType = valueType;
        this.isLoopVar = false;
    }

    public VariableSymbol(String name, int line, int column, String valueType, boolean isLoopVar) {
        this(name, line, column, valueType);
        this.isLoopVar = isLoopVar;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isLoopVar() {
        return isLoopVar;
    }

    public void setLoopVar(boolean loopVar) {
        isLoopVar = loopVar;
    }
}
