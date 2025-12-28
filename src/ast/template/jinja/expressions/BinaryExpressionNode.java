package ast.template.jinja.expressions;

public class BinaryExpressionNode extends ExpressionNode {
    private String operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public BinaryExpressionNode(int line, int column, String operator,
                               ExpressionNode left, ExpressionNode right) {
        super("Binary", line, column);
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public ExpressionNode getLeft() { return left; }
    public void setLeft(ExpressionNode left) { this.left = left; }

    public ExpressionNode getRight() { return right; }
    public void setRight(ExpressionNode right) { this.right = right; }
}
