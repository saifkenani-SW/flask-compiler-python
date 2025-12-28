package ast.template.css;

import java.util.ArrayList;
import java.util.List;

public class CSSStyleNode extends CSSNode {
    private List<CSSAttributeNode> attributes;
    private List<CSSRuleNode> rules;

    public CSSStyleNode(int line, int column) {
        super("Style", line, column);
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
}
