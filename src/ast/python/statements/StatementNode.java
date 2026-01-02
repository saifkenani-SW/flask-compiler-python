package ast.python.statements;

import ast.python.PythonNode;

public abstract class StatementNode extends PythonNode {
    public StatementNode(int line, int column) {
        super(line, column);
    }
}
