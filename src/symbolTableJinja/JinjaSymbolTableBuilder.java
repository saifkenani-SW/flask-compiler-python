package symbolTableJinja;

import gen.FlaskTemplateParser;
import gen.FlaskTemplateParserBaseVisitor;

public class JinjaSymbolTableBuilder extends FlaskTemplateParserBaseVisitor<Void> {
    private final JinjaSymbolTable symbolTable;

    public JinjaSymbolTableBuilder(String templateName) {
        this.symbolTable = new JinjaSymbolTable(templateName);
    }

    // ---------------------------
    // Blocks
    // ---------------------------
    @Override
    public Void visitBlockStart(FlaskTemplateParser.BlockStartContext ctx) {
        String blockName = ctx.BLOCK_ID().getText();

        // تعريف البلوك
        BlockSymbol blockSymbol = new BlockSymbol(
                blockName,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );
        symbolTable.defineSymbol(blockSymbol);

        // دخول نطاق البلوك
        symbolTable.enterScope("block:" + blockName, JinjaSymbolType.BLOCK);

        // زيارة محتوى البلوك
        super.visitBlockStart(ctx);

        // خروج من نطاق البلوك
        symbolTable.exitScope();

        return null;
    }

    // ---------------------------
    // Loops
    // ---------------------------
    @Override
    public Void visitForStart(FlaskTemplateParser.ForStartContext ctx) {
        String loopVar = ctx.BLOCK_ID().getText();

        // تعريف متغير الحلقة
        VariableSymbol loopSymbol = new VariableSymbol(
                loopVar,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                "any",
                true
        );
        symbolTable.defineSymbol(loopSymbol);

        // دخول نطاق الحلقة
        symbolTable.enterScope("for:" + loopVar, JinjaSymbolType.LOOP_VARIABLE);

        super.visitForStart(ctx);

        symbolTable.exitScope();
        return null;
    }

    // ---------------------------
    // Set variables
    // ---------------------------
    @Override
    public Void visitSetBlock(FlaskTemplateParser.SetBlockContext ctx) {
        String varName = ctx.BLOCK_ID().getText();

        VariableSymbol varSymbol = new VariableSymbol(
                varName,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                "any"
        );

        if (ctx.blockExpression() != null) {
            varSymbol.setValueType(inferType(ctx.blockExpression()));
        }

        symbolTable.defineSymbol(varSymbol);
        return null;
    }

    // ---------------------------
    // Macros
    // ---------------------------
    @Override
    public Void visitMacroBlock(FlaskTemplateParser.MacroBlockContext ctx) {
        String macroName = ctx.BLOCK_ID().getText();
        MacroSymbol macro = new MacroSymbol(
                macroName,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );

        // إضافة الباراميترز
        int position = 0;
        if (ctx.macroParameters() != null) {
            for (var param : ctx.macroParameters().BLOCK_ID()) {
                ParameterSymbol p = new ParameterSymbol(
                        param.getText(),
                        param.getSymbol().getLine(),
                        param.getSymbol().getCharPositionInLine(),
                        "any",
                        position++
                );
                macro.addParameter(p);
                symbolTable.defineSymbol(p); // تعريف الباراميتر في الـ scope
            }
        }

        symbolTable.defineSymbol(macro);

        // دخول نطاق الماكرو
        symbolTable.enterScope("macro:" + macroName, JinjaSymbolType.MACRO);

        super.visitMacroBlock(ctx);

        symbolTable.exitScope();
        return null;
    }

    // ---------------------------
    // Includes / Extends / Imports
    // ---------------------------
    @Override
    public Void visitExtendsBlock(FlaskTemplateParser.ExtendsBlockContext ctx) {
        String templateName = cleanString(ctx.BLOCK_STRING().getText());
        symbolTable.addExtendsTemplate(templateName);
        return null;
    }

    @Override
    public Void visitIncludeBlock(FlaskTemplateParser.IncludeBlockContext ctx) {
        String templateName = cleanString(ctx.BLOCK_STRING().getText());
        symbolTable.addIncludedTemplate(templateName);
        return null;
    }

    @Override
    public Void visitImportBlock(FlaskTemplateParser.ImportBlockContext ctx) {
        String templateName = cleanString(ctx.BLOCK_STRING().getText());
        ImportSymbol importSymbol = new ImportSymbol(
                templateName,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );
        if (ctx.BLOCK_ID() != null) importSymbol.setAlias(ctx.BLOCK_ID().getText());
        symbolTable.defineSymbol(importSymbol);
        return null;
    }

    @Override
    public Void visitFromImportBlock(FlaskTemplateParser.FromImportBlockContext ctx) {
        String templateName = cleanString(ctx.BLOCK_STRING().getText());
        ImportSymbol importSymbol = new ImportSymbol(
                templateName,
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine()
        );
        for (var id : ctx.importList().BLOCK_ID()) {
            importSymbol.addImportedName(id.getText());
        }
        symbolTable.defineSymbol(importSymbol);
        return null;
    }

    // ---------------------------
    // Identifiers, calls, filters
    // ---------------------------
    @Override
    public Void visitIdentifierExpr(FlaskTemplateParser.IdentifierExprContext ctx) {
        String varName = ctx.getText();
        symbolTable.recordSymbolUsage(varName, ctx.start.getLine());
        return null;
    }

    @Override
    public Void visitBlockIdentifier(FlaskTemplateParser.BlockIdentifierContext ctx) {
        String varName = ctx.BLOCK_ID().getText();
        symbolTable.recordSymbolUsage(varName, ctx.start.getLine());
        return null;
    }

    @Override
    public Void visitCallExpr(FlaskTemplateParser.CallExprContext ctx) {
        String funcName = ctx.EXPR_ID().getText();
        symbolTable.recordSymbolUsage(funcName, ctx.start.getLine());
        return super.visitCallExpr(ctx);
    }

    @Override
    public Void visitFilterExpr(FlaskTemplateParser.FilterExprContext ctx) {
        String filterName = ctx.EXPR_ID().getText();
        symbolTable.recordSymbolUsage(filterName, ctx.start.getLine());
        return super.visitFilterExpr(ctx);
    }

    // ---------------------------
    // Utilities
    // ---------------------------
    private String inferType(FlaskTemplateParser.BlockExpressionContext ctx) {
        // يمكن توسيع المنطق لاحقًا
        return "any";
    }

    private String cleanString(String str) {
        if (str.length() >= 2 &&
                (str.startsWith("\"") && str.endsWith("\"") ||
                        str.startsWith("'") && str.endsWith("'"))) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    public JinjaSymbolTable getSymbolTable() {
        return symbolTable;
    }
}
