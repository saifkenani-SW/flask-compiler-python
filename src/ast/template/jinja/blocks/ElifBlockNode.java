package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.expressions.ExpressionNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class ElifBlockNode extends JinjaBlockNode {
    private ExpressionNode condition;

    public ElifBlockNode(int line, int column, ExpressionNode condition) {
        super(NodeKind.JINJA_ELIF_BLOCK, line, column);
        this.condition = condition;
    }

    public ExpressionNode getCondition() { return condition; }
    public void setCondition(ExpressionNode condition) { this.condition = condition; }


    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
}
