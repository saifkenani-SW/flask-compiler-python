package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;
public class CSSSelectorNode extends CSSNode {
    private String selector;

    public CSSSelectorNode(int line, int column, String selector) {
        super(NodeKind.CSS_SELECTOR, line, column);
        this.selector = selector;
    }

    public String getSelector() { return selector; }
    public void setSelector(String selector) { this.selector = selector; }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of(); // ليس له أبناء
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
