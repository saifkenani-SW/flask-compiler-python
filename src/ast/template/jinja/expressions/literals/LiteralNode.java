package ast.template.jinja.expressions.literals;

import ast.template.jinja.expressions.ExpressionNode;

public abstract class LiteralNode extends ExpressionNode {
    public LiteralNode(String nodeType, int line, int column) {
        super("Literal:" + nodeType, line, column);
    }
}
