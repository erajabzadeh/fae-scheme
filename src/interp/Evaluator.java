package interp;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ast.*;

public class Evaluator implements Visitor {

    @Override
    public VObject visit(final AdditionNode node) {
        return new ClosureV(
        		node.toString(),
        		null,
        		node,
        		new Environment(this.currentEnv)) {

            @Override
            public VObject evaluate(final Environment e) {
                ASTNode lhsNode = node.getChildAt(0),
                        rhsNode = node.getChildAt(1);

                VObject lhs = lhsNode.accept(Evaluator.this).evaluate(e),
                        rhs = rhsNode.accept(Evaluator.this).evaluate(e);

                /*
                if (!(lhs instanceof NumV && rhs instanceof NumV))
                	throw new Exception ("wrong operand type!");
                */
                
                NumV result = new NumV(
                		null,
                		((NumV) lhs).getValue() + ((NumV) rhs).getValue()
                		);
                
                return result; 
            }

        };
    }

    @Override
    public VObject visit(IntegerNode node) {
        return new NumV(null, node.getValue());
    }

    @Override
    public VObject visit(SymbolNode node) {
    	return new ClosureV(
			node.getId(),
			node.getId(),
			null,
			null) {
				@Override
				public VObject evaluate(Environment e) {
					System.out.println("symbol ENV=" + e);
					return e.lookUp(this.getId());
			}
    	};
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
    	System.out.println("visiting lambda node: " + node);

        System.out.println();
        
        return new ClosureV(
        		node.toString(),
                node.getParam().getId(),
                node.getBody(),
                new Environment(this.currentEnv)) {

            @Override
            public VObject evaluate(final Environment e) {
            	System.out.println("closure eval ENV=" + this.getEnv());
            	System.out.println("evaluating: " + this.getBody());
            	
            	VObject vo = this.getBody().accept(Evaluator.this).evaluate(this.getEnv());
            	
            	return vo;
            }
        };
    }

    @Override
    public VObject visit(ListNode node) {
    	
    	final ClosureV fun = (ClosureV) node.getChildAt(0).accept(this);
    	System.out.println("fun=" + fun);
    	
    	final VObject arg = node.getChildAt(1).accept(this);
    	
    	fun.apply(arg);
    	System.out.println("arg=" + arg);
    	
    	return fun; 
    }

    private Environment currentEnv = new Environment();
    private final Logger logger = Logger.getLogger(Evaluator.class.getName());
    
    {
    	logger.addHandler(new ConsoleHandler());
    	logger.setLevel(Level.ALL);
    }
}
