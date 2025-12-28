package ast.template.css;

public class CSSAttributeNode extends CSSNode {
    private String name;
    private String value;

    public CSSAttributeNode(int line, int column, String name) {
        super("Attribute", line, column);
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
