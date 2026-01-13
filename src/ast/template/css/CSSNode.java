package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;

import java.util.List;
public abstract class CSSNode extends TemplateNode {
    public CSSNode(NodeKind nodeType, int line, int column) {
        super(nodeType, line, column);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return List.of(); // Abstract node، ليس له أبناء بشكل عام
    }

}
