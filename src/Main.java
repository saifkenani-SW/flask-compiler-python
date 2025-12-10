import gen.MiniLangLexer;
import gen.MiniLangParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // لو ما أعطيت ملف، نستخدم مثال بسيط
        String source = """
                x = 10;
                y = x + 5 * 2;
                print y;
                print x + y;
                """;

        CharStream input = CharStreams.fromString(source);
        // لو تبغى من ملف:
        // CharStream input = CharStreams.fromFileName("test.mini");

        MiniLangLexer lexer = new MiniLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniLangParser parser = new MiniLangParser(tokens);

        ParseTree tree = parser.program(); // نبدأ من rule program

        System.out.println(tree.toStringTree(parser));
    }
}