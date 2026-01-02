package ast.python.literals;

import ast.python.expressions.ExpressionNode;

public abstract class LiteralNode extends ExpressionNode {
    public LiteralNode(int line, int column) {
        super(line, column);
    }
}
