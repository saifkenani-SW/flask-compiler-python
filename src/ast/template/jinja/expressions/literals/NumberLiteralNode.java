package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class NumberLiteralNode extends LiteralNode {
    private Number value;

    public NumberLiteralNode(int line, int column, Number value) {
        super(NodeKind.JINJA_LITERAL_NUMBER, line, column);
        this.value = value;
    }

    public Number getValue() { return value; }
    public void setValue(Number value) { this.value = value; }
    @Override
    public List<TemplateNode> getChildren() {
        return Collections.emptyList(); // لا يحتوي أي أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
