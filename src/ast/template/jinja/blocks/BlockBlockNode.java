package ast.template.jinja.blocks;

public class BlockBlockNode extends JinjaBlockNode {
    private String blockName;

    public BlockBlockNode(int line, int column, String blockName) {
        super("Block", line, column);
        this.blockName = blockName;
    }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }
}
