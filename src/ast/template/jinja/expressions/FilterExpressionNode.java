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

    // Getters
    public ExpressionNode getInput() {
        return input;
    }

    public String getFilterName() {
        return filterName;
    }

    public List<ExpressionNode> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    // Setters
    public void setInput(ExpressionNode input) {
        this.input = input;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    // Add methods (similar to CallExpressionNode)
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

    // Remove methods
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

    public int getTotalParameters() {
        return 1 + arguments.size(); // input + arguments
    }

    public boolean isChained() {
        return input instanceof FilterExpressionNode;
    }

    @Override
    public String toString() {
        return String.format("FilterExpressionNode{filter='%s', input=%s, arguments=%d, line=%d, column=%d}",
                filterName,
                input != null ? input.getClass().getSimpleName() : "null",
                arguments.size(), getLine(), getColumn());
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
