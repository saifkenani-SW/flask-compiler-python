package ast.python.declarations;

import ast.python.PythonNode;
import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class DecoratorNode extends PythonNode {
    private String name;
    private List<PythonNode> arguments;

    public DecoratorNode(int line, int column, String name) {
        super(line, column);
        this.name = name;
        this.arguments = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<PythonNode> getArguments() { return arguments; }
    public void addArgument(PythonNode argument) {
        arguments.add(argument);
        addChild(argument);
    }
    public void addArguments(List<PythonNode> args) {
        for (PythonNode arg : args) {
            addArgument(arg);
        }
    }

    public boolean hasArguments() { return !arguments.isEmpty(); }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
