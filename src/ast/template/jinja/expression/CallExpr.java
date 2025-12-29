package ast.template.jinja.expression;

import java.util.List;
//func(a, b)
public final class CallExpr implements Expr {

    private final Expr callee;
    private final List<Expr> arguments;

    public CallExpr(Expr callee, List<Expr> arguments) {
        this.callee = callee;
        this.arguments = List.copyOf(arguments);
    }

    public Expr getCallee() {
        return callee;
    }

    public List<Expr> getArguments() {
        return arguments;
    }
}
