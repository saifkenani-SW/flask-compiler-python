package ast.python.literals;

import ast.ASTVisitor;

/**
 * قيمة منطقية
 */
public class BoolLiteralNode extends LiteralNode {
    private boolean value;

    public BoolLiteralNode(int line, int column, boolean value) {
        super(line, column);
        this.value = value;
    }

    public boolean getValue() { return value; }
    public void setValue(boolean value) { this.value = value; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
