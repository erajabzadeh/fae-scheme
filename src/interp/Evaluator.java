package interp;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ast.*;

public class Evaluator implements Visitor {

    @Override
    public VObject visit(final AdditionNode node, Environment e) {
    	
        VObject lhs = node.getLHS().accept(Evaluator.this, e),
                rhs = node.getRHS().accept(Evaluator.this, e);

        return new NumV(
        		null,
        		((NumV) lhs).getValue() + ((NumV) rhs).getValue()
        		);
    }

    @Override
    public VObject visit(IntegerNode node, Environment e) {
        return new NumV(null, node.getValue());
    }

    @Override
    public VObject visit(SymbolNode node, Environment e) {
		return e.lookUp(node.getId());
    }

    @Override
    public VObject visit(LetNode node, Environment e) {
        for (ASTNode child : node.getBindings().getChildren()) {
            this.currentEnv.putIn(
                    ((SymbolNode) child.getChildAt(0)).getId(),
                    (VObject) child.getChildAt(1).accept(this, e)
                    );
        }

        VObject vo = (VObject) node.getBody().accept(this, null);

        return vo;
    }
	
    @Override
    public VObject visit(LambdaNode node, Environment e) {
        return new ClosureV(
        		node.toString(),
                node.getParam().getId(),
                node.getBody(),
                new Environment(e));
    }

    @Override
    public VObject visit(ListNode node, Environment e) {
    	
    	ClosureV fun = (ClosureV) node.getChildAt(0).accept(this, e);
    	VObject  arg = node.getChildAt(1).accept(this, e);

    	e.putIn(fun.getParam(), arg);
    	return fun.getBody().accept(this, new Environment(e));
    }

    private Environment currentEnv = new Environment();
    private final Logger logger = Logger.getLogger(Evaluator.class.getName());
    
    {
    	logger.addHandler(new ConsoleHandler());
    	logger.setLevel(Level.ALL);
    }
}
