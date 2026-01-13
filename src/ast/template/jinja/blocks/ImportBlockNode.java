package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class ImportBlockNode extends JinjaBlockNode {
    private String templateName;
    private String alias;

    public ImportBlockNode(int line, int column, String templateName, String alias) {
        super(NodeKind.JINJA_IMPORT_BLOCK, line, column);
        this.templateName = templateName;
        this.alias = alias;
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
    }
