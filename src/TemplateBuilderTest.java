import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import gen.FlaskLexer;
import gen.FlaskTemplateParser;
import ast.visitors.TemplateASTBuilder;
import ast.visitors.TemplateASTPrinter;
import ast.template.TemplateNode;

import java.nio.file.*;

public class TemplateBuilderTest {
    public static void main(String[] args) throws Exception {
        // 1️⃣ قراءة ملف القالب
        String content = Files.readString(Path.of("test/jinja_html_test"));

        // 2️⃣ إنشاء Lexer
        CharStream input = CharStreams.fromString(content);
        FlaskLexer lexer = new FlaskLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 3️⃣ إنشاء Parser
        FlaskTemplateParser parser = new FlaskTemplateParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                System.err.println("خطأ في السطر " + line + ":" + charPositionInLine + " -> " + msg);
            }
        });

        // 4️⃣ بناء Parse Tree
        ParseTree tree = parser.template();

        // 5️⃣ زيارة Parse Tree بواسطة Builder
        TemplateASTBuilder builder = new TemplateASTBuilder();
        TemplateNode astRoot = builder.visit(tree);

        // 6️⃣ طباعة AST للتحقق
        TemplateASTPrinter printer = new TemplateASTPrinter();
        astRoot.accept(printer);
    }
}
