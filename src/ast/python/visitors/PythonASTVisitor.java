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

public interface PythonASTVisitor<T> {
    // Program
    T visit(ProgramNode n);
    T visit(BlockNode n);

    // Declarations
    T visit(FunctionNode n);
    T visit(ParameterNode n);
    T visit(ImportNode n);
    T visit(DecoratorNode n);

    // Statements
    T visit(AssignmentNode n);
    T visit(IfNode n);
    T visit(ForNode n);
    T visit(ReturnNode n);
    T visit(ExpressionStatementNode n);
    T visit(GlobalNode n);
    T visit(WithNode n);
    T visit(PassNode n);
    T visit(BreakNode n);
    T visit(ContinueNode n);

    // Expressions
    T visit(BinaryOpNode n);
    T visit(UnaryOpNode n);
    T visit(CallNode n);
    T visit(AttributeNode n);
    T visit(IndexNode n);
    T visit(IdentifierNode n);

    // Literals
    T visit(IntLiteralNode n);
    T visit(FloatLiteralNode n);
    T visit(StringLiteralNode n);
    T visit(BoolLiteralNode n);
    T visit(NoneLiteralNode n);
    T visit(ListLiteralNode n);
    T visit(DictLiteralNode n);
    T visit(SetLiteralNode n);
}
