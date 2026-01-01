//package ast.template;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TemplateRootNode extends TemplateNode {
//    private List<TemplateNode> documents;
//
//    public TemplateRootNode(int line, int column) {
//        super("TemplateRoot", line, column);
//        this.documents = new ArrayList<>();
//    }
//
//    public List<TemplateNode> getDocuments() { return documents; }
//    public void addDocument(TemplateNode document) {
//        documents.add(document);
//    }
//}



package ast.template;

import ast.visitors.TemplateASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class TemplateRootNode extends TemplateNode {
    private List<TemplateNode> documents;

    public TemplateRootNode(int line, int column) {
        super(NodeKind.TEMPLATE_ROOT, line, column);
        this.documents = new ArrayList<>();
    }

    public List<TemplateNode> getDocuments() { return documents; }
    public void addDocument(TemplateNode document) {
        documents.add(document);
    }

    @Override
    public List<TemplateNode> getChildren() {
        return documents;
    }

    @Override
    public <T> T accept(TemplateASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
