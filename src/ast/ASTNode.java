package ast;

public abstract class ASTNode {

    protected final int line;
    protected final int column;

    protected ASTNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

}
