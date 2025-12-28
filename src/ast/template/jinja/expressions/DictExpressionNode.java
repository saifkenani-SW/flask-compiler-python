package ast.template.jinja.expressions;

import java.util.LinkedHashMap;
import java.util.Map;

public class DictExpressionNode extends ExpressionNode {
    private Map<ExpressionNode, ExpressionNode> pairs;

    public DictExpressionNode(int line, int column) {
        super("Dict", line, column);
        this.pairs = new LinkedHashMap<>();
    }

    public Map<ExpressionNode, ExpressionNode> getPairs() { return pairs; }
    public void addPair(ExpressionNode key, ExpressionNode value) {
        pairs.put(key, value);
    }
}
