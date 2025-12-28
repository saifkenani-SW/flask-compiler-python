package ast.template.jinja.expressions;

import ast.template.jinja.JinjaNode;

public class JinjaExpressionNode extends JinjaNode {
    private ExpressionNode expression;

    public JinjaExpressionNode(int line, int column, ExpressionNode expression) {
        super("Expression", line, column);
        this.expression = expression;
    }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
    }
}
