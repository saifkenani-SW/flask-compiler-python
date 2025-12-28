package ast.python.expressions;

import ast.ASTVisitor;

/**
 * عملية ثنائية (+, -, *, /, ==, !=, <, >, <=, >=, and, or, in, is)
 */
public class BinaryOpNode extends ExpressionNode {
    private String operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public BinaryOpNode(int line, int column, String operator,
                       ExpressionNode left, ExpressionNode right) {
        super(line, column);
        this.operator = operator;
        this.left = left;
        this.right = right;
        addChild(left);
        addChild(right);
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public ExpressionNode getLeft() { return left; }
    public void setLeft(ExpressionNode left) {
        this.left = left;
        addChild(left);
    }

    public ExpressionNode getRight() { return right; }
    public void setRight(ExpressionNode right) {
        this.right = right;
        addChild(right);
    }

    public boolean isLogical() {
        return operator.equals("and") || operator.equals("or");
    }

    public boolean isComparison() {
        return operator.equals("==") || operator.equals("!=") ||
               operator.equals("<") || operator.equals("<=") ||
               operator.equals(">") || operator.equals(">=") ||
               operator.equals("in") || operator.equals("is");
    }

    public boolean isArithmetic() {
        return operator.equals("+") || operator.equals("-") ||
               operator.equals("*") || operator.equals("/") ||
               operator.equals("%") || operator.equals("**") ||
               operator.equals("//");
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
