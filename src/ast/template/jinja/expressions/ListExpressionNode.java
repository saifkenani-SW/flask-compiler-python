package ast.template.jinja.expressions;

import java.util.ArrayList;
import java.util.List;

public class ListExpressionNode extends ExpressionNode {
    private List<ExpressionNode> elements;

    public ListExpressionNode(int line, int column) {
        super("List", line, column);
        this.elements = new ArrayList<>();
    }

    public List<ExpressionNode> getElements() { return elements; }
    public void addElement(ExpressionNode element) {
        elements.add(element);
    }
}
