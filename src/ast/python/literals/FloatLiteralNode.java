package ast.python.literals;

import ast.ASTVisitor;

/**
 * عدد عشري
 */
public class FloatLiteralNode extends LiteralNode {
    private double value;

    public FloatLiteralNode(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
