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
                    (VObject) child.getChildAt(1).accept(this, null)
                    );
        }

        VObject vo = (VObject) node.getBody().accept(this, null);

        return vo;
    }

    @Override
    public VObject visit(LambdaNode node, Environment e) {
    	System.out.println("visiting lambda node: " + node);
        
        return new ClosureV(
        		node.toString(),
                node.getParam().getId(),
                node.getBody(),
                new Environment(e)) {

            @Override
            public VObject evaluate(final Environment env) {
            	System.out.println("closure eval ENV=" + this.getEnv());
            	System.out.println("evaluating: " + this.getBody());
            	
            	VObject vo = this.getBody().accept(Evaluator.this, this.getEnv());
            	
            	return vo;
            }
        };
    }

    @Override
    public VObject visit(ListNode node, Environment e) {
    	
    	final ClosureV fun = (ClosureV) node.getChildAt(0).accept(this, e);
    	System.out.println("fun=" + fun);
    	
    	final VObject arg = node.getChildAt(1).accept(this, e);
    	System.out.println("arg=" + arg);
    	
    	return fun.getBody().accept(this, new Environment(e) {{
    			this.putIn(fun.getParam(), arg);
    		}}); 
    }

    private Environment currentEnv = new Environment();
    private final Logger logger = Logger.getLogger(Evaluator.class.getName());
    
    {
    	logger.addHandler(new ConsoleHandler());
    	logger.setLevel(Level.ALL);
    }
}
