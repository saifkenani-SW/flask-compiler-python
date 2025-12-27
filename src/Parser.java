import gen.FlaskTemplateLexer;
//import gen.FlaskTemplateParser;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Parser {

    public static void main(String[] args) {

    /*    String filePath = "/home/saif/IdeaProjects/compiler/flask-compiler-python/test/flask";

        try {
            // 1. قراءة النص
            CharStream input = CharStreams.fromFileName(filePath);

            // 2. بناء الـ Lexer
            FlaskTemplateLexer lexer = new FlaskTemplateLexer(input);

            // 3. بناء token stream
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 4. بناء الـ Parser
            FlaskTemplateParser parser = new FlaskTemplateParser(tokens);

            // 5. إضافة Error Listener فقط (Diagnostic Mode)
            parser.removeErrorListeners();
            parser.addErrorListener(new DiagnosticErrorListener());

            // 6. (اختياري جداً) وقف البارسر عند أول خطأ
            // parser.setErrorHandler(new BailErrorStrategy());

            // 7. تشغيل القاعدة الرئيسية program
            ParseTree tree = parser.program();

            // 8. عرض شجرة التحليل رسوميّاً
            Trees.inspect(tree, parser);

            // 9. عرض الشجرة في صيغة LISP
            System.out.println(tree.toStringTree(parser));

        }
        catch (IOException e) {
            System.err.println("File Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Parser Error: ");
            e.printStackTrace();
        }*/
    }
}
