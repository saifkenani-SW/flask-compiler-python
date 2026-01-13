package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.jinja.expressions.ExpressionNode;
import ast.visitors.TemplateASTVisitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ForBlockNode extends JinjaBlockNode {
    private String variable;
    private ExpressionNode iterable;
    private ElseBlockNode elseBlock;

    public ForBlockNode(int line, int column, String variable, ExpressionNode iterable) {
        super(NodeKind.JINJA_FOR_BLOCK, line, column);
        this.variable = variable;
        this.iterable = iterable;
    }

    // Getters
    public String getVariable() {
        return variable;
    }

    public ExpressionNode getIterable() {
        return iterable;
    }

    public ElseBlockNode getElseBlock() {
        return elseBlock;
    }

    // Setters
    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void setIterable(ExpressionNode iterable) {
        this.iterable = iterable;
    }

    public void setElseBlock(ElseBlockNode elseBlock) {
        this.elseBlock = elseBlock;
    }

    // Utility methods
    public boolean hasElseBlock() {
        return this.elseBlock != null;
    }

    public boolean isSingleVariable() {
        return variable != null && !variable.contains(",");
    }

    public List<String> getVariables() {
        if (variable == null) return Collections.emptyList();

        return Arrays.stream(variable.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("ForBlockNode{variable='%s', line=%d, column=%d, hasElse=%b, content=%d}",
                variable, getLine(), getColumn(), hasElseBlock(), getContent().size());
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of();
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
