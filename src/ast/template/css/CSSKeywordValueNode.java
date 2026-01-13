package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;
public class CSSKeywordValueNode extends CSSValueNode {
    public CSSKeywordValueNode(int line, int column, String value) {
        super(NodeKind.CSS_KEYWORD, line, column, value);
    }
    @Override
    public List<TemplateNode> getChildren() {
        return List.of(); // ليس له أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
