package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class BooleanLiteralNode extends LiteralNode {
    private boolean value;

    public BooleanLiteralNode(int line, int column, boolean value) {
        super(NodeKind.JINJA_LITERAL_BOOLEAN, line, column);
        this.value = value;
    }

    public boolean getValue() { return value; }
    public void setValue(boolean value) { this.value = value; }
    @Override
    public List<TemplateNode> getChildren() {
        return Collections.emptyList(); // لا يحتوي أي أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
