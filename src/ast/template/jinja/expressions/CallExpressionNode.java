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

    public ExpressionNode getCallee() { return callee; }
    public void setCallee(ExpressionNode callee) { this.callee = callee; }

    public List<ExpressionNode> getArguments() { return arguments; }
    public void addArgument(ExpressionNode argument) {
        arguments.add(argument);
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
