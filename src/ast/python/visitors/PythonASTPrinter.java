package ast.python.visitors;

import ast.IndentedAction;
import ast.python.declarations.*;
import ast.python.expressions.*;
import ast.python.literals.*;
import ast.python.program.BlockNode;
import ast.python.program.ProgramNode;
import ast.python.statements.*;

public class PythonASTPrinter extends PythonBaseASTVisitor<Void> {

    private int indent = 0;

    private void print(String text, int line) {
        System.out.println("  ".repeat(indent) + text + " [Line: " + line + "]");
    }

    private void withIndent(IndentedAction action) {
        indent++;
        try {
            action.execute();
        } finally {
            indent--;
        }
    }

    @Override
    public Void visit(ProgramNode node) {
        print("Program", node.getLine());
        withIndent(() -> node.getStatements().forEach(s -> s.accept(this)));
        return null;
    }

    @Override
    public Void visit(BlockNode node) {
        print("Block", node.getLine());
        withIndent(() -> node.getStatements().forEach(s -> s.accept(this)));
        return null;
    }

    @Override
    public Void visit(ImportNode node) {

        if (node.isFromImport()) {

            if (node.isImportAll()) {
                print(
                        "ImportFrom " + node.getModule() + " import *",
                        node.getLine()
                );
            } else {
                String imports = node.getImports().stream()
                        .map(x->x.getName())
                        .collect(java.util.stream.Collectors.joining(", "));

                print(
                        "ImportFrom " + node.getModule() + " import " + imports,
                        node.getLine()
                );
            }

        } else {
            // import module[, module2...]
            if (node.getImports().isEmpty()) {
                print(
                        "Import " + node.getModule(),
                        node.getLine()
                );
            } else {
                String imports = node.getImports().stream()
                        .map(x -> x.getName()
                        )
                        .collect(java.util.stream.Collectors.joining(", "));

                print(
                        "Import " + node.getModule() + ", " + imports,
                        node.getLine()
                );
            }
        }

        return null;
    }


