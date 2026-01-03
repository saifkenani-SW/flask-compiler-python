package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class BlockBlockNode extends JinjaBlockNode {
    private String blockName;

    public BlockBlockNode(int line, int column, String blockName) {
        super(NodeKind.JINJA_BLOCK_BLOCK, line, column);
        this.blockName = blockName;
    }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
