package ast.python.expressions;

import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * استدعاء دالة
 */
public class CallNode extends ExpressionNode {
    private ExpressionNode function;
    private List<ExpressionNode> arguments;

    public CallNode(int line, int column, ExpressionNode function) {
        super(line, column);
        this.function = function;
        this.arguments = new ArrayList<>();
        addChild(function);
    }

    public ExpressionNode getFunction() { return function; }
    public void setFunction(ExpressionNode function) {
        this.function = function;
        addChild(function);
    }

    public List<ExpressionNode> getArguments() { return arguments; }
    public void addArgument(ExpressionNode argument) {
        arguments.add(argument);
        addChild(argument);
    }
    public void addArguments(List<ExpressionNode> args) {
        for (ExpressionNode arg : args) {
            addArgument(arg);
        }
    }

    public int getArgumentCount() { return arguments.size(); }
    public boolean hasArguments() { return !arguments.isEmpty(); }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
