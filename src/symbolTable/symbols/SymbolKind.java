package symbolTable.symbols;

// أنواع الرموز
public enum SymbolKind {
    VARIABLE("variable"),
    FUNCTION("function"),
    PARAMETER("parameter"),
    IMPORT("import"),
    CLASS("class"),
    MODULE("module"),
  //  CONSTANT("constant"),
    ATTRIBUTE("attribute"),
    BUILTIN("builtin");

    private final String description;

    SymbolKind(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
