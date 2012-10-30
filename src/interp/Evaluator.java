package interp;

import ast.*;

public class Evaluator implements Visitor {

    @Override
    public Object visit(AdditionNode node) {
        return null;
    }

    @Override
    public Object visit(IntegerNode node) {
        return new NumV("", node.getValue());
    }

    @Override
    public Object visit(SymbolNode node) {
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
    public Object visit(LetNode node) {
        return null;
    }

    @Override
    public Object visit(LambdaNode node) {
        return null;
    }

    @Override
    public Object visit(SimpleNode node) {
        return null;
    }
}
