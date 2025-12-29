package ast.python.statements;

import ast.python.visitors.PythonASTVisitor;

/**
 * جملة continue
 */
public class ContinueNode extends StatementNode {

    public ContinueNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
