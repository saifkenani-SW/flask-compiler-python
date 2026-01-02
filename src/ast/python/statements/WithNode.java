package ast.python.statements;

import ast.python.expressions.ExpressionNode;
import ast.python.expressions.IdentifierNode;
import ast.python.program.BlockNode;
import ast.python.visitors.PythonASTVisitor;

public class WithNode extends StatementNode {
    private ExpressionNode expression;
    private IdentifierNode alias; // بعد as
    private BlockNode body;

    public WithNode(int line, int column, ExpressionNode expression, BlockNode body) {
        super(line, column);
        this.expression = expression;
        this.body = body;
        addChild(expression);
        addChild(body);
    }

    public ExpressionNode getExpression() { return expression; }
    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
        addChild(expression);
    }

    public IdentifierNode getAlias() { return alias; }
    public void setAlias(IdentifierNode alias) {
        this.alias = alias;
        addChild(alias);
    }

    public BlockNode getBody() { return body; }
    public void setBody(BlockNode body) {
        this.body = body;
        addChild(body);
    }

    public boolean hasAlias() { return alias != null; }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
