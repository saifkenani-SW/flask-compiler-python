package ast.template.jinja.expression;

public sealed interface Expr
        permits
        LiteralExpr,
        IdentifierExpr,
        BinaryExpr,
        UnaryExpr,
        CallExpr,
        ListExpr,
        DictExpr,
        FilterExpr {
}
