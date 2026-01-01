package ast.visitors;

import ast.*;
import ast.python.PythonNode;
import ast.python.declarations.DecoratorNode;
import ast.python.declarations.FunctionNode;
import ast.python.declarations.ImportNode;
import ast.python.declarations.ParameterNode;
import ast.python.expressions.*;
import ast.python.literals.*;
import ast.python.program.BlockNode;
import ast.python.program.ProgramNode;
import ast.python.statements.*;
import ast.python.visitors.PythonBaseASTVisitor;

import java.util.Stack;

/**
 * ASTPrinter
 * زائر لطباعة شجرة AST بشكل مقروء مع مسافات بادئة
 */
public class PythonASTPrinter extends PythonBaseASTVisitor<Void> {

    private int indentLevel = 0;
    private Stack<String> indents = new Stack<>();
    private StringBuilder output = new StringBuilder();

    public PythonASTPrinter() {
        indents.push("");
    }

    private String getIndent() {
        return indents.peek();
    }

    private void increaseIndent() {
        indentLevel++;
        indents.push(indents.peek() + "  ");
    }

    private void decreaseIndent() {
        if (indentLevel > 0) {
            indentLevel--;
            indents.pop();
        }
    }

    private void print(String message) {
        output.append(getIndent()).append(message).append("\n");
        System.out.println(getIndent() + message);
    }

    public String getOutput() {
        return output.toString();
    }

    // ==================================================
    // Program
    // ==================================================
    @Override
    public Void visit(ProgramNode node) {
        print("Program [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");
        increaseIndent();
        for (PythonNode stmt : node.getStatements()) {
            if (stmt != null) {
                stmt.accept(this);
            }
        }
        decreaseIndent();
        return null;
    }

    // ==================================================
    // Blocks
    // ==================================================
    @Override
    public Void visit(BlockNode node) {
        print("Block [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Statements: " + node.getStatements().size());
        increaseIndent();
        for (PythonNode stmt : node.getStatements()) {
            if (stmt != null) {
                stmt.accept(this);
            }
        }
        decreaseIndent();
        return null;
    }

    // ==================================================
    // Declarations
    // ==================================================
    @Override
    public Void visit(ImportNode node) {
        if (node.isFromImport()) {
            String imports;
            if (node.isImportAll()) {
                imports = "*";
            } else {
                imports = String.join(", ", node.getImports());
            }
            print("Import [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                  "] - from " + node.getModule() + " import " + imports);
        } else {
            if (node.getImports().isEmpty()) {
                print("Import [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                      "] - import " + node.getModule());
            } else {
                String imports = node.getModule() + ", " + String.join(", ", node.getImports());
                print("Import [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                      "] - import " + imports);
            }
        }
        return null;
    }

    @Override
    public Void visit(FunctionNode node) {
        print("Function [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Name: " + node.getName() +
              ", Parameters: " + node.getParameters().size());

        increaseIndent();

        // Decorators
        if (!node.getDecorators().isEmpty()) {
            for (DecoratorNode decorator : node.getDecorators()) {
                decorator.accept(this);
            }
        }

        // Parameters
        if (!node.getParameters().isEmpty()) {
            print("Parameters:");
            increaseIndent();
            for (ParameterNode param : node.getParameters()) {
                param.accept(this);
            }
            decreaseIndent();
        }

        // Body
        if (node.getBody() != null) {
            print("Body:");
            increaseIndent();
            node.getBody().accept(this);
            decreaseIndent();
        }

        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(ParameterNode node) {
        String info = "Parameter [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                     "] - Name: " + node.getName();
        if (node.getType() != null && !node.getType().isEmpty()) {
            info += ", Type: " + node.getType();
        }
        if (node.getDefaultValue() != null && !node.getDefaultValue().isEmpty()) {
            info += ", Default: " + node.getDefaultValue();
        }
        print(info);
        return null;
    }

    @Override
    public Void visit(DecoratorNode node) {
        String info = "Decorator [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                     "] - @" + node.getName();
        if (!node.getArguments().isEmpty()) {
            info += " (Arguments: " + node.getArguments().size() + ")";
        }
        print(info);

