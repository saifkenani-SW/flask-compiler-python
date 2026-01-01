package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;

public abstract class HTMLNode extends TemplateNode {
    public HTMLNode(NodeKind nodeType, int line, int column) {
        super(nodeType, line, column);
    }
}
