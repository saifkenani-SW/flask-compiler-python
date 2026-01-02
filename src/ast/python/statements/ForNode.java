package ast.python.statements;

import ast.python.expressions.ExpressionNode;
import ast.python.expressions.IdentifierNode;
import ast.python.program.BlockNode;
import ast.python.visitors.PythonASTVisitor;

public class ForNode extends StatementNode {
    private IdentifierNode variable;
    private ExpressionNode iterable;
    private BlockNode body;

    public ForNode(int line, int column, IdentifierNode variable, ExpressionNode iterable, BlockNode body) {
        super(line, column);
        this.variable = variable;
        this.iterable = iterable;
        this.body = body;
        addChild(variable);
        addChild(iterable);
        addChild(body);
    }

    public IdentifierNode getVariable() { return variable; }
    public void setVariable(IdentifierNode variable) {
        this.variable = variable;
        addChild(variable);
    }

    public ExpressionNode getIterable() { return iterable; }
    public void setIterable(ExpressionNode iterable) {
        this.iterable = iterable;
        addChild(iterable);
    }

    public BlockNode getBody() { return body; }
    public void setBody(BlockNode body) {
        this.body = body;
        addChild(body);
    }



    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}



//For
//├─ Variable:
//│   └─ Identifier i
//├─ Iterable:
//│   └─ Identifier items
//└─ Body:
//    └─ Block
//        └─ ExpressionStatement
//            └─ Call
//                ├─ Identifier print
//                └─ Identifier i