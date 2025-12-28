package ast.python.literals;

import ast.ASTVisitor;

/**
 * نص
 */
public class StringLiteralNode extends LiteralNode {
    private String value;

    public StringLiteralNode(int line, int column, String value) {
        super(line, column);
        this.value = value;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
