package ast.python.statements;

import ast.python.expressions.ExpressionNode;
import ast.python.visitors.PythonASTVisitor;

/**
 * تعبير كجملة (مثل استدعاء دالة)
 */
public class ExpressionStatementNode extends StatementNode {
    private ExpressionNode expression;

    public ExpressionStatementNode(int line, int column, ExpressionNode expression) {
        super(line, column);
        this.expression = expression;
        addChild(expression);
    }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
