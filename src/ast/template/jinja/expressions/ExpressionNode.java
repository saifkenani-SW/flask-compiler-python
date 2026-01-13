package ast.template.jinja.expressions;

import ast.template.NodeKind;
import ast.template.jinja.JinjaNode;

public abstract class ExpressionNode extends JinjaNode {
    public ExpressionNode(NodeKind nodeType, int line, int column) {
        super(nodeType, line, column);
    }
}
