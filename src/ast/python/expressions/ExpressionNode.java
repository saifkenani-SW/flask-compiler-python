package ast.python.expressions;

import ast.python.PythonNode;

public abstract class ExpressionNode extends PythonNode {
    public ExpressionNode(int line, int column) {
        super(line, column);
    }
}
