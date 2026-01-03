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

    // Getters
    public Map<ExpressionNode, ExpressionNode> getPairs() {
        return Collections.unmodifiableMap(pairs);
    }

    // Add methods
    public void putPair(ExpressionNode key, ExpressionNode value) {
        if (key != null && value != null) {
            this.pairs.put(key, value);
        }
    }

    public void putAllPairs(Map<ExpressionNode, ExpressionNode> pairs) {
        if (pairs != null) {
            this.pairs.putAll(pairs);
        }
    }

    // Remove methods
    public ExpressionNode removePair(ExpressionNode key) {
        return this.pairs.remove(key);
    }

    // Utility methods
    public boolean hasPairs() {
        return !this.pairs.isEmpty();
    }

    public int size() {
        return this.pairs.size();
    }

    public boolean isEmpty() {
        return this.pairs.isEmpty();
    }

    public void clearPairs() {
        this.pairs.clear();
    }

    public boolean containsKey(ExpressionNode key) {
        return this.pairs.containsKey(key);
    }

    public boolean containsValue(ExpressionNode value) {
        return this.pairs.containsValue(value);
    }

    public Set<ExpressionNode> keySet() {
        return this.pairs.keySet();
    }

    public Collection<ExpressionNode> values() {
        return this.pairs.values();
    }

    public Set<Map.Entry<ExpressionNode, ExpressionNode>> entrySet() {
        return this.pairs.entrySet();
    }

    @Override
    public String toString() {
        return String.format("DictExpressionNode{line=%d, column=%d, size=%d}",
                getLine(), getColumn(), pairs.size());
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
