package ast.python.program;

import ast.python.PythonNode;
import ast.ASTVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * العقدة الجذرية للبرنامج
 */
public class ProgramNode extends PythonNode {
    private String fileName;
    private List<PythonNode> statements;

    public ProgramNode(int line, int column) {
        super(line, column);
        this.statements = new ArrayList<>();
    }

    public ProgramNode(int line, int column, String fileName) {
        this(line, column);
        this.fileName = fileName;
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public List<PythonNode> getStatements() { return statements; }
    public void addStatement(PythonNode statement) {
        statements.add(statement);
        addChild(statement);
    }
    public void addStatements(List<PythonNode> statements) {
        for (PythonNode stmt : statements) {
            addStatement(stmt);
        }
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
