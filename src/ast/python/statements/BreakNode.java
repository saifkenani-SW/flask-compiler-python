package ast.python.statements;

import ast.ASTVisitor;

/**
 * جملة break
 */
public class BreakNode extends StatementNode {

    public BreakNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
