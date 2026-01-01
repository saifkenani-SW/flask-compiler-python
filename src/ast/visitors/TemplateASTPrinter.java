package ast.visitors;

import ast.template.TemplateNode;
import ast.template.TemplateRootNode;
import ast.template.css.*;
import ast.template.html.*;
import ast.template.jinja.blocks.*;
import ast.template.jinja.expressions.*;
import ast.template.jinja.expressions.literals.*;

public class TemplateASTPrinter implements TemplateASTVisitor<Void> {

    private int indent = 0;

    private void print(String text) {
        System.out.println("  ".repeat(indent) + text);
    }
    private void printNode(String name, TemplateNode node) {
        print(name + " [line=" + node.getLine() + ", col=" + node.getColumn() + "]");
    }

    private void withIndent(Runnable r) {
        indent+=3;
        r.run();
        indent-=3;
    }

    // ================= Generic visit =================
    private void visitChildren(TemplateNode node) {
        if (node.getChildren() != null) {
            for (TemplateNode child : node.getChildren()) {
                if (child != null) {
                    child.accept(this);
                } else {
                    print("null child!");
                }
            }
        }
        if (node instanceof JinjaExpressionNode) {
            ((JinjaExpressionNode) node).getExpression().accept(this);
        }
    }


    @Override
    public Void visit(TemplateRootNode node) {
        printNode("TemplateRootNode" , node);
        withIndent(() -> visitChildren(node));
        return null;
    }

    @Override
    public Void visit(CSSAttributeNode node) {
        printNode("CSSAttributeNode " + node.getName(), node);
        return null;
    }
    @Override
    public Void visit(HTMLClosingTagNode node) {
        printNode("HTMLClosingTagNode </" + node.getTagName() + ">", node);
        return null;
    }


    @Override
    public Void visit(DoctypeNode node) {
        printNode("DoctypeNode", node);
        return null;
    }




    // ================= HTML =================
    @Override
    public Void visit(HTMLDocumentNode node) {
        printNode("HTMLDocumentNode", node);
        withIndent(() -> visitChildren(node));
        return null;
    }

    @Override
    public Void visit(HTMLNormalElementNode node) {
        printNode("HTMLNormalElementNode <" + node.getTagName() + ">", node);
        withIndent(() -> {
            node.getAttributes().forEach(a -> a.accept(this));
            visitChildren(node);
        });
        return null;
    }

    @Override
    public Void visit(HTMLVoidElementNode node) {
        printNode("HTMLVoidElementNode <" + node.getTagName() + ">", node);
        return null;
    }

    @Override
    public Void visit(HTMLSelfClosingElementNode node) {
        printNode("HTMLSelfClosingElementNode <" + node.getTagName() + " />", node);
        return null;
    }

    @Override
    public Void visit(HTMLTextNode node) {
        printNode("HTMLTextNode \"" + node.getText() + "\"", node);
        return null;
    }

    @Override
    public Void visit(HTMLAttributeNode node) {
        if (node.isBoolean()) {
            printNode("HTMLBooleanAttribute " + node.getName(), node);
        } else {
            printNode("HTMLAttribute " + node.getName() + " = \"" + node.getValue() + "\"", node);
        }
        return null;
    }

    // ================= Jinja Expressions =================
    @Override
    public Void visit(JinjaExpressionNode node) {
        printNode("JinjaExpressionNode", node);
        withIndent(() -> node.getExpression().accept(this));
        return null;
    }



    @Override
    public Void visit(VariableNode node) {
        String name = node.getPath().isEmpty() ? "<empty>" : node.getPath().get(0);
        printNode("VariableNode " + name, node);

        if (node.getPath().size() > 1) {
            withIndent(() -> {
                for (int i = 1; i < node.getPath().size(); i++) {
                    printNode("Path: " + node.getPath().get(i), node);
                }
            });
        }

        return null;
    }


    @Override
    public Void visit(NumberLiteralNode node) {
        printNode("NumberLiteralNode " + node.getValue(), node);
        return null;
    }

    @Override
    public Void visit(StringLiteralNode node) {
        printNode("StringLiteralNode \"" + node.getValue() + "\"", node);
        return null;
    }

    @Override
    public Void visit(BooleanLiteralNode node) {
        printNode("BooleanLiteralNode " + node.getValue(), node);
        return null;
    }

    @Override
    public Void visit(NoneLiteralNode node) {
        printNode("NoneLiteralNode", node);
        return null;
    }

    @Override
    public Void visit(ListExpressionNode node) {
        printNode("ListExpressionNode", node);
        withIndent(() -> visitChildren(node));
        return null;
    }

