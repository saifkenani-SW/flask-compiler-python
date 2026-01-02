package ast.python.visitors;

import ast.python.declarations.DecoratorNode;
import ast.python.declarations.FunctionNode;
import ast.python.declarations.ImportNode;
import ast.python.declarations.ParameterNode;
import ast.python.expressions.*;
import ast.python.literals.*;
import ast.python.program.BlockNode;
import ast.python.program.ProgramNode;
import ast.python.statements.*;

public class PythonASTPrinter extends PythonBaseASTVisitor<Void> {

    private int indent = 0;

    private void print(String text) {
        System.out.println("  ".repeat(indent) + text);
    }

    private void withIndent(Runnable r) {
        indent++;
        r.run();
        indent--;
    }

    // ================= Program =================
    @Override
    public Void visit(ProgramNode node) {
        print("Program");
        withIndent(() -> node.getStatements().forEach(s -> s.accept(this)));
        return null;
    }

    @Override
    public Void visit(BlockNode node) {
        print("Block");
        withIndent(() -> node.getStatements().forEach(s -> s.accept(this)));
        return null;
    }

    // ================= Declarations =================
    @Override
    public Void visit(ImportNode node) {
        if (node.isFromImport()) {
            print("ImportFrom " + node.getModule() +
                    (node.isImportAll() ? " *" : " " + node.getImports()));
        } else {
            print("Import " + node.getModule());
        }
        return null;
    }

    @Override
    public Void visit(FunctionNode node) {
        print("Function " + node.getName());
        withIndent(() -> {
            node.getDecorators().forEach(d -> d.accept(this));
            node.getParameters().forEach(p -> p.accept(this));
            node.getBody().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(ParameterNode node) {
        print("Parameter " + node.getName());
        return null;
    }

    @Override
    public Void visit(DecoratorNode node) {
        print("Decorator @" + node.getName());
        withIndent(() -> node.getArguments().forEach(a -> a.accept(this)));
        return null;
    }

    // ================= Statements =================
    @Override
    public Void visit(AssignmentNode node) {
        print("Assignment (" + node.getOperator() + ")");
        withIndent(() -> {
            print("Target:");
            withIndent(() -> node.getTarget().accept(this));
            print("Value:");
            withIndent(() -> node.getValue().accept(this));
        });
        return null;
    }

    @Override
    public Void visit(IfNode node) {
        print("If");
        withIndent(() -> {
            print("Condition:");
            withIndent(() -> node.getCondition().accept(this));
            print("Then:");
            withIndent(() -> node.getThenBlock().accept(this));

            node.getElifBranches().forEach(e -> {
                print("Elif:");
                withIndent(() -> {
                    e.getCondition().accept(this);
                    e.getBlock().accept(this);
                });
            });

            if (node.getElseBlock() != null) {
                print("Else:");
                withIndent(() -> node.getElseBlock().accept(this));
            }
        });
        return null;
    }

    @Override
    public Void visit(ForNode node) {
        print("For");
        withIndent(() -> {
            print("Variable:");
            withIndent(() -> node.getVariable().accept(this));
            print("Iterable:");
            withIndent(() -> node.getIterable().accept(this));
            print("Body:");
            withIndent(() -> node.getBody().accept(this));
        });
        return null;
    }

    @Override
    public Void visit(ReturnNode node) {
        print("Return");
        if (node.hasValue()) {
            withIndent(() -> node.getValue().accept(this));
        }
        return null;
    }

    @Override
    public Void visit(ExpressionStatementNode node) {
        print("ExpressionStatement");
        withIndent(() -> node.getExpression().accept(this));
        return null;
    }

    @Override
    public Void visit(GlobalNode node) {
        print("Global");
        indent++;
        for (String var : node.getVariables()) {
            print("Var " + var);
        }
        indent--;
        return null;
    }


    @Override
    public Void visit(WithNode node) {
        print("With");
        indent++;

        print("Expression:");
        indent++;
        node.getExpression().accept(this);
        indent--;

        if (node.getAlias() != null) {
            print("Alias:");
            indent++;
            node.getAlias().accept(this);
            indent--;
        }

        print("Body:");
        indent++;
        node.getBody().accept(this);
        indent--;

        indent--;
        return null;
    }


    @Override
    public Void visit(PassNode node) {
        print("Pass");
        return null;
    }

    @Override
    public Void visit(BreakNode node) {
        print("Break");
        return null;
    }

    @Override
    public Void visit(ContinueNode node) {
        print("Continue");
        return null;
    }

    // ================= Expressions =================
    @Override
    public Void visit(BinaryOpNode node) {
        print("BinaryOp " + node.getOperator());
        withIndent(() -> {
            node.getLeft().accept(this);
            node.getRight().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(UnaryOpNode node) {
        print("UnaryOp " + node.getOperator());
        withIndent(() -> node.getOperand().accept(this));
        return null;
    }

    @Override
    public Void visit(CallNode node) {
        print("Call");
        withIndent(() -> {
            node.getFunction().accept(this);
            node.getArguments().forEach(a -> a.accept(this));
        });
        return null;
    }

    @Override
    public Void visit(AttributeNode node) {
        print("Attribute ." + node.getAttributeName());
        withIndent(() -> node.getObject().accept(this));
        return null;
    }

    @Override
    public Void visit(IndexNode node) {
        print("Index");
        withIndent(() -> {
            node.getCollection().accept(this);
            node.getIndex().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(IdentifierNode node) {
        print("Identifier " + node.getName());
        return null;
    }

    // ================= Literals =================
    @Override
    public Void visit(IntLiteralNode node) {
        print("Int " + node.getValue());
        return null;
    }

    @Override
    public Void visit(FloatLiteralNode node) {
        print("Float " + node.getValue());
        return null;
    }

    @Override
    public Void visit(StringLiteralNode node) {
        print("String \"" + node.getValue() + "\"");
        return null;
    }

    @Override
    public Void visit(BoolLiteralNode node) {
        print("Bool " + node.getValue());
        return null;
    }

    @Override
    public Void visit(NoneLiteralNode node) {
        print("None");
        return null;
    }

    @Override
    public Void visit(ListLiteralNode node) {
        print("List");
        withIndent(() -> node.getElements().forEach(e -> e.accept(this)));
        return null;
    }

    @Override
    public Void visit(DictLiteralNode node) {
        print("Dict");
        withIndent(() ->
                node.getEntries().forEach(e -> {
                    print("Entry:");
                    withIndent(() -> {
                        e.getKey().accept(this);
                        e.getValue().accept(this);
                    });
                })
        );
        return null;
    }

    @Override
    public Void visit(SetLiteralNode node) {
        print("Set");
        withIndent(() -> node.getElements().forEach(e -> e.accept(this)));
        return null;
    }
}
