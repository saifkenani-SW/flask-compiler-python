package ast.template.jinja.expressions;

import ast.template.NodeKind;

import java.util.LinkedHashMap;
import java.util.Map;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class DictExpressionNode extends ExpressionNode {
    private Map<ExpressionNode, ExpressionNode> pairs;

    public DictExpressionNode(int line, int column) {
        super(NodeKind.JINJA_EXPR_DICT, line, column);
        this.pairs = new LinkedHashMap<>();
    }

    public Map<ExpressionNode, ExpressionNode> getPairs() { return pairs; }
    public void addPair(ExpressionNode key, ExpressionNode value) {
        pairs.put(key, value);
    }
    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        if (pairs != null) {
            for (Map.Entry<ExpressionNode, ExpressionNode> entry : pairs.entrySet()) {
                if (entry.getKey() != null) children.add(entry.getKey());
                if (entry.getValue() != null) children.add(entry.getValue());
            }
        }
        return children;
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
