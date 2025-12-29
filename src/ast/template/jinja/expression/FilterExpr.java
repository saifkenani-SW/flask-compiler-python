package ast.template.jinja.expression;

import java.util.List;
//name | upper | truncate(10)


public final class FilterExpr implements Expr {

    private final Expr target;
    private final String filterName;
    private final List<Expr> arguments;

    public FilterExpr(Expr target, String filterName, List<Expr> arguments) {
        this.target = target;
        this.filterName = filterName;
        this.arguments = List.copyOf(arguments);
    }

    public Expr getTarget() {
        return target;
    }

    public String getFilterName() {
        return filterName;
    }

    public List<Expr> getArguments() {
        return arguments;
    }
}
