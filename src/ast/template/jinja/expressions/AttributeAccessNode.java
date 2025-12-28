package ast.template.jinja.expressions;

public class AttributeAccessNode extends ExpressionNode {
    private ExpressionNode object;
    private String attribute;

    public AttributeAccessNode(int line, int column, ExpressionNode object, String attribute) {
        super("AttributeAccess", line, column);
        this.object = object;
        this.attribute = attribute;
    }

    public ExpressionNode getObject() { return object; }
    public void setObject(ExpressionNode object) { this.object = object; }

    public String getAttribute() { return attribute; }
    public void setAttribute(String attribute) { this.attribute = attribute; }
}
