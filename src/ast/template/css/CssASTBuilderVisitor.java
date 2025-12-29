package ast.template.css;

import ast.template.css.value.*;
import gen.FlaskTemplateParser;
import gen.FlaskTemplateParserBaseVisitor;

import java.util.ArrayList;
import java.util.List;

public final class CssASTBuilderVisitor
        extends FlaskTemplateParserBaseVisitor<Object> {

    @Override
    public CssString visitCssStringValue(
            FlaskTemplateParser.CssStringValueContext ctx) {

        String raw = ctx.CSS_STRING().getText();
        String value = raw.substring(1, raw.length() - 1);

        return new CssString(value);
    }

    @Override
    public CssNumeric visitCssNumericValue(
            FlaskTemplateParser.CssNumericValueContext ctx) {

        return new CssNumeric(ctx.CSS_NUMERIC().getText());
    }

    @Override
    public CssColor visitCssColorValue(
            FlaskTemplateParser.CssColorValueContext ctx) {

        return new CssColor(ctx.CSS_COLOR().getText());
    }

    @Override
    public CssKeyword visitCssKeywordValue(
            FlaskTemplateParser.CssKeywordValueContext ctx) {

        return new CssKeyword(ctx.CSS_KEYWORD().getText());
    }

    @Override
    public CssValues visitCssValueList(
            FlaskTemplateParser.CssValueListContext ctx) {

        List<CssValue> values = new ArrayList<>();

        for (FlaskTemplateParser.CssValueContext valueCtx : ctx.cssValue()) {
            CssValue value = (CssValue) visit(valueCtx);
            values.add(value);
        }

        return new CssValues(values);
    }



}

