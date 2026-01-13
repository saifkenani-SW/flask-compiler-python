package ast.template.jinja.expressions;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ListExpressionNode extends ExpressionNode {
    private List<ExpressionNode> elements;

    public ListExpressionNode(int line, int column) {
        super(NodeKind.JINJA_EXPR_LIST, line, column);
        this.elements = new ArrayList<>();
    }

    public List<ExpressionNode> getElements() { return elements; }
    public void addElement(ExpressionNode element) {
        elements.add(element);
    }

    public void addAllElement(List<ExpressionNode> elements) {
        this.elements.addAll(elements);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return elements != null ? new ArrayList<>(elements) : Collections.emptyList();
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
