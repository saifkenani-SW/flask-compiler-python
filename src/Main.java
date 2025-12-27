import gen.FlaskTemplateLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import java.io.IOException;
import org.antlr.v4.runtime.Lexer;

// ... (تأكد من استيراد PythonBasic إذا كان في حزمة مختلفة)
// import com.yourpackage.PythonBasic;

public class Main {
    public static void main(String[] args) {


        String filePath1 = "/home/saif/IdeaProjects/compiler/flask-compiler-python/test/flask";
        String filePath2 = "/home/saif/IdeaProjects/compiler/flask-compiler-python/test/testJinja2";

        try {
            CharStream input = CharStreams.fromFileName(filePath2);

            FlaskTemplateLexer lexer = new FlaskTemplateLexer(input);
            System.out.println("--- Starting Lexer Test: Tokens for " + filePath2 + " ---");

            Token token;

            do {
                token = lexer.nextToken();

                if (token.getChannel() != Lexer.HIDDEN) {
                    // استخدام VOCABULARY للحصول على اسم الرمز
                    String tokenName = FlaskTemplateLexer.VOCABULARY.getSymbolicName(token.getType());

                    // تنسيق الطباعة
                    System.out.printf("Line %-3d Col %-2d -> Type: %-10s Value: \"%s\"\n",
                            token.getLine(),
                            token.getCharPositionInLine(),
                            tokenName != null ? tokenName : String.valueOf(token.getType()),
                            // نستبدل \n بـ \\n لتكون القراءة أسهل
                            token.getText().replace("\n", "\\n"));
                }
            } while (token.getType() != Token.EOF);

            System.out.println("--- Lexer Test Finished ---");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}