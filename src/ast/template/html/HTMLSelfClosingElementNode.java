package ast.template.html;


import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public final class HTMLSelfClosingElementNode extends HTMLElementNode {
    public HTMLSelfClosingElementNode(int line, int column, String tagName) {
        super(NodeKind.HTML_SELF_CLOSING_ELEMENT, line, column, tagName); // Self-closing element
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
