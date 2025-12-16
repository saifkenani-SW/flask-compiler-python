import gen.PythonLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class CheckLexer {
    public static void main(String[] args) {
        String filePath = "test/test10";

        try {
            // 1. قراءة الملف كاملاً
            StringBuilder fileContent = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int lineNum = 1;

            System.out.println("=== FILE CONTENT ===");
            while ((line = br.readLine()) != null) {
                System.out.printf("%3d: %s%n", lineNum, line);
                fileContent.append(line).append("\n");
                lineNum++;
            }
            br.close();

            System.out.println("\n=== LEXER OUTPUT ===");

            // 2. تحليل بالمحلل
            CharStream input = CharStreams.fromString(fileContent.toString());
            PythonLexer lexer = new PythonLexer(input);

            // 3. جمع Tokens مع تشخيص دقيق
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();

            System.out.println("Line Col  TokenType      Value");
            System.out.println("---- ---  ------------   -----------------");

            for (Token token : tokens.getTokens()) {
                if (token.getType() == Token.EOF) {
                    System.out.println("EOF");
                    break;
                }

                String tokenName = PythonLexer.VOCABULARY.getSymbolicName(token.getType());
                if (tokenName == null) {
                    tokenName = "HIDDEN_" + token.getType();
                }

                String text = token.getText();
                String displayText;

                if (text.contains("\n")) {
                    displayText = "'\\n'";
                } else if (text.contains("\t")) {
                    displayText = "'\\t'";
                } else if (text.contains("\r")) {
                    displayText = "'\\r'";
                } else {
                    displayText = "'" + text + "'";
                }

                System.out.printf("%4d %3d  %-12s  %s%n",
                        token.getLine(),
                        token.getCharPositionInLine() + 1,
                        tokenName,
                        displayText);

                // تحقق خاص من @ و .
                if (text.equals("@") || text.equals(".")) {
                    System.out.printf("     ⚡ Found %s at line %d, col %d%n",
                            text, token.getLine(), token.getCharPositionInLine() + 1);
                }
            }

            // 4. التحقق من وجود AT و DOT
            System.out.println("\n=== CHECKING FOR @ AND . ===");
            boolean foundAt = false;
            boolean foundDot = false;

            for (Token token : tokens.getTokens()) {
                String tokenName = PythonLexer.VOCABULARY.getSymbolicName(token.getType());
                if ("AT".equals(tokenName)) {
                    System.out.println("✅ Found AT token: " + token.getText());
                    foundAt = true;
                }
                if ("DOT".equals(tokenName)) {
                    System.out.println("✅ Found DOT token: " + token.getText());
                    foundDot = true;
                }
            }

            if (!foundAt) System.out.println("❌ No AT token found!");
            if (!foundDot) System.out.println("❌ No DOT token found!");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}