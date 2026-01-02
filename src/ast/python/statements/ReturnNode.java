package ast.python.statements;

import ast.python.expressions.ExpressionNode;
import ast.python.visitors.PythonASTVisitor;

public class ReturnNode extends StatementNode {
    private ExpressionNode value;

    public ReturnNode(int line, int column) {
        super(line, column);
    }

    public ReturnNode(int line, int column, ExpressionNode value) {
        super(line, column);
        this.value = value;
        if (value != null) {
            addChild(value);
        }
    }

    public ExpressionNode getValue() { return value; }
    public void setValue(ExpressionNode value) {
        this.value = value;
        if (value != null) {
            addChild(value);
        }
    }

    public boolean hasValue() { return value != null; }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
