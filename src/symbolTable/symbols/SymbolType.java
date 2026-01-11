package symbolTable.symbols;

public enum SymbolType {
    INT("int"),
    FLOAT("float"),
    STRING("str"),
    BOOL("bool"),
    NONE("None"),

    LIST("list"),
    DICT("dict"),
    SET("set"),
    //TUPLE("tuple"),

    FUNCTION_TYPE("function"),
   // CLASS_TYPE("class"),
    MODULE_TYPE("module"),
    ANY("any"),
    UNKNOWN("unknown");

    private final String description;

    SymbolType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public boolean isNumeric() {
        return this == INT || this == FLOAT;
    }

    public boolean isSequence() {
        return this == LIST /*|| this == TUPLE*/ || this == SET;
    }

    public boolean isBasicType() {
        return this == INT || this == FLOAT ||
                this == STRING || this == BOOL || this == NONE;
    }
}