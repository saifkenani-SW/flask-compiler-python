package ast.visitors;

import ast.template.TemplateNode;

public abstract class TemplateBaseASTVisitor<T>
        implements TemplateASTVisitor<T> {

    protected T visitChildren(TemplateNode node) {
        for (TemplateNode child : node.getChildren()) {
            if (child != null) {
                child.accept(this);
            }
        }
        return null;
    }
}
