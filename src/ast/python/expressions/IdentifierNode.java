package ast.python.expressions;


import ast.python.visitors.PythonASTVisitor;

public class IdentifierNode extends ExpressionNode {
    private String name;

    public IdentifierNode(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
