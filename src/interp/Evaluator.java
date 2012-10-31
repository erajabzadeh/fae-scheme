package interp;

import ast.*;

public class Evaluator implements Visitor {

    @Override
    public VObject visit(final AdditionNode node) {
        return new ClosureV (node.toString(), null, new Environment(this.currentEnv)) {

            @Override
            public Object evaluate(final Environment e) {
                ASTNode lhsNode  = node.getChildAt(0),
                        rhsNode = node.getChildAt(1);

                System.out.println("ENV:" + this.getEnv());

                Object lhs = ((VObject) lhsNode.accept(Evaluator.this)).evaluate(this.getEnv()),
                       rhs = ((VObject) rhsNode.accept(Evaluator.this)).evaluate(this.getEnv());
 
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
        VObject vo = this.currentEnv.lookUp(id);

        return vo;
    }

    @Override
    public VObject visit(LetNode node) {

        for (ASTNode child : node.getBindings().getChildren()) {
            this.currentEnv.putIn(
                ((SymbolNode) child.getChildAt(0)).getId(),
                (VObject) child.getChildAt(1).accept(this)
            );
        }

        VObject vo = (VObject) node.getBody().accept(this);

        return vo;
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
