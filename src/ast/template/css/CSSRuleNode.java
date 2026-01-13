package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CSSRuleNode extends CSSNode {
    private List<CSSSelectorNode> selectors;
    private List<CSSDeclarationNode> declarations;

    public CSSRuleNode(int line, int column) {
        super(NodeKind.CSS_RULE, line, column);
        this.selectors = new ArrayList<>();
        this.declarations = new ArrayList<>();
    }

    // Getters
    public List<CSSSelectorNode> getSelectors() {
        return Collections.unmodifiableList(selectors);
    }

    public List<CSSDeclarationNode> getDeclarations() {
        return Collections.unmodifiableList(declarations);
    }

    // Add methods
    public void addSelector(CSSSelectorNode selector) {
        if (selector != null) {
            this.selectors.add(selector);
        }
    }

    public void addDeclaration(CSSDeclarationNode declaration) {
        if (declaration != null) {
            this.declarations.add(declaration);
        }
    }

    public void addAllSelectors(List<CSSSelectorNode> selectors) {
        if (selectors != null) {
            this.selectors.addAll(selectors);
        }
    }

    public void addAllDeclarations(List<CSSDeclarationNode> declarations) {
        if (declarations != null) {
            this.declarations.addAll(declarations);
        }
    }

    // Remove methods
    public boolean removeSelector(CSSSelectorNode selector) {
        return this.selectors.remove(selector);
    }

    public boolean removeDeclaration(CSSDeclarationNode declaration) {
        return this.declarations.remove(declaration);
    }

    // Utility methods
    public boolean hasSelectors() {
        return !this.selectors.isEmpty();
    }

    public boolean hasDeclarations() {
        return !this.declarations.isEmpty();
    }

    public void clearSelectors() {
        this.selectors.clear();
    }

    public void clearDeclarations() {
        this.declarations.clear();
    }

    public String getSelectorText() {
        return selectors.stream()
                .map(CSSSelectorNode::getSelector)
                .collect(Collectors.joining(", "));
    }

    public Optional<CSSDeclarationNode> getDeclaration(String property) {
        return declarations.stream()
                .filter(decl -> decl.getProperty().equalsIgnoreCase(property))
                .findFirst();
    }

    @Override
    public String toString() {
        return String.format("CSSRuleNode{selectors=%d, declarations=%d, line=%d, column=%d}",
                selectors.size(), declarations.size(), getLine(), getColumn());
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
