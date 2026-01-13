package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CSSDeclarationNode extends CSSNode {
    private String property;
    private List<CSSValueNode> values;

    public CSSDeclarationNode(int line, int column, String property) {
        super(NodeKind.CSS_DECLARATION, line, column);
        this.property = property;
        this.values = new ArrayList<>();
    }

    // Getters
    public String getProperty() {
        return property;
    }

    public List<CSSValueNode> getValues() {
        return Collections.unmodifiableList(values);
    }

    // Setters
    public void setProperty(String property) {
        this.property = property;
    }

    // Add methods
    public void addValue(CSSValueNode value) {
        if (value != null) {
            this.values.add(value);
        }
    }

    public void addAllValues(List<CSSValueNode> values) {
        if (values != null) {
            this.values.addAll(values);
        }
    }

    // Remove methods
    public boolean removeValue(CSSValueNode value) {
        return this.values.remove(value);
    }

    // Utility methods
    public boolean hasValues() {
        return !this.values.isEmpty();
    }

    public void clearValues() {
        this.values.clear();
    }

    public String getValueText() {
        return values.stream()
                .map(CSSValueNode::getValue)
                .collect(Collectors.joining(" "));
    }

    public <T extends CSSValueNode> List<T> getValuesByType(Class<T> type) {
        return values.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("CSSDeclarationNode{property='%s', values=%d, line=%d, column=%d}",
                property, values.size(), getLine(), getColumn());
    }

    @Override
    public List<TemplateNode> getChildren() {
        return new ArrayList<>(values); // CSSValueNode
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
