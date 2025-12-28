package ast.template.css;

import java.util.ArrayList;
import java.util.List;

public class CSSDeclarationNode extends CSSNode {
    private String property;
    private List<CSSValueNode> values;

    public CSSDeclarationNode(int line, int column, String property) {
        super("Declaration", line, column);
        this.property = property;
        this.values = new ArrayList<>();
    }

    public String getProperty() { return property; }
    public void setProperty(String property) { this.property = property; }

    public List<CSSValueNode> getValues() { return values; }
    public void addValue(CSSValueNode value) {
        values.add(value);
    }
}
