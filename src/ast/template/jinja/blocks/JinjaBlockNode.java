package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.JinjaNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class JinjaBlockNode extends JinjaNode {
    private List<TemplateNode> content;

    public JinjaBlockNode(NodeKind blockType, int line, int column) {
        super(blockType, line, column);
        this.content = new ArrayList<>();
    }

    public List<TemplateNode> getContent() { return content; }
    public void addContent(TemplateNode contentItem) {
        content.add(contentItem);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return content != null ? content : Collections.emptyList();
    }

}
