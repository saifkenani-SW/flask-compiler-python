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

    public FlaskTemplateLexerBase(CharStream input) {
        super(input);
        indentations.push(0);
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

        if (token.getType() == FlaskTemplateLexer.NEWLINE) {
            return handleNewline(token);
        } else if (token.getType() == Token.EOF) {
            return handleEof(token);
        } else {
            lastConsumedToken = token;
            return token;
        }
    }

    private Token handleNewline(Token newlineToken) {
        // إضافة NEWLINE للرموز المعلقة
        pendingTokens.add(newlineToken);

        // حساب المسافة البادئة للسطر التالي (دون استهلاك input!)
        nextIndentation = peekIndentation();
        int currentIndent = indentations.peek();

        // INDENT
        if (nextIndentation > currentIndent) {
            indentations.push(nextIndentation);
            pendingTokens.add(createIndentToken(newlineToken));
        }
        // DEDENT
        else if (nextIndentation < currentIndent) {
            handleDedents(newlineToken);
        }

        calculatedNextIndent = false;
        return pendingTokens.poll();
    }

    private int peekIndentation() {
        if (calculatedNextIndent) {
            return nextIndentation;
        }

        // حفظ الموضع الحالي
        int mark = _input.mark();
        int indent = 0;
        int c;

        while (true) {
            c = _input.LA(1);

            // حساب المسافات
            if (c == ' ') {
                indent++;
                _input.consume();
            } else if (c == '\t') {
                indent += 8;
                _input.consume();
            }
            // تخطي التعليقات
            else if (c == '#') {
                while (_input.LA(1) != '\n' && _input.LA(1) != '\r' && _input.LA(1) != CharStream.EOF) {
                    _input.consume();
                }
                continue;
            }
            // تخطي الأسطر الفارغة
            else if (c == '\n' || c == '\r') {
                // سطر فارغ، ابدأ من جديد
                consumeNewline();
                indent = 0;
                continue;
            }
            // أي حرف آخر - توقف
            else {
                break;
            }
        }

        // العودة للموضع الأصلي
        _input.release(mark);
        calculatedNextIndent = true;
        nextIndentation = indent;
        return indent;
    }

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

    private void handleDedents(Token referenceToken) {
        int currentIndent = indentations.peek();

        while (nextIndentation < currentIndent) {
            if (currentIndent == 0) break;

            indentations.pop();
            pendingTokens.add(createDedentToken(referenceToken));

            currentIndent = indentations.peek();
        }
    }

    private Token handleEof(Token eofToken) {
        // توليد DEDENTs حتى المستوى 0
        while (indentations.peek() > 0) {
            indentations.pop();
            pendingTokens.add(createDedentToken(eofToken));
        }

        pendingTokens.add(eofToken);
        return pendingTokens.poll();
    }

    private CommonToken createIndentToken(Token referenceToken) {
        CommonToken token = new CommonToken(FlaskTemplateLexer.INDENT, "INDENT");
        token.setLine(referenceToken.getLine() + 1); // السطر التالي
        token.setCharPositionInLine(0);

        // تعيين start/stop index مناسب
        int pos = getCharIndex();
        token.setStartIndex(pos);
        token.setStopIndex(pos);

        return token;
    }

    private CommonToken createDedentToken(Token referenceToken) {
        CommonToken token = new CommonToken(FlaskTemplateLexer.DEDENT, "DEDENT");
        token.setLine(referenceToken.getLine());
        token.setCharPositionInLine(0);

        int pos = getCharIndex();
        token.setStartIndex(pos);
        token.setStopIndex(pos);

        return token;
    }
}