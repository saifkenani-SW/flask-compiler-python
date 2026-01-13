package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.Collections;
import java.util.List;
public class BooleanLiteralNode extends LiteralNode {
    private boolean value;

    public BooleanLiteralNode(int line, int column, boolean value) {
        super(NodeKind.JINJA_LITERAL_BOOLEAN, line, column);
        this.value = value;
    }

    // Getters
    public boolean getValue() {
        return value;
    }

    // Setters
    public void setValue(boolean value) {
        this.value = value;
    }

    // Utility methods
    public boolean isTrue() {
        return value;
    }

    public boolean isFalse() {
        return !value;
    }

    @Override
    public String toString() {
        return String.format("BooleanLiteralNode{value=%b, line=%d, column=%d}",
                value, getLine(), getColumn());
    }

    @Override
    public List<TemplateNode> getChildren() {
        return Collections.emptyList(); // لا يحتوي أي أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
