package ast.python.expressions;

import ast.ASTVisitor;

/**
 * عملية أحادية (+, -, not)
 */
public class UnaryOpNode extends ExpressionNode {
    private String operator;
    private ExpressionNode operand;

    public UnaryOpNode(int line, int column, String operator, ExpressionNode operand) {
        super(line, column);
        this.operator = operator;
        this.operand = operand;
        addChild(operand);
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public ExpressionNode getOperand() { return operand; }
    public void setOperand(ExpressionNode operand) {
        this.operand = operand;
        addChild(operand);
    }

    public boolean isLogicalNot() {
        return operator.equals("not");
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
