package ast.template.jinja.expression;

import java.util.List;
//user.profile.name


public final class IdentifierExpr implements Expr {

    private final List<String> path;

    public IdentifierExpr(List<String> path) {
        this.path = List.copyOf(path);
    }

    public List<String> getPath() {
        return path;
    }
}
