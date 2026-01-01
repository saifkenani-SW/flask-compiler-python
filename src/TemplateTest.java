import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import ast.visitors.TemplateASTBuilder;
import ast.visitors.TemplateASTPrinter;
import gen.FlaskLexer;
import gen.FlaskTemplateParser;
import org.antlr.v4.runtime.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TemplateTest {
    public static void main(String[] args) throws Exception {

        // 1. مسار الملف
        Path filePath = Paths.get("test/jinja_html_test");

        // 2. قراءة المحتوى
        String template = Files.readString(filePath);

        // 3. CharStream
        CharStream input = CharStreams.fromString(template);

        // 4. Lexer
        FlaskLexer lexer = new FlaskLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 5. Parser
        FlaskTemplateParser parser = new FlaskTemplateParser(tokens);
        FlaskTemplateParser.TemplateContext tree = parser.template();

        // 6. AST Root
        TemplateRootNode root = new TemplateRootNode(
                tree.getStart().getLine(),
                tree.getStart().getCharPositionInLine()
        );

        // 7. Builder
        TemplateASTBuilder builder = new TemplateASTBuilder();

        // 8. زيارة عناصر HTML
        FlaskTemplateParser.TemplateRootContext ctx =
                (FlaskTemplateParser.TemplateRootContext) tree;

        for (FlaskTemplateParser.HtmlContext htmlCtx : ctx.html()) {
            TemplateNode docNode = builder.visit(htmlCtx);
            root.addDocument(docNode);
        }

        // 9. طباعة AST
        TemplateASTPrinter printer = new TemplateASTPrinter();
        root.accept(printer);



    }
}
