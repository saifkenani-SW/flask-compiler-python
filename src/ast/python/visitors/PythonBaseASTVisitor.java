package ast.python.visitors;

import ast.python.PythonNode;

public abstract class PythonBaseASTVisitor<T> implements PythonASTVisitor<T> {

    protected T defaultResult() { return null; }

    protected T aggregateResult(T aggregate, T nextResult) {
        return nextResult;
    }

    protected T visitChildren(PythonNode node) {
        T result = defaultResult();
        for (PythonNode child : node.getChildren()) {
            T childResult = child.accept(this);
             result = aggregateResult(result, childResult);
        }
        return result;
    }
}
