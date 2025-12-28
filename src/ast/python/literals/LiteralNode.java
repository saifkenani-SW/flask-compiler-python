package ast.python.literals;

import ast.python.expressions.ExpressionNode;

/**
 * فئة أساسية لجميع القيم الحرفية
 */
public abstract class LiteralNode extends ExpressionNode {
    public LiteralNode(int line, int column) {
        super(line, column);
    }
}
