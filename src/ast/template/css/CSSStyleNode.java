package ast.template.css;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class CSSStyleNode extends CSSNode {
    private List<CSSAttributeNode> attributes;
    private List<CSSRuleNode> rules;

    public CSSStyleNode(int line, int column) {
        super(NodeKind.CSS_STYLE, line, column);
        this.attributes = new ArrayList<>();
        this.rules = new ArrayList<>();
    }

    public List<CSSAttributeNode> getAttributes() { return attributes; }
    public void addAttribute(CSSAttributeNode attribute) {
        attributes.add(attribute);
    }
    public void addAttributes(List<CSSAttributeNode> attributes) {
        this.attributes.addAll(attributes);
    }

    public List<CSSRuleNode> getRules() { return rules; }
    public void addRule(CSSRuleNode rule) {
        rules.add(rule);
    }
    public void addRules( List<CSSRuleNode> rule) {
        rules.addAll(rule);
    }

    @Override
    public List<TemplateNode> getChildren() {
        List<TemplateNode> children = new ArrayList<>();
        children.addAll(attributes); // CSSAttributeNode
        children.addAll(rules);      // CSSRuleNode
        return children;
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return null;
    }
//    @Override
//    public <T> T accept(TemplateASTVisitor<T> visitor) {
//        return visitor.visit(this);
//    }
}
