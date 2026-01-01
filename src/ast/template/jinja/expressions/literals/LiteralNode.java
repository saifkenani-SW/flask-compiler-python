package ast.template.jinja.expressions.literals;

import ast.template.NodeKind;
import ast.template.jinja.expressions.ExpressionNode;

public abstract class LiteralNode extends ExpressionNode {
    public LiteralNode(NodeKind nodeType, int line, int column) {
        super(nodeType, line, column);
    }
}
