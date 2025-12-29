package ast.python.statements;


import ast.python.visitors.PythonASTVisitor;

/**
 * جملة pass
 */
public class PassNode extends StatementNode {

    public PassNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
