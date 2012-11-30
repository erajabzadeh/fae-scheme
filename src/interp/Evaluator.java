package interp;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.*;

import ast.*;

public class Evaluator implements Visitor {

    public enum Scope { STATIC, DYNAMIC };

    public Evaluator() {
        this.setUpLogger();
        this.configure();
    }

    @Override
    public VObject visit(final AdditionNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(this, e),
             rhs = (NumV) node.getRHS().accept(this, e);

        return NumV.add(lhs, rhs);
    }

    @Override
    public VObject visit(final SubtractionNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(this, e),
             rhs = (NumV) node.getRHS().accept(this, e);

        return NumV.sub(lhs, rhs);
    }

    @Override
    public VObject visit(final MultiplicationNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(this, e),
             rhs = (NumV) node.getRHS().accept(this, e);

        return NumV.mul(lhs, rhs);
    }

    @Override
    public VObject visit(final DivisionNode node, Environment e) {

        NumV lhs = (NumV) node.getLHS().accept(this, e),
             rhs = (NumV) node.getRHS().accept(this, e);

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
                this.scope == Scope.STATIC ? new Environment(e) : new Environment()); 
    }

    @Override
    public VObject visit(ListNode node, Environment e) {

        final ClosureV fun = (ClosureV) node.getFunction().accept(this, e);
        final VObject  arg = node.getArgument().accept(this, e);

        logger.info("Evaluating fun=" + fun + ", arg=" + arg);
        Environment evaluationEnv = 
            this.scope == Scope.STATIC ? new Environment(fun.getEnvironment()) : e;
        evaluationEnv.putIn(fun.getParam(), arg);
        logger.info("Environment=" + evaluationEnv.toFAEString());

        return fun.getBody().accept(this, evaluationEnv);
    }

    private void configure() {

        // scoping
        String ds = JVMOptionParser.instance().value("dynamic-scope");
        if ("true".equals(ds) || "".equals(ds)) {
            this.scope = Scope.DYNAMIC;
            this.logger.info("Dynamic scoping");
        }
        else {
            this.scope = Scope.STATIC;
            this.logger.info("Static scoping");
        }

        // TODO: misc configs (logging level, ...)
    }

    private void setUpLogger() {
        Handler handler = this.logger.getParent().getHandlers()[0];
        handler.setFormatter(new LogFormatter());
        logger.setLevel(Level.INFO);
    }

    private Scope scope         = Scope.STATIC;
    private final Logger logger	= Logger.getLogger(Evaluator.class.getName());
}
