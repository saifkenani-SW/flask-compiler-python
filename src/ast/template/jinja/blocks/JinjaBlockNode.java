package ast.template.jinja.blocks;

import ast.template.NodeKind;
import ast.template.TemplateNode;
import ast.template.html.*;
import ast.template.jinja.JinjaNode;
import ast.template.jinja.expressions.JinjaExpressionNode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JinjaBlockNode extends JinjaNode {
    private List<TemplateNode> content;
    public abstract List<TemplateNode> getChildren();

    public JinjaBlockNode(NodeKind blockType, int line, int column) {
        super(blockType, line, column);
        this.content = new ArrayList<>();
    }

    // Getters
    public List<TemplateNode> getContent() {
        return Collections.unmodifiableList(content);
    }

    // Add methods
    public void addContent(TemplateNode content) {
        if (content != null) {
            this.content.add(content);
        }
    }

    public void addAllContent(List<TemplateNode> content) {
        if (content != null) {
            this.content.addAll(content);
        }
    }

    public void addContent(int index, TemplateNode content) {
        if (content != null && index >= 0 && index <= this.content.size()) {
            this.content.add(index, content);
        }
    }

    // Remove methods
    public TemplateNode removeContent(int index) {
        if (index >= 0 && index < content.size()) {
            return content.remove(index);
        }
        return null;
    }

    public boolean removeContent(TemplateNode content) {
        return this.content.remove(content);
    }

    // Utility methods
    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public int getContentSize() {
        return content.size();
    }

    public void clearContent() {
        this.content.clear();
    }

    public TemplateNode getContent(int index) {
        if (index >= 0 && index < content.size()) {
            return content.get(index);
        }
        return null;
    }

    public <T extends TemplateNode> List<T> getContentByType(Class<T> type) {
        return content.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    public List<JinjaBlockNode> getNestedBlocks() {
        return getContentByType(JinjaBlockNode.class);
    }

    public List<JinjaExpressionNode> getExpressions() {
        return getContentByType(JinjaExpressionNode.class);
    }

    public List<HTMLElementNode> getHtmlElements() {
        return getContentByType(HTMLElementNode.class);
    }

    @Override
    public String toString() {
        return String.format("%s{line=%d, column=%d, content=%d}",
                getClass().getSimpleName(), getLine(), getColumn(), content.size());
    }
}
