package ast.template.jinja.blocks;

public class ImportBlockNode extends JinjaBlockNode {
    private String templateName;
    private String alias;

    public ImportBlockNode(int line, int column, String templateName, String alias) {
        super("Import", line, column);
        this.templateName = templateName;
        this.alias = alias;
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
}
