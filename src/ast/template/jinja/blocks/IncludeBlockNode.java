package ast.template.jinja.blocks;

public class IncludeBlockNode extends JinjaBlockNode {
    private String templateName;

    public IncludeBlockNode(int line, int column, String templateName) {
        super("Include", line, column);
        this.templateName = templateName;
    }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
}
