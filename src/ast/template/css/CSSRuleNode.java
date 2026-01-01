package ast.template.css;

import ast.template.NodeKind;

import java.util.ArrayList;
import java.util.List;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
public class CSSRuleNode extends CSSNode {
    private List<CSSSelectorNode> selectors;
    private List<CSSDeclarationNode> declarations;

    public CSSRuleNode(int line, int column) {
        super(NodeKind.CSS_RULE, line, column);
        this.selectors = new ArrayList<>();
        this.declarations = new ArrayList<>();
    }

    public List<CSSSelectorNode> getSelectors() { return selectors; }
    public void addSelector(CSSSelectorNode selector) {
        selectors.add(selector);
    }

    public List<CSSDeclarationNode> getDeclarations() { return declarations; }
    public void addDeclaration(CSSDeclarationNode declaration) {
        declarations.add(declaration);
    }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        children.addAll(selectors);    // CSSSelectorNode
        children.addAll(declarations); // CSSDeclarationNode
        return children;
    }
    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
