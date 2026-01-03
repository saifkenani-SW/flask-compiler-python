package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public class ElseBlockNode extends JinjaBlockNode {
    public ElseBlockNode(int line, int column) {
        super(NodeKind.JINJA_ELSE_BLOCK, line, column);
    }


    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
}
