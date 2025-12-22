package gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntegerStack;
import java.util.LinkedList;

public abstract class FlaskTemplateLexerBase extends Lexer {

    private IntegerStack indentations = new IntegerStack();
    private LinkedList<Token> pendingTokens = new LinkedList<>();
    private Token lastConsumedToken = null;
    private boolean calculatedNextIndent = false;
    private int nextIndentation = 0;

    // ⭐️ مستويات الأقواس المختلفة
    private int parenLevel = 0;      // ()
    private int bracketLevel = 0;    // []
    private int braceLevel = 0;      // {}

    // ⭐️ لحفظ حالة نحتاجها لـ handleNewline
    private boolean skipNextIndentCalculation = false;

    public FlaskTemplateLexerBase(CharStream input) {
        super(input);
        indentations.push(0);
    }

    /**
     * تحقق إذا كنا داخل أي نوع من الأقواس
     */
    private boolean isInsideBrackets() {
        return (parenLevel + bracketLevel + braceLevel) > 0;
    }

    /**
     * إضافة مستوى للأقواس
     */
    private void enterBracket() {
        parenLevel++;
    }

    private void enterSquareBracket() {
        bracketLevel++;
    }

    private void enterBrace() {
        braceLevel++;
    }

    /**
     * تقليل مستوى للأقواس
     */
    private void exitBracket() {
        if (parenLevel > 0) parenLevel--;
    }

    private void exitSquareBracket() {
        if (bracketLevel > 0) bracketLevel--;
    }

    private void exitBrace() {
        if (braceLevel > 0) braceLevel--;
    }

    @Override
    public Token nextToken() {
        // 1. إرسال الرموز المعلقة أولاً
        if (!pendingTokens.isEmpty()) {
            Token token = pendingTokens.poll();
            if (token.getType() != FlaskTemplateLexer.INDENT &&
                    token.getType() != FlaskTemplateLexer.DEDENT) {
                lastConsumedToken = token;
            }
            return token;
        }

        // 2. الحصول على الرمز التالي من ANTLR
        Token token = super.nextToken();

        // 3. تحديث مستويات الأقواس
        switch (token.getType()) {
            case FlaskTemplateLexer.LPAREN:
                enterBracket();
                break;

            case FlaskTemplateLexer.RPAREN:
                exitBracket();
                break;

            case FlaskTemplateLexer.LBRACK:
                enterSquareBracket();
                break;

            case FlaskTemplateLexer.RBRACK:
                exitSquareBracket();
                break;

            case FlaskTemplateLexer.LBRACE:
                enterBrace();
                break;

            case FlaskTemplateLexer.RBRACE:
                exitBrace();
                break;

            case FlaskTemplateLexer.NEWLINE:
                return handleNewline(token);

            case Token.EOF:
                return handleEof(token);
        }

        lastConsumedToken = token;
        return token;
    }

    /**
     * معالجة سطر جديد
     */
    private Token handleNewline(Token newlineToken) {
        // ⭐️ إذا كنا داخل أقواس، نتجاهل NEWLINE بالكامل ولا نحسب indentation
        if (isInsideBrackets()) {
            // نتجاهل هذا السطر ونستمر في القراءة للحصول على التوكن التالي
            skipNextIndentCalculation = true;
            calculatedNextIndent = false;

            // الحصول على التوكن التالي مباشرة (تخطي NEWLINE هذا)
            return nextToken();
        }

        // ⭐️ خارج الأقواس: معالجة INDENT/DEDENT كالمعتاد
        pendingTokens.add(newlineToken);

        // حساب المسافة البادئة للسطر التالي
        nextIndentation = peekIndentation();
        int currentIndent = indentations.peek();

        // INDENT: زيادة في المسافة البادئة
        if (nextIndentation > currentIndent) {
            indentations.push(nextIndentation);
            pendingTokens.add(createIndentToken(newlineToken));
        }
        // DEDENT: انخفاض في المسافة البادئة
        else if (nextIndentation < currentIndent) {
            handleDedents(newlineToken);
        }
        // نفس المستوى: لا شيء

        calculatedNextIndent = false;
        return pendingTokens.poll();
    }

