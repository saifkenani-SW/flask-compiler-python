package ast.template.jinja.blocks;

import ast.template.jinja.expressions.ExpressionNode;

public class ElifBlockNode extends JinjaBlockNode {
    private ExpressionNode condition;

    public ElifBlockNode(int line, int column, ExpressionNode condition) {
        super("Elif", line, column);
        this.condition = condition;
    }

    public ExpressionNode getCondition() { return condition; }
    public void setCondition(ExpressionNode condition) { this.condition = condition; }
}
