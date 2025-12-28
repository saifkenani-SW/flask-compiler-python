package ast.template.jinja.expressions;

import java.util.ArrayList;
import java.util.List;

public class CallExpressionNode extends ExpressionNode {
    private ExpressionNode callee;
    private List<ExpressionNode> arguments;

    public CallExpressionNode(int line, int column, ExpressionNode callee) {
        super("Call", line, column);
        this.callee = callee;
        this.arguments = new ArrayList<>();
    }

    public ExpressionNode getCallee() { return callee; }
    public void setCallee(ExpressionNode callee) { this.callee = callee; }

    public List<ExpressionNode> getArguments() { return arguments; }
    public void addArgument(ExpressionNode argument) {
        arguments.add(argument);
    }
}
