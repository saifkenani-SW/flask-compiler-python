package ast.python.declarations;

import ast.python.PythonNode;
import ast.ASTVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * ديكوراتور دالة
 */
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
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
