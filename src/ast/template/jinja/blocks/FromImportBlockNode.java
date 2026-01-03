package ast.template.jinja.blocks;

import ast.template.NodeKind;

import java.util.*;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

public class FromImportBlockNode extends JinjaBlockNode {
    private String templateName;
    private List<String> imports;

    public FromImportBlockNode(int line, int column, String templateName) {
        super(NodeKind.JINJA_FROM_IMPORT_BLOCK, line, column);
        this.templateName = templateName;
        this.imports = new ArrayList<>();
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public List<String> getImports() { return imports; }
    public void addImport(String importName) {
        imports.add(importName);
    }


    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
