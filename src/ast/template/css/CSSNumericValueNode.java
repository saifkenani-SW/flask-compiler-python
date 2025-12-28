package ast.template.css;

public class CSSNumericValueNode extends CSSValueNode {
    public CSSNumericValueNode(int line, int column, String value) {
        super("Numeric", line, column, value);
    }
}
