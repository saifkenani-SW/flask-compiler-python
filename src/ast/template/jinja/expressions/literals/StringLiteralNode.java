package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class StringLiteralNode extends LiteralNode {
    private String value;

    public StringLiteralNode(int line, int column, String value) {
        super(NodeKind.JINJA_LITERAL_STRING, line, column);
        this.value = value;
    }

    // Getters
    public String getValue() {
        return value;
    }

    public String getUnquotedValue() {
        if (value == null) return "";
        if ((value.startsWith("\"") && value.endsWith("\"")) ||
                (value.startsWith("'") && value.endsWith("'"))) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    // Setters
    public void setValue(String value) {
        this.value = value;
    }

    // Utility methods
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    public int getLength() {
        return value != null ? value.length() : 0;
    }

    public boolean isQuoted() {
        return value != null &&
                ((value.startsWith("\"") && value.endsWith("\"")) ||
                        (value.startsWith("'") && value.endsWith("'")));
    }

    @Override
    public String toString() {
        String displayValue = value != null && value.length() > 20 ?
                value.substring(0, 17) + "..." : value;
        return String.format("StringLiteralNode{value='%s', line=%d, column=%d}",
                displayValue, getLine(), getColumn());
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
