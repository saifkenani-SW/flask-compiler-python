package ast.template.jinja.blocks;

import ast.template.TemplateNode;
import ast.template.jinja.JinjaNode;
import java.util.ArrayList;
import java.util.List;

public abstract class JinjaBlockNode extends JinjaNode {
    private List<TemplateNode> content;

    public JinjaBlockNode(String blockType, int line, int column) {
        super("Block:" + blockType, line, column);
        this.content = new ArrayList<>();
    }

    public List<TemplateNode> getContent() { return content; }
    public void addContent(TemplateNode contentItem) {
        content.add(contentItem);
    }
}
