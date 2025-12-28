package ast.template.visitors;

import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import gen.FlaskTemplateParser;
import gen.FlaskTemplateParserBaseVisitor;


public class TemplateASTBuilderVisitor extends FlaskTemplateParserBaseVisitor<TemplateNode> {

    @Override
    public TemplateNode visitTemplateRoot(
            FlaskTemplateParser.TemplateRootContext ctx) {

        TemplateRootNode root =
                new TemplateRootNode(
                        ctx.start.getLine(),
                        ctx.start.getCharPositionInLine()
                );

        for (var htmlCtx : ctx.html()) {
            TemplateNode htmlNode = visit(htmlCtx);
            root.addDocument(htmlNode);
        }

        return root;
    }


}