    /**
     * حساب المسافة البادئة للسطر التالي (بدون استهلاك input)
     */
    private int peekIndentation() {
        if (calculatedNextIndent) {
            return nextIndentation;
        }

        // ⭐️ إذا كنا داخل أقواس أو طلبنا تخطي الحساب
        if (isInsideBrackets() || skipNextIndentCalculation) {
            skipNextIndentCalculation = false;
            calculatedNextIndent = true;
            nextIndentation = 0;
            return 0;
        }

        // حفظ الموضع الحالي للعودة إليه لاحقاً
        int mark = _input.mark();
        int indent = 0;

        while (true) {
            int c = _input.LA(1);  // الحصول على الحرف التالي دون استهلاكه

            // حساب المسافات
            if (c == ' ') {
                indent++;
                _input.consume();
            }
            // حساب Tabs (كل Tab = 8 مسافات في Python)
            else if (c == '\t') {
                indent += 8;
                _input.consume();
            }
            // تخطي التعليقات
            else if (c == '#') {
                // استهلاك كل الأحرف حتى نهاية السطر
                while (_input.LA(1) != '\n' &&
                        _input.LA(1) != '\r' &&
                        _input.LA(1) != CharStream.EOF) {
                    _input.consume();
                }
                // ⭐️ مهم: continue بعد التعليق للتحقق من الأحرف التالية
                continue;
            }
            // سطر فارغ: ابدأ من جديد
            else if (c == '\n' || c == '\r') {
                // استهلاك أحرف السطر الجديد
                consumeNewline();
                indent = 0;
                // استمر في الحلقة للتحقق من المسافة البادئة للسطر التالي
                continue;
            }
            // أي حرف آخر (بداية كود فعلي) - توقف عن الحساب
            else {
                break;
            }
        }

        // العودة إلى الموضع الأصلي (لأننا كنا نقرأ فقط بدون استهلاك)
        _input.release(mark);

        calculatedNextIndent = true;
        nextIndentation = indent;
        return indent;
    }

    /**
     * استهلاك أحرف السطر الجديد
     */
    private void consumeNewline() {
        int c = _input.LA(1);
        if (c == '\r') {
            _input.consume();
            if (_input.LA(1) == '\n') {
                _input.consume();
            }
        } else if (c == '\n') {
            _input.consume();
        }
    }

    /**
     * معالجة DEDENTs عند انخفاض المسافة البادئة
     */
    private void handleDedents(Token referenceToken) {
        int currentIndent = indentations.peek();

        // إصدار DEDENTs حتى نصل إلى مستوى المسافة البادئة الحالي
        while (nextIndentation < currentIndent) {
            // لا نخرج من المستوى 0 أبداً
            if (indentations.peek() == 0) {
                break;
            }

            indentations.pop();
            pendingTokens.add(createDedentToken(referenceToken));

            currentIndent = indentations.peek();
        }
    }

    /**
     * معالجة نهاية الملف
     */
    private Token handleEof(Token eofToken) {
        // توليد جميع DEDENTs المتبقية حتى نصل إلى المستوى 0
        while (indentations.peek() > 0) {
            indentations.pop();
            pendingTokens.add(createDedentToken(eofToken));
        }

        // إضافة EOF كآخر رمز
        pendingTokens.add(eofToken);
        return pendingTokens.poll();
    }

    /**
     * إنشاء توكن INDENT
     */
    private CommonToken createIndentToken(Token referenceToken) {
        CommonToken token = new CommonToken(FlaskTemplateLexer.INDENT, "INDENT");
        // ضبط معلومات الموقع
        token.setLine(referenceToken.getLine() + 1); // السطر التالي
        token.setCharPositionInLine(0);

        // ضبط مؤشرات البداية والنهاية
        int pos = getCharIndex();
        token.setStartIndex(pos);
        token.setStopIndex(pos);

        return token;
    }

    /**
     * إنشاء توكن DEDENT
     */
    private CommonToken createDedentToken(Token referenceToken) {
        CommonToken token = new CommonToken(FlaskTemplateLexer.DEDENT, "DEDENT");
        // ضبط معلومات الموقع
        token.setLine(referenceToken.getLine());
        token.setCharPositionInLine(0);

        // ضبط مؤشرات البداية والنهاية
        int pos = getCharIndex();
        token.setStartIndex(pos);
        token.setStopIndex(pos);

        return token;
    }

    /**
     * معلومات تصحيح الأخطاء
     */
    public String getDebugInfo() {
        return String.format("Levels: ()=%d, []=%d, {}=%d | InsideBrackets=%s | IndentStack=%s",
                parenLevel, bracketLevel, braceLevel,
                isInsideBrackets(),
                indentations.toString());
    }
}