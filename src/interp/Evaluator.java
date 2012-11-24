package interp;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.LogFormatter;

import ast.*;

public class Evaluator implements Visitor {

    public Evaluator() {
        Handler handler = this.logger.getParent().getHandlers()[0];
        handler.setFormatter(new LogFormatter());
        logger.setLevel(Level.INFO);
    }

    @Override
    public VObject visit(final AdditionNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(Evaluator.this, e),
             rhs = (NumV) node.getRHS().accept(Evaluator.this, e);

        return NumV.add(lhs, rhs);
    }

    @Override
    public VObject visit(final SubtractionNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(Evaluator.this, e),
             rhs = (NumV) node.getRHS().accept(Evaluator.this, e);

        return NumV.sub(lhs, rhs);
    }

    @Override
    public VObject visit(final MultiplicationNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(Evaluator.this, e),
             rhs = (NumV) node.getRHS().accept(Evaluator.this, e);

        return NumV.mul(lhs, rhs);
    }

    @Override
    public VObject visit(final DivisionNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(Evaluator.this, e),
             rhs = (NumV) node.getRHS().accept(Evaluator.this, e);

        return NumV.div(lhs, rhs);
    }

    @Override
    public VObject visit(NumberNode node, Environment e) {
        return new NumV(null, node.getValue());
    }

    @Override
    public VObject visit(SymbolNode node, Environment e) {
        return e.lookUp(node.getId());
    }

    @Override
    public VObject visit(LetNode node, Environment e) {
        for (ASTNode child : node.getBindings().getChildren()) {
            e.putIn(
                    ((SymbolNode) child.getChildAt(0)).getId(),
                    child.getChildAt(1).accept(this, e)
                   );
        }

        return node.getBody().accept(this, e);
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
