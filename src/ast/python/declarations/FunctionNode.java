package ast.python.declarations;

import ast.python.PythonNode;
import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionNode extends PythonNode {
    private String name;
    private List<ParameterNode> parameters;
    private PythonNode body;
    private List<DecoratorNode> decorators;
    private String returnType;

    public FunctionNode(int line, int column, String name) {
        super(line, column);
        this.name = name;
        this.parameters = new ArrayList<>();
        this.decorators = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<ParameterNode> getParameters() { return parameters; }
    public void addParameter(ParameterNode param) {
        parameters.add(param);
        addChild(param);
    }
    public void addParameters(List<ParameterNode> params) {
        for (ParameterNode param : params) {
            addParameter(param);
        }
    }

    public PythonNode getBody() { return body; }
    public void setBody(PythonNode body) {
        this.body = body;
        addChild(body);
    }

    public List<DecoratorNode> getDecorators() { return decorators; }
    public void addDecorator(DecoratorNode decorator) {
        decorators.add(decorator);
        addChild(decorator);
    }

    public String getReturnType() { return returnType; }
    public void setReturnType(String returnType) { this.returnType = returnType; }

    public boolean hasParameters() { return !parameters.isEmpty(); }
    public boolean hasDecorators() { return !decorators.isEmpty(); }
    public boolean hasReturnType() { return returnType != null && !returnType.isEmpty(); }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
