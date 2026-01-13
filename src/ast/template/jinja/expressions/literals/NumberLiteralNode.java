package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.Collections;
import java.util.List;

public class NumberLiteralNode extends LiteralNode {
    private Number value;

    public NumberLiteralNode(int line, int column, Number value) {
        super(NodeKind.JINJA_LITERAL_NUMBER, line, column);
        this.value = value;
    }

    // Getters
    public Number getValue() {
        return value;
    }

    public double getDoubleValue() {
        return value.doubleValue();
    }

    public int getIntValue() {
        return value.intValue();
    }

    // Setters
    public void setValue(Number value) {
        this.value = value;
    }

    // Utility methods
    public boolean isInteger() {
        return value instanceof Integer ||
                (value instanceof Double && value.doubleValue() == value.intValue());
    }

    public boolean isFloatingPoint() {
        return value instanceof Double && value.doubleValue() != value.intValue();
    }

    public boolean isZero() {
        return value.doubleValue() == 0.0;
    }

    public boolean isNegative() {
        return value.doubleValue() < 0;
    }

    @Override
    public String toString() {
        return String.format("NumberLiteralNode{value=%s, line=%d, column=%d}",
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
