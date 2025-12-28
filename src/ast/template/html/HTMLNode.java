package ast.template.html;

import ast.template.TemplateNode;

public abstract class HTMLNode extends TemplateNode {
    public HTMLNode(String nodeType, int line, int column) {
        super("HTML:" + nodeType, line, column);
    }
}
