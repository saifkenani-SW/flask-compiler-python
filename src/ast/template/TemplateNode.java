package ast.template;

import ast.ASTNode;

public abstract class TemplateNode extends ASTNode {

    protected String nodeType;

    protected TemplateNode(String nodeType, int line, int column) {
        super(line, column);
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }
}
