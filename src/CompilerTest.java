import ast.visitors.PythonASTPrinter;
import gen.FlaskLexer;
import org.antlr.v4.runtime.*;
import gen.FlaskPythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import ast.python.visitors.PythonASTBuilderVisitor;
import ast.python.PythonNode;

import java.nio.file.Files;
import java.nio.file.Path;

public class CompilerTest {

    public static void main(String[] args) throws Exception {

        String source = Files.readString(Path.of("test/python"));

        CharStream input = CharStreams.fromString(source);
        FlaskLexer lexer = new FlaskLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        tokens.fill();
        System.out.println("=== TOKENS ===");
        for (Token t : tokens.getTokens()) {
            System.out.printf(
                    "%-15s %-10s (%d:%d)%n",
                    lexer.getVocabulary().getSymbolicName(t.getType()),
                    "'" + t.getText() + "'",
                    t.getLine(),
                    t.getCharPositionInLine()
            );
        }

        FlaskPythonParser parser = new FlaskPythonParser(tokens);
        ParseTree tree = parser.program();

        PythonASTBuilderVisitor builder = new PythonASTBuilderVisitor();
        PythonNode ast = builder.visit(tree);

        System.out.println("\n=== AST ===");
        PythonASTPrinter printer = new PythonASTPrinter();
        ast.accept(printer);
    }
}
