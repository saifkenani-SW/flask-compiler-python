import ast.python.visitors.PythonASTPrinter;
import ast.python.visitors.PythonASTBuilderVisitor;
import ast.python.PythonNode;
import gen.FlaskLexer;
import gen.FlaskPythonParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import symbolTable.PythonSymbolTable;
import symbolTable.visitores.PythonSymbolTableBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

public class CompilerPythonTest {

    public static void main(String[] args) throws Exception {

        String source = Files.readString(Path.of("test/python/python_test.txt"));

        //   Lexer
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

        //  Parser
        FlaskPythonParser parser = new FlaskPythonParser(tokens);
        ParseTree tree = parser.program();

        //  AST Builder
        PythonASTBuilderVisitor astBuilder = new PythonASTBuilderVisitor();
        PythonNode ast = astBuilder.visit(tree);

        //  AST Print
        System.out.println("\n=== AST ===");
        PythonASTPrinter printer = new PythonASTPrinter();
        ast.accept(printer);

        //  Symbol Table Builder
        PythonSymbolTable symbolTable = new PythonSymbolTable();
        PythonSymbolTableBuilder symbolBuilder =
                new PythonSymbolTableBuilder(symbolTable);

        ast.accept(symbolBuilder);

        //  Print Symbol Table
        System.out.println("\n=== SYMBOL TABLE ===");
        symbolTable.print();
    }
}
