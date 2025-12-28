package ast.template.jinja.expressions;

public class UnaryExpressionNode extends ExpressionNode {
    private String operator;
    private ExpressionNode operand;

    public UnaryExpressionNode(int line, int column, String operator, ExpressionNode operand) {
        super("Unary", line, column);
        this.operator = operator;
        this.operand = operand;
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public ExpressionNode getOperand() { return operand; }
    public void setOperand(ExpressionNode operand) { this.operand = operand; }
}
