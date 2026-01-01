package ast.template.jinja.expressions;

import ast.template.NodeKind;

import java.util.ArrayList;
import java.util.List;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class VariableNode extends ExpressionNode {
    private List<String> path;

    public VariableNode(int line, int column, String name) {
        super(NodeKind.JINJA_EXPR_VARIABLE, line, column);
        this.path = new ArrayList<>();
        this.path.add(name);
    }

    public VariableNode(int line, int column, List<String> path) {
        super(NodeKind.JINJA_EXPR_VARIABLE, line, column);
        this.path = new ArrayList<>(path);
    }

    public List<String> getPath() { return path; }
    public void setPath(List<String> path) { this.path = path; }

    public void addPathSegment(String segment) { path.add(segment); }

    public String getFullPath() {
        return String.join(".", path);
    }
    @Override
    public List<TemplateNode> getChildren() {
        return Collections.emptyList(); // لا يوجد ExpressionNode داخله، فقط أسماء متغيرات
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
