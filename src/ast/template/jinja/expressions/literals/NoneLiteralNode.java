package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class NoneLiteralNode extends LiteralNode {
    public NoneLiteralNode(int line, int column) {
        super(NodeKind.JINJA_LITERAL_NONE, line, column);
    }

    public Object getValue() { return null; }
    @Override
    public List<TemplateNode> getChildren() {
        return Collections.emptyList(); // لا يحتوي أي أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
