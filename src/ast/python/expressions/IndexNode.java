package ast.python.expressions;

import ast.ASTVisitor;

/**
 * فهرسة (list[index])
 */
public class IndexNode extends ExpressionNode {
    private ExpressionNode collection;
    private ExpressionNode index;

    public IndexNode(int line, int column, ExpressionNode collection, ExpressionNode index) {
        super(line, column);
        this.collection = collection;
        this.index = index;
        addChild(collection);
        addChild(index);
    }

    public ExpressionNode getCollection() { return collection; }
    public void setCollection(ExpressionNode collection) {
        this.collection = collection;
        addChild(collection);
    }

    public ExpressionNode getIndex() { return index; }
    public void setIndex(ExpressionNode index) {
        this.index = index;
        addChild(index);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
