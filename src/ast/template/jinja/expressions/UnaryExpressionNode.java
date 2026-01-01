package ast.template.jinja.expressions;
import ast.template.TemplateNode;

import java.util.*;
import ast.template.NodeKind;
import ast.visitors.TemplateASTVisitor;

public class UnaryExpressionNode extends ExpressionNode {
    private String operator;
    private ExpressionNode operand;

    public UnaryExpressionNode(int line, int column, String operator, ExpressionNode operand) {
        super(NodeKind.JINJA_EXPR_UNARY, line, column);
        this.operator = operator;
        this.operand = operand;
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public ExpressionNode getOperand() { return operand; }
    public void setOperand(ExpressionNode operand) { this.operand = operand; }
    @Override
    public List<TemplateNode> getChildren() {
        return operand != null ? Collections.singletonList(operand) : Collections.emptyList();
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
