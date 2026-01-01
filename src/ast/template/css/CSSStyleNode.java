package ast.template.css;

import ast.template.NodeKind;

import java.util.ArrayList;
import java.util.List;
import ast.template.TemplateNode;
import ast.visitors.TemplateASTVisitor;

import java.util.*;
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

    public List<CSSRuleNode> getRules() { return rules; }
    public void addRule(CSSRuleNode rule) {
        rules.add(rule);
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
        return visitor.visit(this);
    }
}
