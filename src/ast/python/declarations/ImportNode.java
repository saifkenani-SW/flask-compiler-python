package ast.python.declarations;

import ast.python.PythonNode;
import ast.ASTVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * جملة الاستيراد
 */
public class ImportNode extends PythonNode {
    private String module;
    private List<String> imports;
    private boolean isFromImport;
    private boolean importAll; // import *

    public ImportNode(int line, int column) {
        super(line, column);
        this.imports = new ArrayList<>();
    }

    public ImportNode(int line, int column, String module) {
        this(line, column);
        this.module = module;
        this.isFromImport = false;
    }

    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public List<String> getImports() { return imports; }
    public void addImport(String importName) { imports.add(importName); }
    public void addImports(List<String> imports) { this.imports.addAll(imports); }

    public boolean isFromImport() { return isFromImport; }
    public void setFromImport(boolean fromImport) { isFromImport = fromImport; }

    public boolean isImportAll() { return importAll; }
    public void setImportAll(boolean importAll) { this.importAll = importAll; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
