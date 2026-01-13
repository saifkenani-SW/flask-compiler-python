package ast.template.html;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class DoctypeNode extends HTMLNode {

    public String getDoctype() {
        return doctype;
    }

    String doctype;
    public DoctypeNode(int line, int column, String doctype) {
        super(NodeKind.DOCTYPE, line, column);
        this.doctype = doctype;
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
