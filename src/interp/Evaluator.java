package interp;

import ast.*;

public class Evaluator implements Visitor {

    @Override
    public VObject visit(final AdditionNode node) {
        System.out.println("==AdditionNode==");
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
        System.out.println("==IntegerNode==");
        return new NumV(null, node.getValue());
    }

    @Override
    public VObject visit(SymbolNode node) {
        System.out.println("==SymbolNode==");
        String id = node.getId();

        // TODO: throw exception if not in env
        VObject vo = this.currentEnv.lookUp(id);
        System.out.println("symbol vo=" + vo);
        return vo;
    }

    @Override
    public VObject visit(LetNode node) {
        System.out.println("==LeetNode==");
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
        System.out.println("==LambdaNode==");

        final LambdaNode n = node;
        return new ClosureV(node.toString(),
                node.getParam().getId(),
                new Environment(this.currentEnv)) {

                    @Override
                    public Object evaluate(final Environment env) {
                        return n.getBody().accept(Evaluator.this);
                    }
        };
    }

    @Override
    public VObject visit(ListNode node) {
        System.out.println("==ListNode==");
        ASTNode n0 = node.getChildAt(0);

        System.out.println("n0=" + n0);
        if (n0 instanceof LambdaNode) {
            System.out.print("evaluating...\n\t");
            LambdaNode ln = (LambdaNode) n0;
            SymbolNode param = ln.getParam();
            ASTNode body = ln.getBody(),
                    arg = node.getChildAt(1);

            System.out.println("param=" + param
                    + ", body=" + body + ", arg=" + arg);
            VObject vo = (VObject) arg.accept(this);

            this.currentEnv.putIn(
                param.getId(),
                vo
            );

            return (VObject) body.accept(this);
        }
        else
            return (VObject) node.getChildAt(0).accept(this);
    }

    private Environment currentEnv = new Environment();
}
