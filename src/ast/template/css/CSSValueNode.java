package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;

import java.util.List;
public abstract class CSSValueNode extends CSSNode {
    private String value;

    public CSSValueNode(NodeKind nodeType, int line, int column, String value) {
        super(nodeType, line, column);
        this.value = value;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    @Override
    public List<TemplateNode> getChildren() {
        return List.of(); // ليس له أبناء
    }

}
