package ast.template.jinja.expressions;

import ast.template.NodeKind;

import java.util.ArrayList;
import java.util.List;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class CallExpressionNode extends ExpressionNode {
    private ExpressionNode callee;
    private List<ExpressionNode> arguments;

    public CallExpressionNode(int line, int column, ExpressionNode callee) {
        super(NodeKind.JINJA_EXPR_CALL, line, column);
        this.callee = callee;
        this.arguments = new ArrayList<>();
    }

    // Getters
    public ExpressionNode getCallee() {
        return callee;
    }

    public List<ExpressionNode> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    public int getArgumentCount() {
        return arguments.size();
    }

    // Setters
    public void setCallee(ExpressionNode callee) {
        this.callee = callee;
    }

    // Add methods
    public void addArgument(ExpressionNode argument) {
        if (argument != null) {
            this.arguments.add(argument);
        }
    }

    public void addAllArguments(List<ExpressionNode> arguments) {
        if (arguments != null) {
            this.arguments.addAll(arguments);
        }
    }

    public void addArgument(int index, ExpressionNode argument) {
        if (argument != null && index >= 0 && index <= arguments.size()) {
            this.arguments.add(index, argument);
        }
    }

    // Remove methods
    public ExpressionNode removeArgument(int index) {
        if (index >= 0 && index < arguments.size()) {
            return this.arguments.remove(index);
        }
        return null;
    }

    public boolean removeArgument(ExpressionNode argument) {
        return this.arguments.remove(argument);
    }

    // Utility methods
    public boolean hasArguments() {
        return !this.arguments.isEmpty();
    }

    public void clearArguments() {
        this.arguments.clear();
    }

    public ExpressionNode getArgument(int index) {
        if (index >= 0 && index < arguments.size()) {
            return this.arguments.get(index);
        }
        return null;
    }

    public boolean isMethodCall() {
        return callee instanceof VariableNode &&
                ((VariableNode) callee).getPath().size() > 1;
    }

    public String getMethodName() {
        if (isMethodCall()) {
            VariableNode var = (VariableNode) callee;
            return var.getPath().get(var.getPath().size() - 1);
        }
        return null;
    }

    @Override
    public String toString() {
        String calleeStr = callee != null ? callee.toString() : "null";
        return String.format("CallExpressionNode{callee=%s, arguments=%d, line=%d, column=%d}",
                calleeStr, arguments.size(), getLine(), getColumn());
    }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        if (callee != null) children.add(callee);
        if (arguments != null) children.addAll(arguments);
        return children;
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
