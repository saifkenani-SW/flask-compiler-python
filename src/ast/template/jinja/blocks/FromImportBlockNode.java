package ast.template.jinja.blocks;

import java.util.ArrayList;
import java.util.List;

public class FromImportBlockNode extends JinjaBlockNode {
    private String templateName;
    private List<String> imports;

    public FromImportBlockNode(int line, int column, String templateName) {
        super("FromImport", line, column);
        this.templateName = templateName;
        this.imports = new ArrayList<>();
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public List<String> getImports() { return imports; }
    public void addImport(String importName) {
        imports.add(importName);
    }
}
