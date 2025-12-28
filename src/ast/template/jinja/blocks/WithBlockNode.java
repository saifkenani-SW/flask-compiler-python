package ast.template.jinja.blocks;

import ast.template.jinja.expressions.ExpressionNode;

public class WithBlockNode extends JinjaBlockNode {
    private ExpressionNode expression;

    public WithBlockNode(int line, int column, ExpressionNode expression) {
        super("With", line, column);
        this.expression = expression;
    }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) { this.expression = expression; }
}
