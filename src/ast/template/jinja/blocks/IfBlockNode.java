package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.expressions.ExpressionNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IfBlockNode extends JinjaBlockNode {
    private ExpressionNode condition;


    public IfBlockNode(int line, int column, ExpressionNode condition) {
        super(NodeKind.JINJA_IF_BLOCK, line, column);
        this.condition = condition;
    }

    // Getters
    public ExpressionNode getCondition() {
        return condition;
    }



    // Setters
    public void setCondition(ExpressionNode condition) {
        this.condition = condition;
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
