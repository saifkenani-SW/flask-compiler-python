package ast.template.jinja.expression;

import java.util.List;
//[1, a + b, foo()]


public final class ListExpr implements Expr {

    private final List<Expr> elements;

    public ListExpr(List<Expr> elements) {
        this.elements = List.copyOf(elements);
    }

    public List<Expr> getElements() {
        return elements;
    }
}
