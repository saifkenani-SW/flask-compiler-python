package ast.template.css;

public class CSSColorValueNode extends CSSValueNode {
    public CSSColorValueNode(int line, int column, String value) {
        super("Color", line, column, value);
    }
}
