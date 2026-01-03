import ast.visitors.PrintASTVisitor;
import gen.FlaskTemplateParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import gen.FlaskLexer;
import ast.visitors.TemplateASTBuilder;
import ast.template.TemplateNode;

import java.io.IOException;
import java.nio.file.*;

public class  TemplateBuilderTest {

        public static void main(String[] args) {
            // مثال 1: تحليل نص مباشر
            String templateCode = """
                    <!DOCTYPE html>
                    <html lang="ar">
                    <head>
                        <title>{{ page_title }}</title>
                    </head>
                    <body>
                        {% if user %}
                            <h1>مرحباً {{ user.name }}!</h1>
                        {% endif %}
                        <p>هذا اختبار للقالب</p>
                    </body>
                    </html>
                    """;

            TemplateNode ast = parseTemplate(templateCode);
            printAST(ast);

            // مثال 2: تحليل ملف
            if (args.length > 0) {
                try {
                    String fileContent = Files.readString(Path.of(args[0]));
                    TemplateNode fileAST = parseTemplate(fileContent);
                    printAST(fileAST);
                } catch (IOException e) {
                    System.err.println("خطأ في قراءة الملف: " + e.getMessage());
                }
            }
        }

        public static TemplateNode parseTemplate(String templateCode) {
            // 1. إنشاء CharStream من النص
            CharStream input = CharStreams.fromString(templateCode);

            // 2. إنشاء Lexer
            FlaskLexer lexer = new FlaskLexer(input);

            // 3. إنشاء token stream
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 4. إنشاء Parser
            FlaskTemplateParser parser = new FlaskTemplateParser(tokens);

            // 5. إنشاء visitor لبناء AST
            TemplateASTBuilder builder = new TemplateASTBuilder();

            // 6. تحليل القالب وإرجاع AST
            return builder.visit(parser.template());
        }

        public static void printAST(TemplateNode root) {
            PrintASTVisitor printer = new PrintASTVisitor();
            root.accept(printer);
            System.out.println(printer.getOutput());

    }
}