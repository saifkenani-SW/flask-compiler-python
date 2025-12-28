package ast.python.literals;

import ast.ASTVisitor;
import ast.python.expressions.ExpressionNode;
import java.util.ArrayList;
import java.util.List;

/**
 * قاموس
 */
public class DictLiteralNode extends LiteralNode {
    private List<DictEntry> entries;

    public static class DictEntry {
        private ExpressionNode key;
        private ExpressionNode value;

        public DictEntry(ExpressionNode key, ExpressionNode value) {
            this.key = key;
            this.value = value;
        }

        public ExpressionNode getKey() { return key; }
        public ExpressionNode getValue() { return value; }
    }

    public DictLiteralNode(int line, int column) {
        super(line, column);
        this.entries = new ArrayList<>();
    }

    public List<DictEntry> getEntries() { return entries; }
    public void addEntry(ExpressionNode key, ExpressionNode value) {
        entries.add(new DictEntry(key, value));
        addChild(key);
        addChild(value);
    }

    public boolean isEmpty() { return entries.isEmpty(); }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
