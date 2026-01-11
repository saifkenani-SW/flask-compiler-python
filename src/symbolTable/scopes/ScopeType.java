package symbolTable.scopes;

public enum ScopeType {
    GLOBAL,
    FUNCTION,
    FOR_LOOP,
    IF_BLOCK,
    ELIF_BLOCK,
    ELSE_BLOCK,
    WHILE_LOOP,
    WITH_BLOCK,
    /*TRY_BLOCK,
    EXCEPT_BLOCK,
    COMPREHENSION,
    CLASS,
    MODULE*/;

    public boolean isLoop() {
        return this == FOR_LOOP || this == WHILE_LOOP;
    }

    public boolean isConditional() {
        return this == IF_BLOCK || this == ELIF_BLOCK || this == ELSE_BLOCK;
    }
}