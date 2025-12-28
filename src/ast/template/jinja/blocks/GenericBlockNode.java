package ast.template.jinja.blocks;

import ast.template.jinja.expressions.ExpressionNode;

public class GenericBlockNode extends JinjaBlockNode {
    private String blockName;
    private ExpressionNode expression;

    public GenericBlockNode(int line, int column, String blockName) {
        super("Generic", line, column);
        this.blockName = blockName;
    }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) { this.expression = expression; }
}
