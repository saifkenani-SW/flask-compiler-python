package ast.python.literals;


import ast.python.visitors.PythonASTVisitor;

public class IntLiteralNode extends LiteralNode {
    private long value;

    public IntLiteralNode(int line, int column, long value) {
        super(line, column);
        this.value = value;
    }

    public long getValue() { return value; }
    public void setValue(long value) { this.value = value; }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
