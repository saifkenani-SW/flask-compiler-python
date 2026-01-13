package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;
public class CSSStringValueNode extends CSSValueNode {
    public CSSStringValueNode(int line, int column, String value) {
        super(NodeKind.CSS_STRING, line, column, value);
    }
    @Override
    public List<TemplateNode> getChildren() {
        return List.of(); // ليس لهم أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
