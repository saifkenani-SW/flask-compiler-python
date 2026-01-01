package ast.template.jinja;

import ast.template.NodeKind;
import ast.template.TemplateNode;

public abstract class JinjaNode extends TemplateNode {
    public JinjaNode(NodeKind nodeType, int line, int column) {
        super(nodeType, line, column);
    }
}
