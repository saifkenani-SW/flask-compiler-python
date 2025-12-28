package ast.template.jinja;

import ast.template.TemplateNode;

public abstract class JinjaNode extends TemplateNode {
    public JinjaNode(String nodeType, int line, int column) {
        super("Jinja:" + nodeType, line, column);
    }
}