        if (!node.getArguments().isEmpty()) {
            increaseIndent();
            for (PythonNode arg : node.getArguments()) {
                arg.accept(this);
            }
            decreaseIndent();
        }
        return null;
    }

    // ==================================================
    // Statements
    // ==================================================
    @Override
    public Void visit(AssignmentNode node) {
        String info = "Assignment [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                     "] - Operator: " + node.getOperator();
        if (node.isAugmented()) {
            info += " (Augmented)";
        }
        print(info);

        increaseIndent();
        print("Target:");
        increaseIndent();
        node.getTarget().accept(this);
        decreaseIndent();

        print("Value:");
        increaseIndent();
        node.getValue().accept(this);
        decreaseIndent();
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(IfNode node) {
        String info = "If [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]";
        if (node.hasElif()) {
            info += " - with " + node.getElifBranches().size() + " elif";
        }
        if (node.hasElse()) {
            info += " - with else";
        }
        print(info);

        increaseIndent();

        // Condition
        print("Condition:");
        increaseIndent();
        node.getCondition().accept(this);
        decreaseIndent();

        // Then Block
        print("Then Block:");
        increaseIndent();
        node.getThenBlock().accept(this);
        decreaseIndent();

        // Elif Branches
        if (node.hasElif()) {
            print("Elif Branches:");
            increaseIndent();
            for (var elif : node.getElifBranches()) {
                print("Elif:");
                increaseIndent();
                print("Condition:");
                increaseIndent();
                elif.getCondition().accept(this);
                decreaseIndent();
                print("Block:");
                increaseIndent();
                elif.getBlock().accept(this);
                decreaseIndent();
                decreaseIndent();
            }
            decreaseIndent();
        }

        // Else Block
        if (node.hasElse() && node.getElseBlock() != null) {
            print("Else Block:");
            increaseIndent();
            node.getElseBlock().accept(this);
            decreaseIndent();
        }

        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(ForNode node) {
        String info = "For [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]";
        if (node.hasElse()) {
            info += " - with else";
        }
        print(info);

        increaseIndent();

        print("Variable:");
        increaseIndent();
        node.getVariable().accept(this);
        decreaseIndent();

        print("Iterable:");
        increaseIndent();
        node.getIterable().accept(this);
        decreaseIndent();

        print("Body:");
        increaseIndent();
        node.getBody().accept(this);
        decreaseIndent();

        if (node.hasElse() && node.getElseBlock() != null) {
            print("Else Block:");
            increaseIndent();
            node.getElseBlock().accept(this);
            decreaseIndent();
        }

        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(ReturnNode node) {
        if (node.hasValue()) {
            print("Return [Line: " + node.getLine() + ", Column: " + node.getColumn() + "] - with value");
            increaseIndent();
            node.getValue().accept(this);
            decreaseIndent();
        } else {
            print("Return [Line: " + node.getLine() + ", Column: " + node.getColumn() + "] - void");
        }
        return null;
    }

    @Override
    public Void visit(ExpressionStatementNode node) {
        print("ExpressionStatement [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");
        increaseIndent();
        node.getExpression().accept(this);
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(GlobalNode node) {
        print("Global [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Variables: " + String.join(", ", node.getVariables()));
        return null;
    }

    @Override
    public Void visit(WithNode node) {
        String info = "With [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]";
        if (node.hasAlias()) {
            info += " - as " + node.getAlias().getName();
        }
        print(info);

        increaseIndent();
        print("Expression:");
        increaseIndent();
        node.getExpression().accept(this);
        decreaseIndent();

        print("Body:");
        increaseIndent();
        node.getBody().accept(this);
        decreaseIndent();
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(PassNode node) {
        print("Pass [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");
        return null;
    }

    @Override
    public Void visit(BreakNode node) {
        print("Break [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");
        return null;
    }

    @Override
    public Void visit(ContinueNode node) {
        print("Continue [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");
        return null;
    }

    // ==================================================
    // Expressions
    // ==================================================
    @Override
    public Void visit(BinaryOpNode node) {
        String info = "BinaryOp [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                     "] - Operator: " + node.getOperator();

        if (node.isLogical()) info += " (Logical)";
        else if (node.isComparison()) info += " (Comparison)";
        else if (node.isArithmetic()) info += " (Arithmetic)";

        print(info);

        increaseIndent();
        print("Left:");
        increaseIndent();
        node.getLeft().accept(this);
        decreaseIndent();

        print("Right:");
        increaseIndent();
        node.getRight().accept(this);
        decreaseIndent();
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(UnaryOpNode node) {
        String info = "UnaryOp [Line: " + node.getLine() + ", Column: " + node.getColumn() +
                     "] - Operator: " + node.getOperator();
        if (node.isLogicalNot()) {
            info += " (Logical Not)";
        }
        print(info);

        increaseIndent();
        node.getOperand().accept(this);
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(CallNode node) {
        print("Call [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Function, Arguments: " + node.getArguments().size());

        increaseIndent();
        print("Function:");
        increaseIndent();
        node.getFunction().accept(this);
        decreaseIndent();

        if (!node.getArguments().isEmpty()) {
            print("Arguments:");
            increaseIndent();
            for (ExpressionNode arg : node.getArguments()) {
                arg.accept(this);
            }
            decreaseIndent();
        }
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(AttributeNode node) {
        print("Attribute [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - ." + node.getAttributeName());

        increaseIndent();
        print("Object:");
        increaseIndent();
        node.getObject().accept(this);
        decreaseIndent();
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(IndexNode node) {
        print("Index [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");

        increaseIndent();
        print("Collection:");
        increaseIndent();
        node.getCollection().accept(this);
        decreaseIndent();

        print("Index:");
        increaseIndent();
        node.getIndex().accept(this);
        decreaseIndent();
        decreaseIndent();
        return null;
    }

    @Override
    public Void visit(IdentifierNode node) {
        print("Identifier [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Name: " + node.getName());
        return null;
    }

    // ==================================================
    // Literals
    // ==================================================
    @Override
    public Void visit(IntLiteralNode node) {
        print("IntLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Value: " + node.getValue());
        return null;
    }

    @Override
    public Void visit(FloatLiteralNode node) {
        print("FloatLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Value: " + node.getValue());
        return null;
    }

    @Override
    public Void visit(StringLiteralNode node) {
        print("StringLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Value: \"" + node.getValue() + "\"");
        return null;
    }

    @Override
    public Void visit(BoolLiteralNode node) {
        print("BoolLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Value: " + node.getValue());
        return null;
    }

    @Override
    public Void visit(NoneLiteralNode node) {
        print("NoneLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() + "]");
        return null;
    }

    @Override
    public Void visit(ListLiteralNode node) {
        print("ListLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Elements: " + node.getElements().size());

        if (!node.getElements().isEmpty()) {
            increaseIndent();
            for (ExpressionNode elem : node.getElements()) {
                elem.accept(this);
            }
            decreaseIndent();
        }
        return null;
    }

    @Override
    public Void visit(DictLiteralNode node) {
        print("DictLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Entries: " + node.getEntries().size());

        if (!node.getEntries().isEmpty()) {
            increaseIndent();
            for (var entry : node.getEntries()) {
                print("Entry:");
                increaseIndent();
                print("Key:");
                increaseIndent();
                entry.getKey().accept(this);
                decreaseIndent();
                print("Value:");
                increaseIndent();
                entry.getValue().accept(this);
                decreaseIndent();
                decreaseIndent();
            }
            decreaseIndent();
        }
        return null;
    }

    @Override
    public Void visit(SetLiteralNode node) {
        print("SetLiteral [Line: " + node.getLine() + ", Column: " + node.getColumn() +
              "] - Elements: " + node.getElements().size());

        if (!node.getElements().isEmpty()) {
            increaseIndent();
            for (ExpressionNode elem : node.getElements()) {
                elem.accept(this);
            }
            decreaseIndent();
        }
        return null;
    }
}
