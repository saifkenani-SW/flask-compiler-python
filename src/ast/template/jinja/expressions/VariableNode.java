package ast.template.jinja.expressions;

import java.util.ArrayList;
import java.util.List;

public class VariableNode extends ExpressionNode {
    private List<String> path;

    public VariableNode(int line, int column, String name) {
        super("Variable", line, column);
        this.path = new ArrayList<>();
        this.path.add(name);
    }

    public VariableNode(int line, int column, List<String> path) {
        super("Variable", line, column);
        this.path = new ArrayList<>(path);
    }

    public List<String> getPath() { return path; }
    public void setPath(List<String> path) { this.path = path; }

    public void addPathSegment(String segment) { path.add(segment); }

    public String getFullPath() {
        return String.join(".", path);
    }
}
