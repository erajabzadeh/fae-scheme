package interp;

import ast.*;

public class Evaluator implements Visitor {

    @Override
    public VObject visit(final AdditionNode node) {
        return new ClosureV (node.toString(), null, new Environment(this.currentEnv)) {

            @Override
            public Object evaluate() {
                ASTNode leftNode  = node.getChildAt(0),
                        rightNode = node.getChildAt(1);

                Object lhs = ((VObject) leftNode.accept(Evaluator.this)).evaluate(),
                       rhs = ((VObject) rightNode.accept(Evaluator.this)).evaluate();
 
                return (Integer) lhs + (Integer) rhs;
            }

        };
    }

    @Override
    public VObject visit(IntegerNode node) {
        return new NumV(null, node.getValue());
    }

    @Override
    public VObject visit(SymbolNode node) {
        String id = node.getId();

        // TODO: throw exception if not in env
        return this.currentEnv.lookUp(id);
    }

    @Override
    public VObject visit(LetNode node) {
        return null;
    }

    @Override
    public VObject visit(LambdaNode node) {
        return null;
    }

    @Override
    public VObject visit(ListNode node) {
        return null;
    }

    private Environment currentEnv = new Environment();
}
