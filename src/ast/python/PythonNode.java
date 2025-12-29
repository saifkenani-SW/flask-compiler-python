package ast.python;

import ast.ASTNode;
import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class PythonNode extends ASTNode {

    private final List<PythonNode> children = new ArrayList<>();
    private PythonNode parent;

    protected PythonNode(int line, int column) {
        super(line, column);
    }

    public List<PythonNode> getChildren() {
        return children;
    }

    public void addChild(PythonNode child) {
        if (child != null) {
            child.parent = this;
            children.add(child);
        }
    }

    public PythonNode getParent() {
        return parent;
    }

    public abstract <T> T accept(PythonASTVisitor<T> visitor);
}