    @Override
    public Void visit(FunctionNode node) {
        print("Function " + node.getName(), node.getLine());
        withIndent(() -> {
            node.getDecorators().forEach(d -> d.accept(this));
            node.getParameters().forEach(p -> p.accept(this));
            node.getBody().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(ParameterNode node) {
        print("Parameter " + node.getName(), node.getLine());
        return null;
    }

    @Override
    public Void visit(DecoratorNode node) {
        print("Decorator @" + node.getName(), node.getLine());
        withIndent(() -> node.getArguments().forEach(a -> a.accept(this)));
        return null;
    }

    @Override
    public Void visit(AssignmentNode node) {
        print("Assignment (" + node.getOperator() + ")", node.getLine());
        withIndent(() -> {
            print("Target:", node.getLine());
            withIndent(() -> node.getTarget().accept(this));
            print("Value:", node.getLine());
            withIndent(() -> node.getValue().accept(this));
        });
        return null;
    }

    @Override
    public Void visit(IfNode node) {
        print("If", node.getLine());
        withIndent(() -> {
            print("Condition:", node.getLine());
            withIndent(() -> node.getCondition().accept(this));
            print("Then:", node.getLine());
            withIndent(() -> node.getThenBlock().accept(this));

            node.getElifBranches().forEach(e -> {
                print("Elif", e.getCondition().getLine());
                withIndent(() -> {
                    print("Condition:", e.getCondition().getLine());
                    withIndent(() -> e.getCondition().accept(this));

                    print("Block:", e.getBlock().getLine());
                    withIndent(() -> e.getBlock().accept(this));
                });
            });


            if (node.getElseBlock() != null) {
                print("Else", node.getElseBlock().getLine());
                withIndent(() -> node.getElseBlock().accept(this));
            }
        });
        return null;
    }

    @Override
    public Void visit(ForNode node) {
        print("For", node.getLine());
        withIndent(() -> {
            print("Variable:", node.getLine());
            withIndent(() -> node.getVariable().accept(this));
            print("Iterable:", node.getLine());
            withIndent(() -> node.getIterable().accept(this));
            print("Body:", node.getLine());
            withIndent(() -> node.getBody().accept(this));
        });
        return null;
    }

    @Override
    public Void visit(ReturnNode node) {
        print("Return", node.getLine());
        if (node.hasValue()) {
            withIndent(() -> node.getValue().accept(this));
        }
        return null;
    }

    @Override
    public Void visit(ExpressionStatementNode node) {
        print("ExpressionStatement", node.getLine());
        withIndent(() -> node.getExpression().accept(this));
        return null;
    }

    @Override
    public Void visit(GlobalNode node) {
        print("Global", node.getLine());
        indent++;
        node.getVariables().forEach(v -> print("Var " + v, node.getLine()));
        indent--;
        return null;
    }

    @Override
    public Void visit(WithNode node) {
        print("With", node.getLine());
        withIndent(() -> {
            print("Expression:", node.getLine());
            withIndent(() -> node.getExpression().accept(this));
            if (node.getAlias() != null) {
                print("Alias:", node.getAlias().getLine());
                withIndent(() -> node.getAlias().accept(this));
            }
            print("Body:", node.getLine());
            withIndent(() -> node.getBody().accept(this));
        });
        return null;
    }

    @Override
    public Void visit(PassNode node) {
        print("Pass", node.getLine());
        return null;
    }

    @Override
    public Void visit(BreakNode node) {
        print("Break", node.getLine());
        return null;
    }

    @Override
    public Void visit(ContinueNode node) {
        print("Continue", node.getLine());
        return null;
    }

    //      Expressions
    @Override
    public Void visit(BinaryOpNode node) {
        print("BinaryOp " + node.getOperator(), node.getLine());
        withIndent(() -> {
            node.getLeft().accept(this);
            node.getRight().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(UnaryOpNode node) {
        print("UnaryOp " + node.getOperator(), node.getLine());
        withIndent(() -> node.getOperand().accept(this));
        return null;
    }

    @Override
    public Void visit(CallNode node) {
        print("Call", node.getLine());
        withIndent(() -> {
            node.getFunction().accept(this);
            node.getArguments().forEach(a -> a.accept(this));
        });
        return null;
    }

    @Override
    public Void visit(AttributeNode node) {
        print("Attribute ." + node.getAttributeName(), node.getLine());
        withIndent(() -> node.getObject().accept(this));
        return null;
    }

    @Override
    public Void visit(IndexNode node) {
        print("Index", node.getLine());
        withIndent(() -> {
            node.getCollection().accept(this);
            node.getIndex().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(IdentifierNode node) {
        print("Identifier " + node.getName(), node.getLine());
        return null;
    }

    @Override
    public Void visit(IntLiteralNode node) {
        print("Int " + node.getValue(), node.getLine());
        return null;
    }

    @Override
    public Void visit(FloatLiteralNode node) {
        print("Float " + node.getValue(), node.getLine());
        return null;
    }

    @Override
    public Void visit(StringLiteralNode node) {
        print("String \"" + node.getValue() + "\"", node.getLine());
        return null;
    }

    @Override
    public Void visit(BoolLiteralNode node) {
        print("Bool " + node.getValue(), node.getLine());
        return null;
    }

    @Override
    public Void visit(NoneLiteralNode node) {
        print("None", node.getLine());
        return null;
    }

    @Override
    public Void visit(ListLiteralNode node) {
        print("List", node.getLine());
        withIndent(() -> node.getElements().forEach(e -> e.accept(this)));
        return null;
    }

    @Override
    public Void visit(DictLiteralNode node) {
        print("Dict", node.getLine());
        withIndent(() ->
                node.getEntries().forEach(e -> {
                    print("Entry", e.getKey().getLine());
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
        print("Set", node.getLine());
        withIndent(() -> node.getElements().forEach(e -> e.accept(this)));
        return null;
    }
}
