package ast.python.statements;

import ast.python.PythonNode;

/**
 * فئة أساسية لجميع العبارات
 */
public abstract class StatementNode extends PythonNode {
    public StatementNode(int line, int column) {
        super(line, column);
    }
}
