package ast.template.jinja.expressions;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.Collections;
import java.util.List;

public class AttributeAccessNode extends ExpressionNode {
    private ExpressionNode object;
    private String attribute;

    public AttributeAccessNode(int line, int column, ExpressionNode object, String attribute) {
        super(NodeKind.JINJA_EXPR_ATTRIBUTE_ACCESS, line, column);
        this.object = object;
        this.attribute = attribute;
    }

    public ExpressionNode getObject() { return object; }
    public void setObject(ExpressionNode object) { this.object = object; }

    public String getAttribute() { return attribute; }
    public void setAttribute(String attribute) { this.attribute = attribute; }

    @Override
    public List<TemplateNode> getChildren() {
        return object != null ? Collections.singletonList(object) : Collections.emptyList();
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
