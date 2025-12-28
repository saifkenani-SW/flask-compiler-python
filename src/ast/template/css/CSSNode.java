package ast.template.css;

import ast.template.TemplateNode;

public abstract class CSSNode extends TemplateNode {
    public CSSNode(String nodeType, int line, int column) {
        super("CSS:" + nodeType, line, column);
    }
}
