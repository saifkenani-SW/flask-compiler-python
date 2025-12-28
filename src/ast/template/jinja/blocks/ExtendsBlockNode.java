package ast.template.jinja.blocks;

public class ExtendsBlockNode extends JinjaBlockNode {
    private String templateName;

    public ExtendsBlockNode(int line, int column, String templateName) {
        super("Extends", line, column);
        this.templateName = templateName;
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
}
