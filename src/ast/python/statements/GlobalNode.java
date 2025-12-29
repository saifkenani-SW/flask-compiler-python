package ast.python.statements;

import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * جملة global
 */
public class GlobalNode extends StatementNode {
    private List<String> variables;

    public GlobalNode(int line, int column) {
        super(line, column);
        this.variables = new ArrayList<>();
    }

    public List<String> getVariables() { return variables; }
    public void addVariable(String variable) { variables.add(variable); }
    public void addVariables(List<String> vars) { variables.addAll(vars); }

    public boolean isEmpty() { return variables.isEmpty(); }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
