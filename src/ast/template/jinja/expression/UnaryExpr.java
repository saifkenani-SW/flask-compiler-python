package ast.template.jinja.expression;


//  -not x
public final class UnaryExpr implements Expr {

    private final String operator;
    private final Expr operand;

    public UnaryExpr(String operator, Expr operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
    }

    public Expr getOperand() {
        return operand;
    }
}
