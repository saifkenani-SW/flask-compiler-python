package ast.template.css;

import java.util.ArrayList;
import java.util.List;

public class CSSRuleNode extends CSSNode {
    private List<CSSSelectorNode> selectors;
    private List<CSSDeclarationNode> declarations;

    public CSSRuleNode(int line, int column) {
        super("Rule", line, column);
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
}
