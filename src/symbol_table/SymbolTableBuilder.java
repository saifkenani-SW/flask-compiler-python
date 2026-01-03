package symbol_table;


import ast.template.html.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;
import gen.FlaskTemplateParser;
import gen.FlaskTemplateParserBaseVisitor;

import java.util.Stack;

public class SymbolTableBuilder extends FlaskTemplateParserBaseVisitor<SymbolTable> {
    private SymbolTable currentScope;
    private final Stack<SymbolTable> scopeStack = new Stack<>();

    // للتعقب
    private final Stack<String> blockStack = new Stack<>();
    private final Stack<String> forLoopStack = new Stack<>();

    public SymbolTableBuilder() {
        // إنشاء النطاق العالمي
        currentScope = new SymbolTable("global");
        scopeStack.push(currentScope);
    }

    public SymbolTable getGlobalScope() {
        return scopeStack.firstElement();
    }

    private void enterScope(String scopeName) {
        SymbolTable newScope = new SymbolTable(scopeName, currentScope);
        currentScope = newScope;
        scopeStack.push(currentScope);
    }

    private void exitScope() {
        if (scopeStack.size() > 1) {
            scopeStack.pop();
            currentScope = scopeStack.peek();
        }
    }

    @Override
    public SymbolTable visitTemplateRoot(FlaskTemplateParser.TemplateRootContext ctx) {
        // النطاق الرئيسي للقالب
        enterScope("template_root");

        // زيارة العناصر
        if (ctx.doctype() != null) {
            visit(ctx.doctype());
        }

        if (ctx.html() != null) {
            visit(ctx.html());
        }

        exitScope();
        return currentScope;
    }

    @Override
    public SymbolTable visitHtmlDocument(FlaskTemplateParser.HtmlDocumentContext ctx) {
        enterScope("html_document");

        // زيارة المحتوى
        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        return currentScope;
    }

    @Override
    public SymbolTable visitBlockStart(FlaskTemplateParser.BlockStartContext ctx) {
        String blockName = ctx.BLOCK_ID().getText();

        // تعريف الـ block
        BlockBlockNode blockNode = new BlockBlockNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                blockName
        );

        Symbol blockSymbol = new Symbol(
                blockName,
                Symbol.Type.BLOCK,
                blockNode,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        currentScope.define(blockSymbol);
        blockStack.push(blockName);

        // دخول نطاق الـ block
        enterScope("block_" + blockName);

        // زيارة محتوى الـ block
        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        blockStack.pop();

        return currentScope;
    }

    @Override
    public SymbolTable visitForStart(FlaskTemplateParser.ForStartContext ctx) {
        String loopVar = ctx.BLOCK_ID().getText();

        // تعريف متغير الحلقة
        VariableNode varNode = new VariableNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                loopVar
        );

        Symbol loopSymbol = new Symbol(
                loopVar,
                Symbol.Type.LOOP_VAR,
                varNode,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        currentScope.define(loopSymbol);
        forLoopStack.push(loopVar);

        // دخول نطاق الحلقة
        enterScope("for_loop_" + loopVar);

        // زيارة محتوى الحلقة
        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        forLoopStack.pop();

        return currentScope;
    }

