import gen.FlaskLexer;
import gen.FlaskTemplateParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import symbolTable.JinjaSymbolTable.JinjaSymbolTable;
import symbolTable.JinjaSymbolTable.JinjaSymbolTableBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class TestJinjaSymbolTable {
    public static void main(String[] args) throws IOException {

//        String template = Files.readString(
//                Path.of("test/add_product"),
//                StandardCharsets.UTF_8
//        );
//        String template = Files.readString(
//                Path.of("test/product_details"),
//                StandardCharsets.UTF_8
//        );
//        String template = Files.readString(
//                Path.of("test/index"),
//                StandardCharsets.UTF_8
//        );
        String template = Files.readString(
                Path.of("test/base"),
                StandardCharsets.UTF_8
        );

        try {
            // 1. Parse النص
            CharStream input = CharStreams.fromString(template);
            FlaskLexer lexer = new FlaskLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FlaskTemplateParser parser = new FlaskTemplateParser(tokens);

            // الحصول على شجرة الـ Parse
            FlaskTemplateParser.TemplateContext parseTree = parser.template();

            // 2. بناء جدول الرموز
            JinjaSymbolTableBuilder builder = new JinjaSymbolTableBuilder("test.html");
            builder.visit(parseTree);

            // 3. التحليل
            JinjaSymbolTable symbolTable = builder.getSymbolTable();
            symbolTable.analyze();

            // 4. عرض النتائج
            symbolTable.printSymbolTable();
            symbolTable.printAnalysisReport();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}