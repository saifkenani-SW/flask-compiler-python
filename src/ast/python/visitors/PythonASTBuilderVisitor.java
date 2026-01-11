package ast.python.visitors;

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
import gen.FlaskPythonParser;
import gen.FlaskPythonParserBaseVisitor;

public class PythonASTBuilderVisitor extends FlaskPythonParserBaseVisitor<PythonNode> {


    @Override
    public PythonNode visitProgramRoot(FlaskPythonParser.ProgramRootContext ctx) {
        ProgramNode program = new ProgramNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );
        for (var item : ctx.programItem()) {
            PythonNode node = visit(item);
            if (node != null) {
                program.addStatement(node);
            }
        }

        return program;
    }

    @Override
    public PythonNode visitImportItem(FlaskPythonParser.ImportItemContext ctx) {
        return visit(ctx.importStatement());
    }

    @Override
    public PythonNode visitDeclarationItem(FlaskPythonParser.DeclarationItemContext ctx) {
        return visit(ctx.declaration());
    }

    @Override
    public PythonNode visitFunctionItem(FlaskPythonParser.FunctionItemContext ctx) {
        return visit(ctx.functionDecl());
    }

    @Override
    public PythonNode visitStatementItem(FlaskPythonParser.StatementItemContext ctx) {
        return visit(ctx.statement());
    }

    @Override
    public PythonNode visitNewlineItem(FlaskPythonParser.NewlineItemContext ctx) {
        return null;
    }

    @Override
    public PythonNode visitImportModule(FlaskPythonParser.ImportModuleContext ctx) {
        ImportNode importNode = new ImportNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );
        importNode.setModule(ctx.dottedName(0).getText());
        importNode.setFromImport(false);

        for (int i = 1; i < ctx.dottedName().size(); i++) {
            IdentifierNode imp =
                    (IdentifierNode) visit(ctx.dottedName(i));
            importNode.addImport(imp);
        }

        return importNode;
    }


    @Override
    public PythonNode visitFromImport(FlaskPythonParser.FromImportContext ctx) {
        ImportNode importNode = new ImportNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        importNode.setModule(ctx.dottedName(0).getText());
        importNode.setFromImport(true);

        if (ctx.STAR() != null) {
            importNode.setImportAll(true);
        } else {
            for (int i = 1; i < ctx.dottedName().size(); i++) {
                IdentifierNode name =
                        (IdentifierNode) visit(ctx.dottedName(i));
                importNode.addImport(name);
            }
        }

        return importNode;
    }




    @Override
    public PythonNode visitFunctionDecleration(FlaskPythonParser.FunctionDeclerationContext ctx) {
        FunctionNode function = new FunctionNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            ctx.ID().getText()
        );

        for (var decoratorCtx : ctx.decorator()) {
            DecoratorNode decorator = (DecoratorNode) visit(decoratorCtx);
            if (decorator != null) {
                function.addDecorator(decorator);
            }
        }

        if (ctx.paramList() != null) {
            for (var paramCtx : ctx.paramList().ID()) {
                ParameterNode param = new ParameterNode(
                    paramCtx.getSymbol().getLine(),
                    paramCtx.getSymbol().getCharPositionInLine(),
                    paramCtx.getText()
                );
                function.addParameter(param);
            }
        }
        PythonNode body = visit(ctx.suite());
        if (body != null) {
            function.setBody(body);
        }

        return function;
    }

    @Override
    public PythonNode visitDecoratorRule(FlaskPythonParser.DecoratorRuleContext ctx) {
        DecoratorNode decorator = new DecoratorNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            ctx.dottedName().getText()
        );
        if (ctx.argumentList() != null) {
            for (var argCtx : ctx.argumentList().argument()) {
                PythonNode arg = visit(argCtx);
                if (arg != null) {
                    decorator.addArgument(arg);
                }
            }
        }

        return decorator;
    }


    @Override
    public PythonNode visitDeclarationStmt(FlaskPythonParser.DeclarationStmtContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public PythonNode visitAssignmentStmt(FlaskPythonParser.AssignmentStmtContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public PythonNode visitAssignmentRule(FlaskPythonParser.AssignmentRuleContext ctx) {
        PythonNode target = visit(ctx.leftHandSide());

        String operator = ctx.assignOp().getText();
        ExpressionNode value = (ExpressionNode) visit(ctx.expression());

        return new AssignmentNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            target,
            operator,
            value
        );
    }

    @Override
    public PythonNode visitSuite(FlaskPythonParser.SuiteContext ctx) {
        if (ctx.braceBlock() != null) {
            return visit(ctx.braceBlock());
        }
        return visit(ctx.indentBlock());
    }
    @Override
    public PythonNode visitBraceBlock(FlaskPythonParser.BraceBlockContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public PythonNode visitIndentBlock(FlaskPythonParser.IndentBlockContext ctx) {
        return visit(ctx.block());
    }



    @Override
    public PythonNode visitIfStmt(FlaskPythonParser.IfStmtContext ctx) {
        return visit(ctx.ifStatement());
    }

    @Override
    public PythonNode visitIfStatementRule(FlaskPythonParser.IfStatementRuleContext ctx) {

        ExpressionNode condition =
                (ExpressionNode) visit(ctx.expression(0));
        BlockNode thenBlock =
                (BlockNode) visit(ctx.suite(0));

        IfNode ifNode = new IfNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                condition,
                thenBlock
        );

        for (int i = 0; i < ctx.ELIF().size(); i++) {
            ExpressionNode elifCondition =
                    (ExpressionNode) visit(ctx.expression(i + 1));

            BlockNode elifBlock =
                    (BlockNode) visit(ctx.suite(i + 1));

            ifNode.addElifBranch(elifCondition, elifBlock);
        }

        if (ctx.ELSE() != null) {
            int elseIndex = ctx.suite().size() - 1;

            BlockNode elseBlock =
                    (BlockNode) visit(ctx.suite(elseIndex));

            ifNode.setElseBlock(elseBlock);
        }

        return ifNode;
    }


    @Override
    public PythonNode visitForStmt(FlaskPythonParser.ForStmtContext ctx) {
        return visit(ctx.forStatement());
    }

    @Override
    public PythonNode visitForStatementRule(FlaskPythonParser.ForStatementRuleContext ctx) {
        IdentifierNode variable = new IdentifierNode(
            ctx.ID().getSymbol().getLine(),
            ctx.ID().getSymbol().getCharPositionInLine(),
            ctx.ID().getText()
        );

        ExpressionNode iterable = (ExpressionNode) visit(ctx.expression());

        BlockNode body = (BlockNode) visit(ctx.suite());

        return new ForNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            variable,
            iterable,
            body
        );
    }

    @Override
    public PythonNode visitReturnStmt(FlaskPythonParser.ReturnStmtContext ctx) {
        return visit(ctx.returnStatement());
    }

    @Override
    public PythonNode visitReturnStatement(FlaskPythonParser.ReturnStatementContext ctx) {
        if (ctx.expression() != null) {
            ExpressionNode value = (ExpressionNode) visit(ctx.expression());
            return new ReturnNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                value
            );
        } else {
            return new ReturnNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
            );
        }
    }

    @Override
    public PythonNode visitExprStmt(FlaskPythonParser.ExprStmtContext ctx) {
        return visit(ctx.exprStatement());
    }

    @Override
    public PythonNode visitExprStatement(FlaskPythonParser.ExprStatementContext ctx) {
        ExpressionNode expression = (ExpressionNode) visit(ctx.expression());
        return new ExpressionStatementNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            expression
        );
    }

    @Override
    public PythonNode visitGlobalStmt(FlaskPythonParser.GlobalStmtContext ctx) {
        return visit(ctx.globalStatement());
    }

    @Override
    public PythonNode visitGlobalStatement(FlaskPythonParser.GlobalStatementContext ctx) {
        GlobalNode globalNode = new GlobalNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );

        for (var idCtx : ctx.ID()) {
            globalNode.addVariable(idCtx.getText());
        }

        return globalNode;
    }

    @Override
    public PythonNode visitWithStmt(FlaskPythonParser.WithStmtContext ctx) {
        return visit(ctx.withStatement());
    }

    @Override
    public PythonNode visitWithStatementRule(FlaskPythonParser.WithStatementRuleContext ctx) {
        ExpressionNode expression = (ExpressionNode) visit(ctx.expression());
        BlockNode body = (BlockNode) visit(ctx.suite());

        WithNode withNode = new WithNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            expression,
            body
        );
        if (ctx.AS() != null && ctx.ID() != null) {
            IdentifierNode alias = new IdentifierNode(
                ctx.ID().getSymbol().getLine(),
                ctx.ID().getSymbol().getCharPositionInLine(),
                ctx.ID().getText()
            );
            withNode.setAlias(alias);
        }

        return withNode;
    }

    @Override
    public PythonNode visitPassStmt(FlaskPythonParser.PassStmtContext ctx) {
        return visit(ctx.passStatement());
    }

    @Override
    public PythonNode visitPassStatement(FlaskPythonParser.PassStatementContext ctx) {
        return new PassNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );
    }

    @Override
    public PythonNode visitBreakStmt(FlaskPythonParser.BreakStmtContext ctx) {
        return visit(ctx.breakStatement());
    }

    @Override
    public PythonNode visitBreakStatement(FlaskPythonParser.BreakStatementContext ctx) {
        return new BreakNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );
    }

    @Override
    public PythonNode visitContinueStmt(FlaskPythonParser.ContinueStmtContext ctx) {
        return visit(ctx.continueStatement());
    }

    @Override
    public PythonNode visitContinueStatement(FlaskPythonParser.ContinueStatementContext ctx) {
        return new ContinueNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );
    }


    @Override
    public PythonNode visitExpressionRoot(FlaskPythonParser.ExpressionRootContext ctx) {
        return visit(ctx.logicalOrExpression());
    }

    @Override
    public PythonNode visitLogicalOrExpression(FlaskPythonParser.LogicalOrExpressionContext ctx) {
        if (ctx.OR() != null && ctx.OR().size() > 0) {
            // تجميع من اليسار إلى اليمين
            PythonNode current = visit(ctx.logicalAndExpression(0));

            for (int i = 0; i < ctx.OR().size(); i++) {
                PythonNode right = visit(ctx.logicalAndExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.OR(i).getSymbol().getLine(),
                    ctx.OR(i).getSymbol().getCharPositionInLine(),
                    "or",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else {
            // لا يوجد OR، انتقل للطبقة التالية
            return visit(ctx.logicalAndExpression(0));
        }
    }

    @Override
    public PythonNode visitLogicalAndExpression(FlaskPythonParser.LogicalAndExpressionContext ctx) {
        if (ctx.AND() != null && ctx.AND().size() > 0) {
            PythonNode current = visit(ctx.equalityExpression(0));

            for (int i = 0; i < ctx.AND().size(); i++) {
                PythonNode right = visit(ctx.equalityExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.AND(i).getSymbol().getLine(),
                    ctx.AND(i).getSymbol().getCharPositionInLine(),
                    "and",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else {
            return visit(ctx.equalityExpression(0));
        }
    }

    @Override
    public PythonNode visitEqualityExpression(FlaskPythonParser.EqualityExpressionContext ctx) {
        if (ctx.EQEQ() != null && ctx.EQEQ().size() > 0) {
            PythonNode current = visit(ctx.comparisonExpression(0));

            for (int i = 0; i < ctx.EQEQ().size(); i++) {
                PythonNode right = visit(ctx.comparisonExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.EQEQ(i).getSymbol().getLine(),
                    ctx.EQEQ(i).getSymbol().getCharPositionInLine(),
                    "==",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.NOTEQ() != null && ctx.NOTEQ().size() > 0) {
            PythonNode current = visit(ctx.comparisonExpression(0));

            for (int i = 0; i < ctx.NOTEQ().size(); i++) {
                PythonNode right = visit(ctx.comparisonExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.NOTEQ(i).getSymbol().getLine(),
                    ctx.NOTEQ(i).getSymbol().getCharPositionInLine(),
                    "!=",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else {
            return visit(ctx.comparisonExpression(0));
        }
    }

    @Override
    public PythonNode visitComparisonExpression(FlaskPythonParser.ComparisonExpressionContext ctx) {
        if (ctx.LT() != null && ctx.LT().size() > 0) {
            PythonNode current = visit(ctx.additiveExpression(0));

            for (int i = 0; i < ctx.LT().size(); i++) {
                PythonNode right = visit(ctx.additiveExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.LT(i).getSymbol().getLine(),
                    ctx.LT(i).getSymbol().getCharPositionInLine(),
                    "<",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.LTEQ() != null && ctx.LTEQ().size() > 0) {
            PythonNode current = visit(ctx.additiveExpression(0));

            for (int i = 0; i < ctx.LTEQ().size(); i++) {
                PythonNode right = visit(ctx.additiveExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.LTEQ(i).getSymbol().getLine(),
                    ctx.LTEQ(i).getSymbol().getCharPositionInLine(),
                    "<=",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.GT() != null && ctx.GT().size() > 0) {
            PythonNode current = visit(ctx.additiveExpression(0));

            for (int i = 0; i < ctx.GT().size(); i++) {
                PythonNode right = visit(ctx.additiveExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.GT(i).getSymbol().getLine(),
                    ctx.GT(i).getSymbol().getCharPositionInLine(),
                    ">",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.GTEQ() != null && ctx.GTEQ().size() > 0) {
            PythonNode current = visit(ctx.additiveExpression(0));

            for (int i = 0; i < ctx.GTEQ().size(); i++) {
                PythonNode right = visit(ctx.additiveExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.GTEQ(i).getSymbol().getLine(),
                    ctx.GTEQ(i).getSymbol().getCharPositionInLine(),
                    ">=",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.IN() != null && ctx.IN().size() > 0) {
            PythonNode current = visit(ctx.additiveExpression(0));

            for (int i = 0; i < ctx.IN().size(); i++) {
                PythonNode right = visit(ctx.additiveExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.IN(i).getSymbol().getLine(),
                    ctx.IN(i).getSymbol().getCharPositionInLine(),
                    "in",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.IS() != null && ctx.IS().size() > 0) {
            PythonNode current = visit(ctx.additiveExpression(0));

            for (int i = 0; i < ctx.IS().size(); i++) {
                PythonNode right = visit(ctx.additiveExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.IS(i).getSymbol().getLine(),
                    ctx.IS(i).getSymbol().getCharPositionInLine(),
                    "is",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else {
            return visit(ctx.additiveExpression(0));
        }
    }

    @Override
    public PythonNode visitAdditiveExpression(FlaskPythonParser.AdditiveExpressionContext ctx) {
        if (ctx.PLUS() != null && ctx.PLUS().size() > 0) {
            PythonNode current = visit(ctx.multiplicativeExpression(0));

            for (int i = 0; i < ctx.PLUS().size(); i++) {
                PythonNode right = visit(ctx.multiplicativeExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.PLUS(i).getSymbol().getLine(),
                    ctx.PLUS(i).getSymbol().getCharPositionInLine(),
                    "+",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.MINUS() != null && ctx.MINUS().size() > 0) {
            PythonNode current = visit(ctx.multiplicativeExpression(0));

            for (int i = 0; i < ctx.MINUS().size(); i++) {
                PythonNode right = visit(ctx.multiplicativeExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.MINUS(i).getSymbol().getLine(),
                    ctx.MINUS(i).getSymbol().getCharPositionInLine(),
                    "-",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else {
            return visit(ctx.multiplicativeExpression(0));
        }
    }

    @Override
    public PythonNode visitMultiplicativeExpression(FlaskPythonParser.MultiplicativeExpressionContext ctx) {
        if (ctx.STAR() != null && ctx.STAR().size() > 0) {
            PythonNode current = visit(ctx.unaryExpression(0));

            for (int i = 0; i < ctx.STAR().size(); i++) {
                PythonNode right = visit(ctx.unaryExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.STAR(i).getSymbol().getLine(),
                    ctx.STAR(i).getSymbol().getCharPositionInLine(),
                    "*",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.SLASH() != null && ctx.SLASH().size() > 0) {
            PythonNode current = visit(ctx.unaryExpression(0));

            for (int i = 0; i < ctx.SLASH().size(); i++) {
                PythonNode right = visit(ctx.unaryExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.SLASH(i).getSymbol().getLine(),
                    ctx.SLASH(i).getSymbol().getCharPositionInLine(),
                    "/",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else if (ctx.PERCENT() != null && ctx.PERCENT().size() > 0) {
            PythonNode current = visit(ctx.unaryExpression(0));

            for (int i = 0; i < ctx.PERCENT().size(); i++) {
                PythonNode right = visit(ctx.unaryExpression(i + 1));
                current = new BinaryOpNode(
                    ctx.PERCENT(i).getSymbol().getLine(),
                    ctx.PERCENT(i).getSymbol().getCharPositionInLine(),
                    "%",
                    (ExpressionNode) current,
                    (ExpressionNode) right
                );
            }

            return current;
        } else {
            return visit(ctx.unaryExpression(0));
        }
    }

    @Override
    public PythonNode visitUnaryOp(FlaskPythonParser.UnaryOpContext ctx) {
        String operator = "";
        if (ctx.PLUS() != null) operator = "+";
        else if (ctx.MINUS() != null) operator = "-";
        else if (ctx.NOT() != null) operator = "not";

        ExpressionNode operand = (ExpressionNode) visit(ctx.unaryExpression());

        return new UnaryOpNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            operator,
            operand
        );
    }

    @Override
    public PythonNode visitSimplePrimary(FlaskPythonParser.SimplePrimaryContext ctx) {
        return visit(ctx.primaryExpression());
    }

    @Override
    public PythonNode visitPrimaryExpression(FlaskPythonParser.PrimaryExpressionContext ctx) {
        PythonNode atom = visit(ctx.atom());

        for (var postfixCtx : ctx.postfix()) {
            if (postfixCtx instanceof FlaskPythonParser.IndexExprContext) {
                ExpressionNode index = (ExpressionNode) visit(
                    ((FlaskPythonParser.IndexExprContext) postfixCtx).expression()
                );
                atom = new IndexNode(
                    postfixCtx.start.getLine(),
                    postfixCtx.start.getCharPositionInLine(),
                    (ExpressionNode) atom,
                    index
                );
            } else if (postfixCtx instanceof FlaskPythonParser.AttrExprContext) {
                String attrName = ((FlaskPythonParser.AttrExprContext) postfixCtx).ID().getText();
                atom = new AttributeNode(
                    postfixCtx.start.getLine(),
                    postfixCtx.start.getCharPositionInLine(),
                    (ExpressionNode) atom,
                    attrName
                );
            } else if (postfixCtx instanceof FlaskPythonParser.CallExprContext) {
                CallNode call = new CallNode(
                    postfixCtx.start.getLine(),
                    postfixCtx.start.getCharPositionInLine(),
                    (ExpressionNode) atom
                );

                var callCtx = (FlaskPythonParser.CallExprContext) postfixCtx;
                if (callCtx.argumentList() != null) {
                    for (var argCtx : callCtx.argumentList().argument()) {
                        PythonNode arg = visit(argCtx);
                        if (arg != null) {
                            call.addArgument((ExpressionNode) arg);
                        }
                    }
                }
                atom = call;
            }
        }

        return atom;
    }


    @Override
    public PythonNode visitIdAtom(FlaskPythonParser.IdAtomContext ctx) {
        return new IdentifierNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            ctx.ID().getText()
        );
    }

    @Override
    public PythonNode visitStringAtom(FlaskPythonParser.StringAtomContext ctx) {
        String value = ctx.STRING().getText();
        value = value.substring(1, value.length() - 1);
        return new StringLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            value
        );
    }

    @Override
    public PythonNode visitIntAtom(FlaskPythonParser.IntAtomContext ctx) {
        long value = Long.parseLong(ctx.INT().getText());
        return new IntLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            value
        );
    }

    @Override
    public PythonNode visitFloatAtom(FlaskPythonParser.FloatAtomContext ctx) {
        double value = Double.parseDouble(ctx.FLOAT().getText());
        return new FloatLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            value
        );
    }

    @Override
    public PythonNode visitTrueAtom(FlaskPythonParser.TrueAtomContext ctx) {
        return new BoolLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            true
        );
    }

    @Override
    public PythonNode visitFalseAtom(FlaskPythonParser.FalseAtomContext ctx) {
        return new BoolLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine(),
            false
        );
    }

    @Override
    public PythonNode visitNoneAtom(FlaskPythonParser.NoneAtomContext ctx) {
        return new NoneLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );
    }

    @Override
    public PythonNode visitListAtom(FlaskPythonParser.ListAtomContext ctx) {
        return visit(ctx.listLiteral());
    }

    @Override
    public PythonNode visitListLiteral(FlaskPythonParser.ListLiteralContext ctx) {
        ListLiteralNode list = new ListLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );

        if (ctx.expression() != null) {
            for (var exprCtx : ctx.expression()) {
                ExpressionNode element = (ExpressionNode) visit(exprCtx);
                list.addElement(element);
            }
        }

        return list;
    }

    @Override
    public PythonNode visitDictAtom(FlaskPythonParser.DictAtomContext ctx) {
        return visit(ctx.dictLiteral());
    }

    @Override
    public PythonNode visitDictLiteral(FlaskPythonParser.DictLiteralContext ctx) {
        DictLiteralNode dict = new DictLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );

        if (ctx.dictEntry() != null) {
            for (var entryCtx : ctx.dictEntry()) {
                ExpressionNode key = (ExpressionNode) visit(entryCtx.expression(0));
                ExpressionNode value = (ExpressionNode) visit(entryCtx.expression(1));
                dict.addEntry(key, value);
            }
        }

        return dict;
    }

    @Override
    public PythonNode visitSetAtom(FlaskPythonParser.SetAtomContext ctx) {
        return visit(ctx.setLiteral());
    }

    @Override
    public PythonNode visitSetLiteral(FlaskPythonParser.SetLiteralContext ctx) {
        SetLiteralNode set = new SetLiteralNode(
            ctx.start.getLine(),
            ctx.start.getCharPositionInLine()
        );

        if (ctx.expression() != null) {
            for (var exprCtx : ctx.expression()) {
                ExpressionNode element = (ExpressionNode) visit(exprCtx);
                set.addElement(element);
            }
        }

        return set;
    }

    @Override
    public PythonNode visitParenAtom(FlaskPythonParser.ParenAtomContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public PythonNode visitKeywordArgument(FlaskPythonParser.KeywordArgumentContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public PythonNode visitPositionalArgument(FlaskPythonParser.PositionalArgumentContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public PythonNode visitLeftHandSide(FlaskPythonParser.LeftHandSideContext ctx) {
        return visit(ctx.primaryExpression());
    }

    @Override
    public PythonNode visitBlock(FlaskPythonParser.BlockContext ctx) {
        BlockNode block = new BlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        for (var stmtCtx : ctx.statement()) {
            PythonNode stmt = visit(stmtCtx);
            if (stmt != null) {
                block.addStatement(stmt);
            }
        }

        return block;
    }



        @Override
        public PythonNode visitDottedName(FlaskPythonParser.DottedNameContext ctx) {
            String name = ctx.getText();
            return new IdentifierNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                name
            );
        }

    @Override
    public PythonNode visitParamList(FlaskPythonParser.ParamListContext ctx) {
        return null;
    }

    @Override
    public PythonNode visitArgumentList(FlaskPythonParser.ArgumentListContext ctx) {
        //       visitCallExpr
        return null;
    }

    @Override
    public PythonNode visitAssignOp(FlaskPythonParser.AssignOpContext ctx) {
        return null;
    }

    @Override
    public PythonNode visitDictEntry(FlaskPythonParser.DictEntryContext ctx) {
        return null;
    }

    @Override
    public PythonNode visitIndexExpr(FlaskPythonParser.IndexExprContext ctx) {
        //       visitPrimaryExpression
        return null;
    }

    @Override
    public PythonNode visitAttrExpr(FlaskPythonParser.AttrExprContext ctx) {
        //      visitPrimaryExpression
        return null;
    }

    @Override
    public PythonNode visitCallExpr(FlaskPythonParser.CallExprContext ctx) {
        //       visitPrimaryExpression
        return null;
    }
}
