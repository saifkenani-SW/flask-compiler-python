package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.expressions.ExpressionNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfBlockNode extends JinjaBlockNode {
    private ExpressionNode condition;
    private List<ElifBlockNode> elifBlocks;
    private ElseBlockNode elseBlock;

    public IfBlockNode(int line, int column, ExpressionNode condition) {
        super(NodeKind.JINJA_IF_BLOCK, line, column);
        this.condition = condition;
        this.elifBlocks = new ArrayList<>();
    }

    public ExpressionNode getCondition() { return condition; }
    public void setCondition(ExpressionNode condition) { this.condition = condition; }

    public List<ElifBlockNode> getElifBlocks() { return elifBlocks; }
    public void addElifBlock(ElifBlockNode elifBlock) {
        elifBlocks.add(elifBlock);
    }

    public ElseBlockNode getElseBlock() { return elseBlock; }
    public void setElseBlock(ElseBlockNode elseBlock) { this.elseBlock = elseBlock; }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>(super.getChildren());
        if (condition != null) children.add(condition);
        if (elifBlocks != null) children.addAll(elifBlocks);
        if (elseBlock != null) children.add(elseBlock);
        return children;
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
