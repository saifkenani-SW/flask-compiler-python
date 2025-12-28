package ast.template.css;

public class CSSKeywordValueNode extends CSSValueNode {
    public CSSKeywordValueNode(int line, int column, String value) {
        super("Keyword", line, column, value);
    }
}