    @Override
    public Void visit(DictExpressionNode node) {
        printNode("DictExpressionNode", node);
        withIndent(() -> {
            node.getPairs().forEach((k, v) -> {
                print("Key:");
                withIndent(() -> k.accept(this));
                print("Value:");
                withIndent(() -> v.accept(this));
            });
        });
        return null;
    }

    @Override
    public Void visit(BinaryExpressionNode node) {
        printNode("BinaryExpressionNode " + node.getOperator(), node);
        withIndent(() -> {
            node.getLeft().accept(this);
            node.getRight().accept(this);
        });
        return null;
    }

    @Override
    public Void visit(UnaryExpressionNode node) {
        printNode("UnaryExpressionNode " + node.getOperator(), node);
        withIndent(() -> node.getOperand().accept(this));
        return null;
    }

    @Override
    public Void visit(CallExpressionNode node) {
        printNode("CallExpressionNode", node);
        withIndent(() -> {
            node.getCallee().accept(this);
            node.getArguments().forEach(a -> a.accept(this));
        });
        return null;
    }

    @Override
    public Void visit(FilterExpressionNode node) {
        printNode("FilterExpressionNode |" + node.getFilterName(), node);
        withIndent(() -> {
            node.getInput().accept(this);
            node.getArguments().forEach(a -> a.accept(this));
        });
        return null;
    }

    @Override
    public Void visit(AttributeAccessNode node) {
        printNode("AttributeAccessNode ." + node.getAttribute(), node);
        withIndent(() -> node.getObject().accept(this));
        return null;
    }

    // ================= Jinja Blocks =================


    @Override
    public Void visit(SetBlockNode node) {
        printNode("SetBlockNode " + node.getVariable(), node);
        withIndent(() -> node.getExpression().accept(this));
        visitChildren(node);
        return null;
    }

    @Override
    public Void visit(WithBlockNode node) {
        printNode("WithBlockNode", node);
        withIndent(() -> node.getExpression().accept(this));
        visitChildren(node);
        return null;
    }

    @Override
    public Void visit(IncludeBlockNode node) {
        printNode("IncludeBlockNode \"" + node.getTemplateName() + "\"", node);
        return null;
    }

    @Override
    public Void visit(ImportBlockNode node) {
        printNode("ImportBlockNode \"" + node.getTemplateName() + "\" as " +
                (node.getAlias() != null ? node.getAlias() : "null"), node);
        return null;
    }

    @Override
    public Void visit(FromImportBlockNode node) {
        printNode("FromImportBlockNode \"" + node.getTemplateName() + "\"", node);
        withIndent(() -> node.getImports().forEach(i -> print("Import: " + i)));
        return null;
    }

    @Override
    public Void visit(IfBlockNode node) {
        printNode("IfBlockNode", node);
        withIndent(() -> visitChildren(node));
        return null;
    }

    @Override
    public Void visit(ElifBlockNode node) {
        printNode("ElifBlockNode", node);
        withIndent(() -> visitChildren(node));
        return null;
    }

    @Override
    public Void visit(ElseBlockNode node) {
        printNode("ElseBlockNode", node);
        withIndent(() -> visitChildren(node));
        return null;
    }

    @Override
    public Void visit(ForBlockNode node) {
        printNode("ForBlockNode " + node.getVariable(), node);
        withIndent(() -> node.getIterable().accept(this));
        visitChildren(node);
        return null;
    }

    @Override
    public Void visit(ExtendsBlockNode node) {
        printNode("ExtendsBlockNode \"" + node.getTemplateName() + "\"", node);
        return null;
    }

    @Override
    public Void visit(BlockBlockNode node) {
        printNode("BlockBlockNode " + node.getBlockName(), node);
        visitChildren(node);
        return null;
    }

    @Override
    public Void visit(GenericBlockNode node) {
        printNode("GenericBlockNode " + node.getBlockName(), node);
        if (node.getExpression() != null) {
            withIndent(() -> node.getExpression().accept(this));
        }
        visitChildren(node);
        return null;
    }

    @Override
    public Void visit(CSSStyleNode node) {
        return null;
    }

    @Override
    public Void visit(CSSRuleNode node) {
        return null;
    }

    @Override
    public Void visit(CSSSelectorNode node) {
        return null;
    }

    @Override
    public Void visit(CSSDeclarationNode node) {
        return null;
    }

    @Override
    public Void visit(CSSColorValueNode node) {
        return null;
    }

    @Override
    public Void visit(CSSNumericValueNode node) {
        return null;
    }

    @Override
    public Void visit(CSSStringValueNode node) {
        return null;
    }

    @Override
    public Void visit(CSSKeywordValueNode node) {
        return null;
    }
}
