package ast.python.program;

import ast.python.PythonNode;
import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends PythonNode {
    private List<PythonNode> statements;

    public BlockNode(int line, int column) {
        super(line, column);
        this.statements = new ArrayList<>();
    }

    public List<PythonNode> getStatements() { return statements; }
    public void addStatement(PythonNode statement) {
        statements.add(statement);
        addChild(statement);
    }
    public void addStatements(List<PythonNode> statements) {
        for (PythonNode stmt : statements) {
            addStatement(stmt);
        }
    }

    public boolean isEmpty() { return statements.isEmpty(); }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
