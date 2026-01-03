package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.jinja.expressions.ExpressionNode;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;


public class WithBlockNode extends JinjaBlockNode {
    private ExpressionNode expression;

    public WithBlockNode(int line, int column, ExpressionNode expression) {
        super(NodeKind.JINJA_WITH_BLOCK, line, column);
        this.expression = expression;
    }

    public ExpressionNode getExpression() { return expression; }

    public void setExpression(ExpressionNode expression) { this.expression = expression; }


    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
}


