import ast.template.css.*;
import ast.template.html.*;
import ast.template.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;
import ast.visitors.TemplateASTBuilder;
import gen.FlaskLexer;
import gen.FlaskTemplateParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import symbolTableJinja.JinjaSymbolTable;
import symbolTableJinja.JinjaSymbolTableBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ast.visitors.PrintASTVisitor.printNode;

public class TemplateASTTest {

    public static void main(String[] args) throws Exception {
        Path templatePath1 = Path.of("test/jinja/base.html");
        Path templatePath2 = Path.of("test/jinja/index.html");
        Path templatePath3 = Path.of("test/jinja/product_detail.html");
        Path templatePath4 = Path.of("test/jinja/add_product.html");

        // 1. إعداد Lexer
        CharStream charStream = CharStreams.fromPath(templatePath2);
        FlaskLexer lexer = new FlaskLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 2. إعداد Parser
        FlaskTemplateParser parser = new FlaskTemplateParser(tokens);

        // 3. إنشاء شجرة Parse
        FlaskTemplateParser.TemplateRootContext tree = (FlaskTemplateParser.TemplateRootContext) parser.template();

        // 4. زيارة الشجرة باستخدام TemplateASTBuilder
        TemplateASTBuilder visitor = new TemplateASTBuilder();
        TemplateNode rootNode = visitor.visitTemplateRoot(tree);

        // 2. بناء جدول الرموز
        JinjaSymbolTableBuilder builder = new JinjaSymbolTableBuilder("test.html");
        builder.visit(tree);

        // 3. التحليل
        JinjaSymbolTable symbolTable = builder.getSymbolTable();
        symbolTable.analyze();

        // 4. عرض النتائج
        symbolTable.printSymbolTable();
        symbolTable.printAnalysisReport();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        // 5. طباعة النتيجة
        printNode(rootNode, 0);
    }

}