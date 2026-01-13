package ast.template.jinja.expressions;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class VariableNode extends ExpressionNode {
    private List<String> path;

    public VariableNode(int line, int column, String name) {
        super(NodeKind.JINJA_EXPR_VARIABLE, line, column);
        this.path = new ArrayList<>();
        if (name != null) {
            this.path.add(name);
        }
    }

    // Getters
    public List<String> getPath() {
        return Collections.unmodifiableList(path);
    }

    public String getName() {
        return path.isEmpty() ? null : path.get(0);
    }

    public String getFullPath() {
        return String.join(".", path);
    }

    public String getLastSegment() {
        return path.isEmpty() ? null : path.get(path.size() - 1);
    }

    // Add methods
    public void addPathSegment(String segment) {
        if (segment != null) {
            this.path.add(segment);
        }
    }

    public void addAllPathSegments(List<String> segments) {
        if (segments != null) {
            this.path.addAll(segments);
        }
    }

    // Remove methods
    public String removeLastSegment() {
        if (!path.isEmpty()) {
            return path.remove(path.size() - 1);
        }
        return null;
    }

    // Utility methods
    public boolean isSimpleVariable() {
        return path.size() == 1;
    }

    public boolean isAttributeAccess() {
        return path.size() > 1;
    }

    public int getDepth() {
        return path.size();
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public void clearPath() {
        this.path.clear();
    }

    public boolean startsWith(String prefix) {
        return !path.isEmpty() && path.get(0).equals(prefix);
    }

    @Override
    public String toString() {
        return String.format("VariableNode{path='%s', line=%d, column=%d}",
                getFullPath(), getLine(), getColumn());
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
