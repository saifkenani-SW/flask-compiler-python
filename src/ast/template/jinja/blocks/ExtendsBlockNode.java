package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class ExtendsBlockNode extends JinjaBlockNode {
    private String templateName;

    public ExtendsBlockNode(int line, int column, String templateName) {
        super(NodeKind.JINJA_EXTENDS_BLOCK, line, column);
        this.templateName = templateName;
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    @Override
    public List<TemplateNode> getChildren() {
        return super.getChildren(); // لا يوجد عناصر Expression
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
