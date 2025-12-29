package ast.python.statements;

import ast.python.visitors.PythonASTVisitor;

/**
 * جملة break
 */
public class BreakNode extends StatementNode {

    public BreakNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
