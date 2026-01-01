package ast.template.jinja.expressions;

import ast.template.NodeKind;

import java.util.ArrayList;
import java.util.List;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class FilterExpressionNode extends ExpressionNode {
    private ExpressionNode input;
    private String filterName;
    private List<ExpressionNode> arguments;

    public FilterExpressionNode(int line, int column, ExpressionNode input, String filterName) {
        super(NodeKind.JINJA_EXPR_FILTER, line, column);
        this.input = input;
        this.filterName = filterName;
        this.arguments = new ArrayList<>();
    }

    public ExpressionNode getInput() { return input; }
    public void setInput(ExpressionNode input) { this.input = input; }

    public String getFilterName() { return filterName; }
    public void setFilterName(String filterName) { this.filterName = filterName; }

    public List<ExpressionNode> getArguments() { return arguments; }
    public void addArgument(ExpressionNode argument) {
        arguments.add(argument);
    }
    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        if (input != null) children.add(input);
        if (arguments != null) children.addAll(arguments);
        return children;
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
