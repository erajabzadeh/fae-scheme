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
        /*
        String symbol = node.getSymbol();
        Object value = this.currentEnvironment.lookUp(symbol);

        if (value == null)
            throw new Exception("Unbound identifier '" + symbol + "'.");

        return value;
        */

        return null;
    }

    @Override
    public VObject visit(LetNode node) {
        return null;
    }

    @Override
    public VObject visit(LambdaNode node) {
        return null;
    }

    private Environment currentEnv = new Environment();
}
