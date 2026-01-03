import ast.template.css.*;
import ast.template.html.*;
import ast.template.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;
import ast.visitors.TemplateASTBuilder;
import gen.FlaskLexer;
import gen.FlaskTemplateParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateASTTest {

    public static void main(String[] args) throws Exception {
        // مثال نص HTML مع Doctype وصفة عادية وصفة boolean
        String input = "<!DOCTYPE html>\n" +
                "<html lang=\"ar\" dir=\"rtl\">\n" +
                "{% extends \"base.html\" %}\n" +
                "{% block title %}إضافة منتج جديد{% endblock %}\n" +
                "\n" +
                "{% block content %}\n" +
                "<h2>إضافة منتج جديد</h2>\n" +
                "\n" +
                "<form method=\"POST\" action=\"{{ url_for('add_product') }}\" class=\"product-form\" enctype=\"multipart/form-data\">\n" +
                "\n" +
                "    <label for=\"name\">اسم المنتج:</label>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\" required>\n" +
                "\n" +
                "    <label for=\"price\">سعر المنتج ($):</label>\n" +
                "    <input type=\"number\" id=\"price\" name=\"price\" step=\"0.01\" min=\"0.01\" required>\n" +
                "\n" +
                "    <label for=\"details\">التفاصيل / الوصف:</label>\n" +
                "    <textarea id=\"details\" name=\"details\" rows=\"5\" required></textarea>\n" +
                "\n" +
                "    <label for=\"image\">صورة المنتج:</label>\n" +
                "    <input type=\"file\" id=\"image\" name=\"image_file\" accept=\".jpg, .jpeg, .png, .gif\" required>\n" +
                "\n" +
                "    <button type=\"submit\" class=\"submit-button\">حفظ وإضافة المنتج</button>\n" +
                "</form>\n" +
                "{% endblock %}\n" +
                "</html>";

        // 1. إعداد Lexer
        CharStream charStream = CharStreams.fromString(input);
        FlaskLexer lexer = new FlaskLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 2. إعداد Parser
        FlaskTemplateParser parser = new FlaskTemplateParser(tokens);

        // 3. إنشاء شجرة Parse
        FlaskTemplateParser.TemplateRootContext tree = (FlaskTemplateParser.TemplateRootContext) parser.template();

        // 4. زيارة الشجرة باستخدام TemplateASTBuilder
        TemplateASTBuilder visitor = new TemplateASTBuilder();
        TemplateNode rootNode = visitor.visitTemplateRoot(tree);

        // 5. طباعة النتيجة
        printNode(rootNode, 0);
    }

    private static void printNode(TemplateNode node, int indent) {
        if (node == null) return;

        String padding = " ".repeat(indent * 2);
        System.out.println(padding + node.getClass().getSimpleName() +
                " [line=" + node.getLine() + ", col=" + node.getColumn() + "]");

        // لكل نوع من العقد، اطبع محتواه المناسب
        if (node instanceof TemplateRootNode root) {
            root.getDocuments().forEach(child -> printNode(child, indent + 1));
        }

        else if (node instanceof DoctypeNode doctype) {
            System.out.println(padding + "  DOCTYPE: " + doctype.getDoctype());
        }

        else if (node instanceof HTMLDocumentNode htmlDoc) {
            htmlDoc.getAttributes().forEach(attr -> printNode(attr, indent + 1));
            htmlDoc.getContent().forEach(child -> printNode(child, indent + 1));
        }

        else if (node instanceof HTMLNormalElementNode normalNode) {
            System.out.println(padding + "  TAG: <" + normalNode.getTagName() + ">");
            normalNode.getAttributes().forEach(attr -> printNode(attr, indent + 1));
            normalNode.getContent().forEach(child -> printNode(child, indent + 1));
        }

        else if (node instanceof HTMLSelfClosingElementNode selfClosingNode) {
            System.out.println(padding + "  TAG: <" + selfClosingNode.getTagName() + "/>");
            selfClosingNode.getAttributes().forEach(attr -> printNode(attr, indent + 1));
        }

        else if (node instanceof HTMLVoidElementNode voidNode) {
            System.out.println(padding + "  TAG: <" + voidNode.getTagName() + "> (void)");
            voidNode.getAttributes().forEach(attr -> printNode(attr, indent + 1));
        }

        else if (node instanceof HTMLAttributeNode attrNode) {
            System.out.print(padding + "  ATTR: " + attrNode.getName());
            if (attrNode.isBoolean()) {
                System.out.println(" (boolean)");
            } else if (!attrNode.getValueParts().isEmpty()) {
                System.out.print(" = \"");
                for (TemplateNode part : attrNode.getValueParts()) {
                    if (part instanceof HTMLAttributeTextNode textPart) {
                        System.out.print(textPart.getText());
                    } else if (part instanceof JinjaExpressionNode exprPart) {
                        System.out.print("{{");
                        printExpression(exprPart.getExpression(), indent + 3);
                        System.out.print("}}");
                    }
                }
                System.out.println("\"");
            } else {
                System.out.println();
            }
        }

        else if (node instanceof HTMLTextNode textNode) {
                String text = textNode.getText().trim();
                // إزالة أي أقواس مربعة إذا كانت موجودة
                if (text.startsWith("[") && text.endsWith("]")) {
                    text = text.substring(1, text.length() - 1);
                }
                System.out.println(padding + "  TEXT: \"" + text.replace("\n", "\\n") + "\"");
            }

        else if (node instanceof HTMLClosingTagNode closingTag) {
            System.out.println(padding + "  CLOSING: </" + closingTag.getTagName() + ">");
        }

        else if (node instanceof HTMLAttributeTextNode attrText) {
            // تمت معالجته في HTMLAttributeNode
        }

        // Jinja Expression Nodes
        else if (node instanceof JinjaExpressionNode jinjaExpr) {
            System.out.println(padding + "  JINJA EXPRESSION:");
            printExpression(jinjaExpr.getExpression(), indent + 2);
        }

        else if (node instanceof VariableNode varNode) {
            System.out.println(padding + "  VARIABLE: " + String.join(".", varNode.getPath()));
        }

        else if (node instanceof StringLiteralNode strNode) {
            System.out.println(padding + "  STRING: \"" + strNode.getValue() + "\"");
        }

        else if (node instanceof NumberLiteralNode numNode) {
            System.out.println(padding + "  NUMBER: " + numNode.getValue());
        }

        else if (node instanceof BooleanLiteralNode boolNode) {
            System.out.println(padding + "  BOOLEAN: " + boolNode.getValue());
        }

        else if (node instanceof NoneLiteralNode noneNode) {
            System.out.println(padding + "  NONE");
        }

        else if (node instanceof UnaryExpressionNode unaryNode) {
            System.out.println(padding + "  UNARY: " + unaryNode.getOperator());
            printExpression(unaryNode.getOperand(), indent + 2);
        }

        else if (node instanceof BinaryExpressionNode binaryNode) {
            System.out.println(padding + "  BINARY: " + binaryNode.getOperator());
            printExpression(binaryNode.getLeft(), indent + 2);
            printExpression(binaryNode.getRight(), indent + 2);
        }

        else if (node instanceof CallExpressionNode callNode) {
            System.out.println(padding + "  CALL:");
            printExpression(callNode.getCallee(), indent + 2);
            for (ExpressionNode arg : callNode.getArguments()) {
                printExpression(arg, indent + 2);
            }
        }

        else if (node instanceof AttributeAccessNode attrAccess) {
            System.out.println(padding + "  ATTRIBUTE ACCESS: ." + attrAccess.getAttribute());
            printExpression(attrAccess.getObject(), indent + 2);
        }

        else if (node instanceof FilterExpressionNode filterNode) {
            System.out.println(padding + "  FILTER: |" + filterNode.getFilterName());
            printExpression(filterNode.getInput(), indent + 2);
            for (ExpressionNode arg : filterNode.getArguments()) {
                printExpression(arg, indent + 2);
            }
        }

        else if (node instanceof ListExpressionNode listNode) {
            System.out.println(padding + "  LIST [" + listNode.getElements().size() + " items]:");
            for (ExpressionNode elem : listNode.getElements()) {
                printExpression(elem, indent + 2);
            }
        }

        else if (node instanceof DictExpressionNode dictNode) {
            System.out.println(padding + "  DICT [" + dictNode.getPairs().size() + " pairs]:");
            for (Map.Entry<ExpressionNode, ExpressionNode> entry : dictNode.getPairs().entrySet()) {
                System.out.println(padding + "    KEY:");
                printExpression(entry.getKey(), indent + 4);
                System.out.println(padding + "    VALUE:");
                printExpression(entry.getValue(), indent + 4);
            }
        }

        // Jinja Block Nodes
        else if (node instanceof IfBlockNode ifNode) {
            System.out.println(padding + "  IF BLOCK:");
            System.out.println(padding + "    CONDITION:");
            printExpression(ifNode.getCondition(), indent + 4);
            System.out.println(padding + "    CONTENT:");
            ifNode.getContent().forEach(child -> printNode(child, indent + 4));

        }

        else if (node instanceof ElifBlockNode elifNode) {
            System.out.println(padding + "  ELIF BLOCK:");
            System.out.println(padding + "    CONDITION:");
            printExpression(elifNode.getCondition(), indent + 4);
            System.out.println(padding + "    CONTENT:");
            elifNode.getContent().forEach(child -> printNode(child, indent + 4));
        }

        else if (node instanceof ElseBlockNode elseNode) {
            System.out.println(padding + "  ELSE BLOCK:");
            elseNode.getContent().forEach(child -> printNode(child, indent + 4));
        }

        else if (node instanceof ForBlockNode forNode) {
            System.out.println(padding + "  FOR BLOCK: " + forNode.getVariable() + " in");
            System.out.println(padding + "    ITERABLE:");
            printExpression(forNode.getIterable(), indent + 4);
            System.out.println(padding + "    CONTENT:");
            forNode.getContent().forEach(child -> printNode(child, indent + 4));
            if (forNode.getElseBlock() != null) {
                System.out.println(padding + "    ELSE:");
                printNode(forNode.getElseBlock(), indent + 4);
            }
        }

        else if (node instanceof BlockBlockNode blockNode) {
            System.out.println(padding + "  BLOCK: " + blockNode.getBlockName());
            blockNode.getContent().forEach(child -> printNode(child, indent + 2));
        }

        else if (node instanceof SetBlockNode setNode) {
            System.out.println(padding + "  SET: " + setNode.getVariable() + " =");
            printExpression(setNode.getExpression(), indent + 2);
        }

        else if (node instanceof IncludeBlockNode includeNode) {
            System.out.println(padding + "  INCLUDE: " + includeNode.getTemplateName());
        }

        else if (node instanceof ImportBlockNode importNode) {
            System.out.println(padding + "  IMPORT: " + importNode.getTemplateName() +
                    (importNode.getAlias() != null ? " as " + importNode.getAlias() : ""));
        }

        else if (node instanceof FromImportBlockNode fromNode) {
            System.out.println(padding + "  FROM IMPORT: from " + fromNode.getTemplateName() +
                    " import " + String.join(", ", fromNode.getImports()));
        }

        else if (node instanceof WithBlockNode withNode) {
            System.out.println(padding + "  WITH BLOCK:");
            printExpression(withNode.getExpression(), indent + 2);
            withNode.getContent().forEach(child -> printNode(child, indent + 2));
        }

        else if (node instanceof ExtendsBlockNode extendsNode) {
            System.out.println(padding + "  EXTENDS: " + extendsNode.getTemplateName());
        }

        else if (node instanceof GenericBlockNode genericNode) {
            System.out.println(padding + "  GENERIC BLOCK: " + genericNode.getBlockName());
            if (genericNode.getExpression() != null) {
                System.out.println(padding + "    EXPRESSION:");
                printExpression(genericNode.getExpression(), indent + 4);
            }
            genericNode.getContent().forEach(child -> printNode(child, indent + 2));
        }
        // نسخة مبسطة للطباعة
        else if (node instanceof CSSStyleNode cssStyle) {
            System.out.println(padding + "CSS STYLE NODE");

            // طباعة سمات وسم <style>
            if (!cssStyle.getAttributes().isEmpty()) {
                System.out.println(padding + "  Style Attributes:");
                for (CSSAttributeNode attr : cssStyle.getAttributes()) {
                    System.out.println(padding + "    " + attr.getName() + "=\"" + attr.getValue() + "\"");
                }
            }

            // طباعة قواعد CSS
            System.out.println(padding + "  CSS Rules (" + cssStyle.getRules().size() + "):");
            for (CSSRuleNode rule : cssStyle.getRules()) {
                System.out.print(padding + "    Selectors: ");
                List<String> selectors = new ArrayList<>();
                for (CSSSelectorNode selector : rule.getSelectors()) {
                    selectors.add(selector.getSelector());
                }
                System.out.println(String.join(", ", selectors));

                for (CSSDeclarationNode decl : rule.getDeclarations()) {
                    System.out.print(padding + "      " + decl.getProperty() + ": ");
                    List<String> values = new ArrayList<>();
                    for (CSSValueNode val : decl.getValues()) {
                        if (val instanceof CSSStringValueNode) values.add(((CSSStringValueNode)val).getValue());
                        else if (val instanceof CSSNumericValueNode) values.add(((CSSNumericValueNode)val).getValue());
                        else if (val instanceof CSSColorValueNode) values.add(((CSSColorValueNode)val).getValue());
                        else if (val instanceof CSSKeywordValueNode) values.add(((CSSKeywordValueNode)val).getValue());
                    }
                    System.out.println(String.join(" ", values) + ";");
                }
            }
        }



        else {
            System.out.println(padding + "  UNKNOWN NODE TYPE: " + node.getClass().getName());
        }
    }

    private static void printExpression(ExpressionNode expr, int indent) {
        if (expr == null) return;

        String padding = " ".repeat(indent * 2);
        System.out.println(padding + expr.getClass().getSimpleName() +
                " [line=" + expr.getLine() + ", col=" + expr.getColumn() + "]");

        if (expr instanceof VariableNode varNode) {
            System.out.println(padding + "  VAR: " + String.join(".", varNode.getPath()));
        }
        else if (expr instanceof StringLiteralNode strNode) {
            System.out.println(padding + "  STR: \"" + strNode.getValue() + "\"");
        }
        else if (expr instanceof NumberLiteralNode numNode) {
            System.out.println(padding + "  NUM: " + numNode.getValue());
        }
        else if (expr instanceof BooleanLiteralNode boolNode) {
            System.out.println(padding + "  BOOL: " + boolNode.getValue());
        }
        else if (expr instanceof NoneLiteralNode) {
            System.out.println(padding + "  NONE");
        }
        else if (expr instanceof UnaryExpressionNode unaryNode) {
            System.out.println(padding + "  UNARY OP: " + unaryNode.getOperator());
            printExpression(unaryNode.getOperand(), indent + 1);
        }
        else if (expr instanceof BinaryExpressionNode binaryNode) {
            System.out.println(padding + "  BINARY OP: " + binaryNode.getOperator());
            printExpression(binaryNode.getLeft(), indent + 1);
            printExpression(binaryNode.getRight(), indent + 1);
        }
        else if (expr instanceof CallExpressionNode callNode) {
            System.out.println(padding + "  CALL:");
            printExpression(callNode.getCallee(), indent + 1);
            for (ExpressionNode arg : callNode.getArguments()) {
                printExpression(arg, indent + 1);
            }
        }
        else if (expr instanceof AttributeAccessNode attrAccess) {
            System.out.println(padding + "  ATTR ACCESS: ." + attrAccess.getAttribute());
            printExpression(attrAccess.getObject(), indent + 1);
        }
        else if (expr instanceof FilterExpressionNode filterNode) {
            System.out.println(padding + "  FILTER: |" + filterNode.getFilterName());
            printExpression(filterNode.getInput(), indent + 1);
            for (ExpressionNode arg : filterNode.getArguments()) {
                printExpression(arg, indent + 1);
            }
        }
        else if (expr instanceof ListExpressionNode listNode) {
            System.out.println(padding + "  LIST [" + listNode.getElements().size() + "]:");
            for (ExpressionNode elem : listNode.getElements()) {
                printExpression(elem, indent + 1);
            }
        }
        else if (expr instanceof DictExpressionNode dictNode) {
            System.out.println(padding + "  DICT [" + dictNode.getPairs().size() + "]:");
            for (Map.Entry<ExpressionNode, ExpressionNode> entry : dictNode.getPairs().entrySet()) {
                System.out.println(padding + "    KEY:");
                printExpression(entry.getKey(), indent + 2);
                System.out.println(padding + "    VALUE:");
                printExpression(entry.getValue(), indent + 2);
            }
        }


    }

    private static void printCSSRule(CSSRuleNode rule, int indent) {
        String padding = " ".repeat(indent * 2);
        System.out.println(padding + "CSS RULE [line=" + rule.getLine() + ", col=" + rule.getColumn() + "]");

        // طباعة المحددات
        if (!rule.getSelectors().isEmpty()) {
            System.out.print(padding + "  SELECTORS: ");
            List<String> selectorNames = new ArrayList<>();
            for (CSSSelectorNode selector : rule.getSelectors()) {
                selectorNames.add(selector.getSelector());
            }
            System.out.println(String.join(", ", selectorNames));
        }

        // طباعة التعريفات
        if (!rule.getDeclarations().isEmpty()) {
            System.out.println(padding + "  DECLARATIONS (" + rule.getDeclarations().size() + "):");
            for (CSSDeclarationNode decl : rule.getDeclarations()) {
                printCSSDeclaration(decl, indent + 2);
            }
        }
    }

    private static void printCSSDeclaration(CSSDeclarationNode decl, int indent) {
        String padding = " ".repeat(indent * 2);
        System.out.println(padding + "DECLARATION: " + decl.getProperty());

        // طباعة القيم
        if (!decl.getValues().isEmpty()) {
            System.out.print(padding + "  VALUES: ");
            List<String> valueStrings = new ArrayList<>();
            for (CSSValueNode value : decl.getValues()) {
                valueStrings.add(getCSSValueString(value));
            }
            System.out.println(String.join(" ", valueStrings));
        }
    }

    private static String getCSSValueString(CSSValueNode value) {
        if (value instanceof CSSStringValueNode stringValue) {
            return "\"" + stringValue.getValue() + "\"";
        } else if (value instanceof CSSNumericValueNode numericValue) {
            return numericValue.getValue();
        } else if (value instanceof CSSColorValueNode colorValue) {
            return colorValue.getValue();
        } else if (value instanceof CSSKeywordValueNode keywordValue) {
            return keywordValue.getValue();
        }
        return "UNKNOWN_VALUE";
    }
}