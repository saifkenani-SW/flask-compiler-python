package ast.template;

import ast.template.html.*;
import ast.template.jinja.blocks.JinjaBlockNode;
import ast.template.jinja.expressions.*;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.antlr.v4.runtime.misc.Predicate;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TemplateNodeUtils {

    // Visitor للبحث في الشجرة
    public static List<TemplateNode> findAll(TemplateNode root, Predicate<TemplateNode> predicate) {
        List<TemplateNode> results = new ArrayList<>();
        traverse(root, node -> {
            if (predicate.test(node)) {
                results.add(node);
            }
        });
        return results;
    }

    public static <T extends TemplateNode> List<T> findAllByType(TemplateNode root, Class<T> type) {
        return findAll(root, type::isInstance)
                .stream()
                .map(type::cast)
                .collect(Collectors.toList());
    }

    // البحث عن العناصر بالاسم
    public static List<HTMLElementNode> findElementsByTagName(TemplateNode root, String tagName) {
        return findAllByType(root, HTMLElementNode.class)
                .stream()
                .filter(element -> element.getTagName().equalsIgnoreCase(tagName))
                .collect(Collectors.toList());
    }

    // البحث عن المتغيرات بالاسم
    public static List<VariableNode> findVariablesByName(TemplateNode root, String varName) {
        return findAllByType(root, VariableNode.class)
                .stream()
                .filter(var -> var.getName().equals(varName))
                .collect(Collectors.toList());
    }

    // اجتياز الشجرة
    public static void traverse(TemplateNode node, Consumer<TemplateNode> visitor) {
        visitor.accept(node);

        if (node instanceof HTMLDocumentNode) {
            HTMLDocumentNode doc = (HTMLDocumentNode) node;
            doc.getContent().forEach(child -> traverse(child, visitor));
        } else if (node instanceof HTMLNormalElementNode) {
            HTMLNormalElementNode elem = (HTMLNormalElementNode) node;
            elem.getContent().forEach(child -> traverse(child, visitor));
        } else if (node instanceof JinjaBlockNode) {
            JinjaBlockNode block = (JinjaBlockNode) node;
            block.getContent().forEach(child -> traverse(child, visitor));
        } else if (node instanceof CallExpressionNode) {
            CallExpressionNode call = (CallExpressionNode) node;
            traverse(call.getCallee(), visitor);
            call.getArguments().forEach(arg -> traverse(arg, visitor));
        } else if (node instanceof FilterExpressionNode) {
            FilterExpressionNode filter = (FilterExpressionNode) node;
            traverse(filter.getInput(), visitor);
            filter.getArguments().forEach(arg -> traverse(arg, visitor));
        } else if (node instanceof BinaryExpressionNode) {
            BinaryExpressionNode binary = (BinaryExpressionNode) node;
            traverse(binary.getLeft(), visitor);
            traverse(binary.getRight(), visitor);
        } else if (node instanceof UnaryExpressionNode) {
            UnaryExpressionNode unary = (UnaryExpressionNode) node;
            traverse(unary.getOperand(), visitor);
        } else if (node instanceof ListExpressionNode) {
            ListExpressionNode list = (ListExpressionNode) node;
            list.getElements().forEach(elem -> traverse(elem, visitor));
        } else if (node instanceof DictExpressionNode) {
            DictExpressionNode dict = (DictExpressionNode) node;
            dict.getPairs().forEach((key, value) -> {
                traverse(key, visitor);
                traverse(value, visitor);
            });
        }
    }

    // تحقق من وجود نوع معين
    public static boolean containsType(TemplateNode root, Class<? extends TemplateNode> type) {
        return !findAllByType(root, type).isEmpty();
    }

    // حساب العمق
    public static int getDepth(TemplateNode node) {
        if (node == null) return 0;

        int maxDepth = 0;
        List<TemplateNode> children = getChildren(node);

        for (TemplateNode child : children) {
            int childDepth = getDepth(child);
            maxDepth = Math.max(maxDepth, childDepth);
        }

        return maxDepth + 1;
    }

    // الحصول على الأطفال
    private static List<TemplateNode> getChildren(TemplateNode node) {
        List<TemplateNode> children = new ArrayList<>();

        if (node instanceof HTMLDocumentNode) {
            children.addAll(((HTMLDocumentNode) node).getContent());
        } else if (node instanceof HTMLNormalElementNode) {
            children.addAll(((HTMLNormalElementNode) node).getContent());
        } else if (node instanceof JinjaBlockNode) {
            children.addAll(((JinjaBlockNode) node).getContent());
        } else if (node instanceof CallExpressionNode) {
            children.add(((CallExpressionNode) node).getCallee());
            children.addAll(((CallExpressionNode) node).getArguments());
        }
        // يمكن إضافة المزيد من الأنواع حسب الحاجة

        return children;
    }
}