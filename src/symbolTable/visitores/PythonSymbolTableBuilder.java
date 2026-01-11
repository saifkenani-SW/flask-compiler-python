package symbolTable.visitores;

import ast.python.declarations.*;
import ast.python.expressions.*;
import ast.python.literals.*;
import ast.python.program.BlockNode;
import ast.python.program.ProgramNode;
import ast.python.statements.*;
import ast.python.visitors.PythonBaseASTVisitor;
import symbolTable.*;
import symbolTable.scopes.*;
import symbolTable.symbols.*;

import java.util.*;

public class PythonSymbolTableBuilder extends PythonBaseASTVisitor<Void> {

    private final PythonSymbolTable symbolTable;

    public PythonSymbolTableBuilder(PythonSymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public PythonSymbolTable getSymbolTable() {
        return symbolTable;
    }


    @Override
    public Void visit(ProgramNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(BlockNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(FunctionNode n) {
        Symbol funcSymbol = new Symbol(
                n.getName(),
                n.getLine(),
                n.getColumn(),
                SymbolKind.FUNCTION,
                SymbolType.FUNCTION_TYPE
        );
        symbolTable.defineSymbol(funcSymbol);
        symbolTable.enterFunctionScope();

        for (ParameterNode param : n.getParameters()) {
            Symbol paramSymbol = new Symbol(
                    param.getName(),
                    param.getLine(),
                    param.getColumn(),
                    SymbolKind.PARAMETER,
                    SymbolType.UNKNOWN
            );
            symbolTable.defineSymbol(paramSymbol);
        }

        if (n.getBody() != null) {
            n.getBody().accept(this);
        }

        symbolTable.exitScope();
        return null;
    }

    @Override
    public Void visit(ImportNode n) {

        Symbol importSymbol = new Symbol(
                n.getModule(),
                n.getLine(),
                n.getColumn(),
                SymbolKind.IMPORT,
                SymbolType.MODULE_TYPE
        );
        importSymbol.setAttribute("isImported", true);
        symbolTable.defineSymbol(importSymbol);

        return null;
    }

    //   Statements

    @Override
    public Void visit(AssignmentNode n) {
        if (n.getTarget() instanceof IdentifierNode id) {
            String varName = id.getName();

            Symbol existing = symbolTable.resolveLocal(varName);
            if (existing != null && existing.hasAttribute("isGlobal")) {
            } else if (existing == null) {
                SymbolType type = inferTypeFromExpression(n.getValue());
                Object value = extractValue(n.getValue());

                Symbol varSymbol = new Symbol(
                        varName, id.getLine(), id.getColumn(),
                        SymbolKind.VARIABLE, type, value
                );

                symbolTable.defineSymbol(varSymbol);
            }
        }

        if (n.getValue() != null) {
            n.getValue().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ForNode n) {
        symbolTable.enterForLoopScope();

        SymbolType loopVarType = SymbolType.UNKNOWN;

        if (n.getIterable() instanceof IdentifierNode id) {
            String iterableName = id.getName();
            Symbol iterableSymbol = symbolTable.resolveSymbol(iterableName);

            if (iterableSymbol != null) {
                if (iterableSymbol.getType() == SymbolType.LIST) {
                    loopVarType = SymbolType.DICT;
                } else if (iterableSymbol.getType() == SymbolType.SET) {
                    loopVarType = SymbolType.UNKNOWN;
                } else if (iterableSymbol.getType() == SymbolType.STRING) {
                    loopVarType = SymbolType.STRING;
                }
            }
        }

        Symbol loopSymbol = new Symbol(
                n.getVariable().getName(),
                n.getVariable().getLine(),
                n.getVariable().getColumn(),
                SymbolKind.VARIABLE,
                loopVarType
        );

        symbolTable.defineSymbol(loopSymbol);

        n.getIterable().accept(this);
        n.getBody().accept(this);

        symbolTable.exitScope();
        return null;
    }

    @Override
    public Void visit(IfNode n) {
        n.getCondition().accept(this);

        //  then
        symbolTable.enterIfScope();
        n.getThenBlock().accept(this);
        symbolTable.exitScope();

        //  else
        if (n.hasElse()) {
            symbolTable.enterElseScope();
            n.getElseBlock().accept(this);
            symbolTable.exitScope();
        }

        return null;
    }

    @Override
    public Void visit(ReturnNode n) {
        if (n.hasValue()) {
            n.getValue().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(GlobalNode n) {
        for (String varName : n.getVariables()) {
            Symbol existing = symbolTable.getGlobalScope().resolve(varName);

            if (existing == null) {
                Symbol globalSymbol = new Symbol(
                        varName,
                        n.getLine(),
                        n.getColumn(),
                        SymbolKind.VARIABLE,
                        SymbolType.UNKNOWN
                );
                globalSymbol.setAttribute("isGlobal", true);
                symbolTable.getGlobalScope().define(globalSymbol);
            } else {
                existing.setAttribute("isGlobal", true);
            }
        }
        return null;
    }

    //  Helper Methods

    private SymbolType inferTypeFromExpression(ExpressionNode expr) {
        if (expr == null) return SymbolType.UNKNOWN;

        if (expr instanceof IntLiteralNode) return SymbolType.INT;
        if (expr instanceof FloatLiteralNode) return SymbolType.FLOAT;
        if (expr instanceof StringLiteralNode) return SymbolType.STRING;
        if (expr instanceof BoolLiteralNode) return SymbolType.BOOL;
        if (expr instanceof NoneLiteralNode) return SymbolType.NONE;

        if (expr instanceof ListLiteralNode) return SymbolType.LIST;
        if (expr instanceof DictLiteralNode) return SymbolType.DICT;
        if (expr instanceof SetLiteralNode) return SymbolType.SET;

        if (expr instanceof BinaryOpNode bin) {
            SymbolType left = inferTypeFromExpression(bin.getLeft());
            SymbolType right = inferTypeFromExpression(bin.getRight());

            String op = bin.getOperator();
            if (op.equals("+") || op.equals("-") || op.equals("*")) {
                if (left == SymbolType.INT && right == SymbolType.INT) {
                    return SymbolType.INT;
                } else if (left == SymbolType.FLOAT || right == SymbolType.FLOAT) {
                    return SymbolType.FLOAT;
                }
            } else if (op.equals("/")) {
                return SymbolType.FLOAT;
            }
        }

        return SymbolType.UNKNOWN;
    }

    private Object extractValue(ExpressionNode expr) {
        if (expr == null) return null;

        if (expr instanceof IntLiteralNode) {
            return ((IntLiteralNode) expr).getValue();
        } else if (expr instanceof FloatLiteralNode) {
            return ((FloatLiteralNode) expr).getValue();
        } else if (expr instanceof StringLiteralNode) {
            return ((StringLiteralNode) expr).getValue();
        } else if (expr instanceof BoolLiteralNode) {
            return ((BoolLiteralNode) expr).getValue();
        } else if (expr instanceof NoneLiteralNode) {
            return null;
        }

        else if (expr instanceof ListLiteralNode list && list.getElements().isEmpty()) {
            return new ArrayList<>();
        }

        else if (expr instanceof DictLiteralNode dict && dict.getEntries().isEmpty()) {
            return new HashMap<>();
        }

        else if (expr instanceof SetLiteralNode set && set.getElements().isEmpty()) {
            return new HashSet<>();
        }

        else if (expr instanceof BinaryOpNode bin) {
            Object left = extractValue(bin.getLeft());
            Object right = extractValue(bin.getRight());

            if (left instanceof Integer l && right instanceof Integer r) {
                switch (bin.getOperator()) {
                    case "+":
                        return l + r;
                    case "-":
                        return l - r;
                    case "*":
                        return l * r;
                    case "/":
                        return (double) l / r;
                }
            }
        }

        return null;
    }

    // other

    @Override
    public Void visit(ParameterNode n) {
        return null;
    }

    @Override
    public Void visit(DecoratorNode n) {
        return null;
    }

    @Override
    public Void visit(ExpressionStatementNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(PassNode n) {
        return null;
    }

    @Override
    public Void visit(BreakNode n) {
        return null;
    }

    @Override
    public Void visit(ContinueNode n) {
        return null;
    }

    @Override
    public Void visit(WithNode n) {
        return null;
    }

    @Override
    public Void visit(IdentifierNode n) {
        return null;
    }

    @Override
    public Void visit(BinaryOpNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(UnaryOpNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(CallNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(AttributeNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(IndexNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(IntLiteralNode n) {
        return null;
    }

    @Override
    public Void visit(FloatLiteralNode n) {
        return null;
    }

    @Override
    public Void visit(StringLiteralNode n) {
        return null;
    }

    @Override
    public Void visit(BoolLiteralNode n) {
        return null;
    }

    @Override
    public Void visit(NoneLiteralNode n) {
        return null;
    }

    @Override
    public Void visit(ListLiteralNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(DictLiteralNode n) {
        visitChildren(n);
        return null;
    }

    @Override
    public Void visit(SetLiteralNode n) {
        visitChildren(n);
        return null;
    }
}