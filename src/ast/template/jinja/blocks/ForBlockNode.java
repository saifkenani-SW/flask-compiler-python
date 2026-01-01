package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.jinja.expressions.ExpressionNode;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class ForBlockNode extends JinjaBlockNode {
    private String variable;
    private ExpressionNode iterable;
    private ElseBlockNode elseBlock;

    public ForBlockNode(int line, int column, String variable, ExpressionNode iterable) {
        super(NodeKind.JINJA_FOR_BLOCK, line, column);
        this.variable = variable;
        this.iterable = iterable;
    }

    public String getVariable() { return variable; }
    public void setVariable(String variable) { this.variable = variable; }

    public ExpressionNode getIterable() { return iterable; }
    public void setIterable(ExpressionNode iterable) { this.iterable = iterable; }

    public ElseBlockNode getElseBlock() { return elseBlock; }
    public void setElseBlock(ElseBlockNode elseBlock) { this.elseBlock = elseBlock; }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>(super.getChildren());
        if (iterable != null) children.add(iterable);
        if (elseBlock != null) children.add(elseBlock);
        return children;
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
