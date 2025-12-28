package ast.python.expressions;

import ast.ASTVisitor;

/**
 * معرف (اسم متغير)
 */
public class IdentifierNode extends ExpressionNode {
    private String name;

    public IdentifierNode(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
