package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class IncludeBlockNode extends JinjaBlockNode {
    private String templateName;

    public IncludeBlockNode(int line, int column, String templateName) {
        super(NodeKind.JINJA_INCLUDE_BLOCK, line, column);
        this.templateName = templateName;
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
}
