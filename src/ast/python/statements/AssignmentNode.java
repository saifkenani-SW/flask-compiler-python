package ast.python.statements;

import ast.python.PythonNode;
import ast.python.expressions.ExpressionNode;
import ast.python.visitors.PythonASTVisitor;

public class AssignmentNode extends StatementNode {
    private PythonNode target;
    private String operator; // =, +=, -=, *=, /=, %=, **=, /=
    private ExpressionNode value;

    public AssignmentNode(int line, int column, PythonNode target, ExpressionNode value) {
        super(line, column);
        this.target = target;
        this.operator = "=";
        this.value = value;
        addChild(target);
        addChild(value);
    }

    public AssignmentNode(int line, int column, PythonNode target, String operator, ExpressionNode value) {
        super(line, column);
        this.target = target;
        this.operator = operator;
        this.value = value;
        addChild(target);
        addChild(value);
    }

    public PythonNode getTarget() { return target; }
    public void setTarget(PythonNode target) {
        this.target = target;
        addChild(target);
    }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public ExpressionNode getValue() { return value; }
    public void setValue(ExpressionNode value) {
        this.value = value;
        addChild(value);
    }

    public boolean isAugmented() {
        return !operator.equals("=");
    }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
