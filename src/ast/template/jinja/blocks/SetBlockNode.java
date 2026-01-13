package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.expressions.ExpressionNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;
public class SetBlockNode extends JinjaBlockNode {
    private String variable;
    private ExpressionNode expression;

    public SetBlockNode(int line, int column, String variable, ExpressionNode expression) {
        super(NodeKind.JINJA_SET_BLOCK, line, column);
        this.variable = variable;
        this.expression = expression;
    }

    public String getVariable() { return variable; }
    public void setVariable(String variable) { this.variable = variable; }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) { this.expression = expression; }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        if (expression != null) children.add(expression);
        return children;
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }


}
