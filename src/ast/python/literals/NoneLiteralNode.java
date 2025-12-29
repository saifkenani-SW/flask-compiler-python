package ast.python.literals;


import ast.python.visitors.PythonASTVisitor;

public class NoneLiteralNode extends LiteralNode {

    public NoneLiteralNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