    @Override
    public SymbolTable visitSetBlock(FlaskTemplateParser.SetBlockContext ctx) {
        String varName = ctx.BLOCK_ID().getText();

        // تعريف متغير
        VariableNode varNode = new VariableNode(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                varName
        );

        Symbol varSymbol = new Symbol(
                varName,
                Symbol.Type.VARIABLE,
                varNode,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        currentScope.define(varSymbol);

        return currentScope;
    }

    @Override
    public SymbolTable visitImportBlock(FlaskTemplateParser.ImportBlockContext ctx) {
        String templateName = ctx.BLOCK_STRING().getText();
        String alias = ctx.BLOCK_ID() != null ? ctx.BLOCK_ID().getText() : templateName;

        Symbol importSymbol = new Symbol(
                alias,
                Symbol.Type.IMPORT,
                null,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        importSymbol.setDataType("template");
        currentScope.define(importSymbol);

        return currentScope;
    }

    @Override
    public SymbolTable visitFromImportBlock(FlaskTemplateParser.FromImportBlockContext ctx) {
        String templateName = ctx.BLOCK_STRING().getText();

        for (var id : ctx.importList().BLOCK_ID()) {
            String importName = id.getText();

            Symbol importSymbol = new Symbol(
                    importName,
                    Symbol.Type.IMPORT,
                    null,
                    id.getSymbol().getLine(),
                    id.getSymbol().getCharPositionInLine()
            );

            importSymbol.setDataType("macro_or_variable");
            currentScope.define(importSymbol);
        }

        return currentScope;
    }

    @Override
    public SymbolTable visitWithStart(FlaskTemplateParser.WithStartContext ctx) {
        enterScope("with_block");

        // زيارة محتوى with
        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        return currentScope;
    }

    @Override
    public SymbolTable visitIfStart(FlaskTemplateParser.IfStartContext ctx) {
        enterScope("if_block");

        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        return currentScope;
    }

    @Override
    public SymbolTable visitElifBlock(FlaskTemplateParser.ElifBlockContext ctx) {
        enterScope("elif_block");

        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        return currentScope;
    }

    @Override
    public SymbolTable visitElseBlock(FlaskTemplateParser.ElseBlockContext ctx) {
        enterScope("else_block");

        if (ctx.templateContent() != null) {
            visit(ctx.templateContent());
        }

        exitScope();
        return currentScope;
    }

    @Override
    public SymbolTable visitIdentifierExpr(FlaskTemplateParser.IdentifierExprContext ctx) {
        String varName = ctx.EXPR_ID().toString();

        // التحقق إذا كان المتغير معرف
        Symbol existing = currentScope.lookup(varName);
        if (existing == null) {
            // يمكنك إضافة تحذير هنا
            System.out.println("Warning: Undefined variable '" + varName +
                    "' at line " + ctx.start.getLine());
        }

        return currentScope;
    }

    @Override
    public SymbolTable visitBlockIdentifier(FlaskTemplateParser.BlockIdentifierContext ctx) {
        String varName = ctx.BLOCK_ID().getText();

        Symbol existing = currentScope.lookup(varName);
        if (existing == null) {
            System.out.println("Warning: Undefined variable '" + varName +
                    "' at line " + ctx.start.getLine());
        }

        return currentScope;
    }

    @Override
    public SymbolTable visitCallExpr(FlaskTemplateParser.CallExprContext ctx) {
        String functionName = ctx.EXPR_ID().getText();

        // تعريف دوال Jinja2 المدمجة
        if (isBuiltInFunction(functionName)) {
            Symbol funcSymbol = new Symbol(
                    functionName,
                    Symbol.Type.FUNCTION,
                    null,
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()
            );

            funcSymbol.setDataType("builtin_function");
            currentScope.define(funcSymbol);
        }

        // زيارة الوسائط
        if (ctx.argumentList() != null) {
            visit(ctx.argumentList());
        }

        return currentScope;
    }

    @Override
    public SymbolTable visitFilterExpr(FlaskTemplateParser.FilterExprContext ctx) {
        String filterName = ctx.EXPR_ID().getText();

        // تعريف الفلاتر
        Symbol filterSymbol = new Symbol(
                filterName,
                Symbol.Type.FILTER,
                null,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        filterSymbol.setDataType("filter");
        currentScope.define(filterSymbol);

        return currentScope;
    }

    private boolean isBuiltInFunction(String name) {
        // قائمة بدوال Jinja2 المدمجة
        String[] builtins = {
                "range", "dict", "list", "cycler", "joiner", "namespace",
                "url_for", "get_flashed_messages", "config", "request",
                "session", "g"
        };

        for (String builtin : builtins) {
            if (builtin.equals(name)) {
                return true;
            }
        }
        return false;
    }
}