package ast.python.declarations;

import ast.python.PythonNode;
import ast.ASTVisitor;

/**
 * بارامتر دالة
 */
public class ParameterNode extends PythonNode {
    private String name;
    private String type;
    private String defaultValue;

    public ParameterNode(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public ParameterNode(int line, int column, String name, String type) {
        this(line, column, name);
        this.type = type;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }

    public boolean hasType() { return type != null && !type.isEmpty(); }
    public boolean hasDefaultValue() { return defaultValue != null && !defaultValue.isEmpty(); }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
