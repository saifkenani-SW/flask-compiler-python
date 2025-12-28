package ast.python.statements;

import ast.ASTVisitor;

/**
 * جملة continue
 */
public class ContinueNode extends StatementNode {

    public ContinueNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
