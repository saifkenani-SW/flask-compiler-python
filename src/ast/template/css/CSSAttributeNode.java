package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;
public class CSSAttributeNode extends CSSNode {
    private String name;
    private String value;

    public CSSAttributeNode(int line, int column, String name) {
        super(NodeKind.CSS_ATTRIBUTE, line, column);
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    @Override
    public List<TemplateNode> getChildren() {
        return List.of(); // ليس له أبناء
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
