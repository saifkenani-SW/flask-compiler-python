//package ast.template;
//
//import ast.ASTNode;
//
//public abstract class TemplateNode extends ASTNode {
//
//    protected String nodeType;
//
//    protected TemplateNode(String nodeType, int line, int column) {
//        super(line, column);
//        this.nodeType = nodeType;
//    }
//
//    public String getNodeType() {
//        return nodeType;
//    }
//}

package ast.template;

import ast.ASTNode;
import ast.visitors.TemplateASTVisitor;

import java.util.List;

public abstract class TemplateNode extends ASTNode {

    protected final NodeKind nodeType;

    protected TemplateNode(NodeKind nodeType, int line, int column) {
        super(line, column);
        this.nodeType = nodeType;
    }

    public NodeKind getNodeType() {
        return nodeType;
    }

    public int getLine() { return line; }
    public int getColumn() { return column; }

    public abstract List<TemplateNode> getChildren();
    public abstract <T> T accept(TemplateASTVisitor<T> visitor);

}
