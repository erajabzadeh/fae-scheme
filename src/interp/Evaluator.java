package interp;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.LogFormatter;

import ast.ASTNode;
import ast.AdditionNode;
import ast.IntegerNode;
import ast.LambdaNode;
import ast.LetNode;
import ast.ListNode;
import ast.SymbolNode;

public class Evaluator implements Visitor {

	public Evaluator() {
		Handler handler = this.logger.getParent().getHandlers()[0];
		handler.setFormatter(new LogFormatter());
		logger.setLevel(Level.INFO);
	}
	
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
    	
    	final ClosureV fun = (ClosureV) node.getChildAt(0).accept(this, e);
    	final VObject  arg = node.getChildAt(1).accept(this, e);

    	logger.info("Evaluating fun=" + fun + ", arg=" + arg);
    	return fun.getBody().accept(this, new Environment(fun.getEnv()) {{
    		putIn(fun.getParam(), arg);
    		logger.info("Environment=" + this.toFAEString());
    	}});
    }

    private Environment currentEnv = new Environment();
    private final Logger logger = Logger.getLogger(Evaluator.class.getName());
}
