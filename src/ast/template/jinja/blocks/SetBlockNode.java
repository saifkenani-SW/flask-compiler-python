package ast.template.jinja.blocks;

import ast.template.jinja.expressions.ExpressionNode;

public class SetBlockNode extends JinjaBlockNode {
    private String variable;
    private ExpressionNode expression;

    public SetBlockNode(int line, int column, String variable, ExpressionNode expression) {
        super("Set", line, column);
        this.variable = variable;
        this.expression = expression;
    }

    public String getVariable() { return variable; }
    public void setVariable(String variable) { this.variable = variable; }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) { this.expression = expression; }
}
