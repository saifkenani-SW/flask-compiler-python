package ast.template.jinja.blocks;

import ast.template.jinja.expressions.ExpressionNode;

public class ForBlockNode extends JinjaBlockNode {
    private String variable;
    private ExpressionNode iterable;
    private ElseBlockNode elseBlock;

    public ForBlockNode(int line, int column, String variable, ExpressionNode iterable) {
        super("For", line, column);
        this.variable = variable;
        this.iterable = iterable;
    }

    public String getVariable() { return variable; }
    public void setVariable(String variable) { this.variable = variable; }

    public ExpressionNode getIterable() { return iterable; }
    public void setIterable(ExpressionNode iterable) { this.iterable = iterable; }

    public ElseBlockNode getElseBlock() { return elseBlock; }
    public void setElseBlock(ElseBlockNode elseBlock) { this.elseBlock = elseBlock; }
}
