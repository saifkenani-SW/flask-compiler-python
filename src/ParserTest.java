import gen.FlaskLexer;
import gen.FlaskPythonParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class ParserTest {

    public static void main(String[] args) throws Exception {

        String filePath = "test/testJinja2";
        String filePath2 = "test/python";

        try {
            CharStream input = CharStreams.fromFileName(filePath2);

            // 2. Lexer
            FlaskLexer lexer = new FlaskLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // (اختياري) طباعة التوكنز
            tokens.fill();
            for (Token t : tokens.getTokens()) {
                System.out.printf(
                        "Line %-3d Col %-3d -> %-15s \"%s\"%n",
                        t.getLine(),
                        t.getCharPositionInLine(),
                        FlaskLexer.VOCABULARY.getSymbolicName(t.getType()),
                        t.getText()
                );
            }

            // 3. Parser
            FlaskPythonParser parser = new FlaskPythonParser(tokens);

            // 4. Error handling (مهم)
            parser.removeErrorListeners();
            parser.addErrorListener(new DiagnosticErrorListener());

            // 5. Parse (غير اسم rule حسب grammar)
            ParseTree tree = parser.program();

            // 6. طباعة الشجرة
            System.out.println(tree.toStringTree(parser));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
