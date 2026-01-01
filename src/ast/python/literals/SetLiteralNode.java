package ast.python.literals;

import ast.python.expressions.ExpressionNode;
import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SetLiteralNode extends LiteralNode {
    private List<ExpressionNode> elements;

    public SetLiteralNode(int line, int column) {
        super(line, column);
        this.elements = new ArrayList<>();
    }

    public List<ExpressionNode> getElements() { return elements; }
    public void addElement(ExpressionNode element) {
        elements.add(element);
        addChild(element);
    }
    public void addElements(List<ExpressionNode> elems) {
        for (ExpressionNode elem : elems) {
            addElement(elem);
        }
    }

    public boolean isEmpty() { return elements.isEmpty(); }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
