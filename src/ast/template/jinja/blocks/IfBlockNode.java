package ast.template.jinja.blocks;

import ast.template.jinja.expressions.ExpressionNode;
import java.util.ArrayList;
import java.util.List;

public class IfBlockNode extends JinjaBlockNode {
    private ExpressionNode condition;
    private List<ElifBlockNode> elifBlocks;
    private ElseBlockNode elseBlock;

    public IfBlockNode(int line, int column, ExpressionNode condition) {
        super("If", line, column);
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
}
