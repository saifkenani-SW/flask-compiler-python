package ast.template.css;

public class CSSStringValueNode extends CSSValueNode {
    public CSSStringValueNode(int line, int column, String value) {
        super("String", line, column, value);
    }
}
