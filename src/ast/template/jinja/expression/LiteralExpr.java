package ast.template.jinja.expression;

public sealed interface LiteralExpr extends Expr
        permits
        StringLiteral,
        NumberLiteral,
        BooleanLiteral,
        NoneLiteral {
}
