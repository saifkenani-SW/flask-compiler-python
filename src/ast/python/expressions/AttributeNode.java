package ast.python.expressions;


import ast.python.visitors.PythonASTVisitor;

/**
 * سمة كائن (object.attribute)
 */
public class AttributeNode extends ExpressionNode {
    private ExpressionNode object;
    private String attributeName;

    public AttributeNode(int line, int column, ExpressionNode object, String attributeName) {
        super(line, column);
        this.object = object;
        this.attributeName = attributeName;
        addChild(object);
    }

    public ExpressionNode getObject() { return object; }
    public void setObject(ExpressionNode object) {
        this.object = object;
        addChild(object);
    }

    public String getAttributeName() { return attributeName; }
    public void setAttributeName(String attributeName) { this.attributeName = attributeName; }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
