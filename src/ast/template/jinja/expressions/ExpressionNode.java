package ast.template.jinja.expressions;

import ast.template.jinja.JinjaNode;

public abstract class ExpressionNode extends JinjaNode {
    public ExpressionNode(String nodeType, int line, int column) {
        super("Expression:" + nodeType, line, column);
    }
}
