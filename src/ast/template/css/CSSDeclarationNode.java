package ast.template.css;

import ast.template.NodeKind;

import java.util.ArrayList;
import java.util.List;import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;

public class CSSDeclarationNode extends CSSNode {
    private String property;
    private List<CSSValueNode> values;

    public CSSDeclarationNode(int line, int column, String property) {
        super(NodeKind.CSS_DECLARATION, line, column);
        this.property = property;
        this.values = new ArrayList<>();
    }

    public String getProperty() { return property; }
    public void setProperty(String property) { this.property = property; }

    public List<CSSValueNode> getValues() { return values; }
    public void addValue(CSSValueNode value) {
        values.add(value);
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
