package symbol_table;

import ast.template.TemplateNode;
import ast.template.jinja.expressions.ExpressionNode;

public class Symbol {
    public enum Type {
        VARIABLE,
        BLOCK,
        FUNCTION,
        FILTER,
        IMPORT,
        MACRO,
        PARAMETER,
        LOOP_VAR
    }

    private final String name;
    private final Type type;
    private final TemplateNode node; // العقدة التي عرفت هذا الرمز
    private ExpressionNode value; // القيمة (للمتغيرات)
    private String dataType; // نوع البيانات
    private final int line;
    private final int column;

    public Symbol(String name, Type type, TemplateNode node, int line, int column) {
        this.name = name;
        this.type = type;
        this.node = node;
        this.line = line;
        this.column = column;
    }

    // Getters and Setters
    public String getName() { return name; }
    public Type getType() { return type; }
    public TemplateNode getNode() { return node; }
    public ExpressionNode getValue() { return value; }
    public void setValue(ExpressionNode value) { this.value = value; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
    public int getLine() { return line; }
    public int getColumn() { return column; }

    @Override
    public String toString() {
        return String.format("%s (%s) at [%d:%d]", name, type, line, column);
    }
}