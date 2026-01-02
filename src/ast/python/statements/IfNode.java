package ast.python.statements;

import ast.python.expressions.ExpressionNode;
import ast.python.program.BlockNode;
import ast.python.visitors.PythonASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfNode extends StatementNode {
    private ExpressionNode condition;
    private BlockNode thenBlock;
    private List<ElifBranch> elifBranches;
    private BlockNode elseBlock;

    public static class ElifBranch {
        private ExpressionNode condition;
        private BlockNode block;

        public ElifBranch(ExpressionNode condition, BlockNode block) {
            this.condition = condition;
            this.block = block;
        }

        public ExpressionNode getCondition() { return condition; }
        public BlockNode getBlock() { return block; }
    }

    public IfNode(int line, int column, ExpressionNode condition, BlockNode thenBlock) {
        super(line, column);
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elifBranches = new ArrayList<>();
        addChild(condition);
        addChild(thenBlock);
    }

    public ExpressionNode getCondition() { return condition; }
    public void setCondition(ExpressionNode condition) {
        this.condition = condition;
        addChild(condition);
    }

    public BlockNode getThenBlock() { return thenBlock; }
    public void setThenBlock(BlockNode thenBlock) {
        this.thenBlock = thenBlock;
        addChild(thenBlock);
    }

    public List<ElifBranch> getElifBranches() { return elifBranches; }
    public void addElifBranch(ExpressionNode condition, BlockNode block) {
        elifBranches.add(new ElifBranch(condition, block));
        addChild(condition);
        addChild(block);
    }

    public BlockNode getElseBlock() { return elseBlock; }
    public void setElseBlock(BlockNode elseBlock) {
        this.elseBlock = elseBlock;
        addChild(elseBlock);
    }

    public boolean hasElif() { return !elifBranches.isEmpty(); }
    public boolean hasElse() { return elseBlock != null; }

    @Override
    public <T> T accept(PythonASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

//IfNode
//├─ Condition:
//│   └─ BinaryOp (>)
//│       ├─ Identifier x
//│       └─ IntLiteral 0
//├─ ThenBlock:
//│   └─ Block
//│       └─ ExpressionStatement
//│           └─ Call print("positive")
//├─ ElifBranch
//│   ├─ Condition:
//│   │   └─ BinaryOp (==)
//│   │       ├─ Identifier x
//│   │       └─ IntLiteral 0
//│   └─ Block:
//│       └─ Block
//│           └─ ExpressionStatement
//│               └─ Call print("zero")
//└─ ElseBlock:
//    └─ Block
//        └─ ExpressionStatement
//            └─ Call print("negative")