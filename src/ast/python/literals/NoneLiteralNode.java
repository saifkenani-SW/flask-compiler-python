package ast.python.literals;

import ast.ASTVisitor;

/**
 * قيمة None
 */
public class NoneLiteralNode extends LiteralNode {

    public NoneLiteralNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
