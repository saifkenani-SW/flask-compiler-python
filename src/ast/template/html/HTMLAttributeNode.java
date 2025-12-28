package ast.template.html;

import ast.template.TemplateNode;

import java.util.ArrayList;
import java.util.List;

public class HTMLAttributeNode extends HTMLNode {
    private String name;
    private String value;
    private boolean isBoolean;
    private List<TemplateNode> valueContent;

    public HTMLAttributeNode(int line, int column, String name) {
        super("Attribute", line, column);
        this.name = name;
        this.valueContent = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public boolean isBoolean() { return isBoolean; }
    public void setBoolean(boolean isBoolean) { this.isBoolean = isBoolean; }

    public List<TemplateNode> getValueContent() { return valueContent; }
    public void addValueContent(TemplateNode content) {
        valueContent.add(content);
    }
}
