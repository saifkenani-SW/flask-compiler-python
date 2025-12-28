package ast.python.statements;

import ast.ASTVisitor;
import ast.python.expressions.ExpressionNode;

/**
 * جملة return
 */
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
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
