package ast.template.jinja.expressions;

import java.util.ArrayList;
import java.util.List;

public class FilterExpressionNode extends ExpressionNode {
    private ExpressionNode input;
    private String filterName;
    private List<ExpressionNode> arguments;

    public FilterExpressionNode(int line, int column, ExpressionNode input, String filterName) {
        super("Filter", line, column);
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
}
