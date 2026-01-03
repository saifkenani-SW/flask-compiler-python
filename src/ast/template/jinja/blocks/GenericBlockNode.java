package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.jinja.expressions.ExpressionNode;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class GenericBlockNode extends JinjaBlockNode {
    private String blockName;
    private ExpressionNode expression;

    public GenericBlockNode(int line, int column, String blockName) {
        super(NodeKind.JINJA_GENERIC_BLOCK, line, column);
        this.blockName = blockName;
    }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) { this.expression = expression; }
    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
