package ast.template.html;

public class DoctypeNode extends HTMLNode {
    private String doctype;

    public DoctypeNode(int line, int column, String doctype) {
        super("Doctype", line, column);
        this.doctype = doctype;
    }

    public String getDoctype() { return doctype; }
    public void setDoctype(String doctype) { this.doctype = doctype; }
}
