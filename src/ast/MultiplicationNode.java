package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

public class MultiplicationNode extends SimpleNode {

    public MultiplicationNode (final ASTNode lhs, final ASTNode rhs) {
        this.addChild(lhs);
        this.addChild(rhs);
    }

    public ASTNode getLHS() {
        return this.getChildAt(0);
    }

    public ASTNode getRHS() {
        return this.getChildAt(1);
    }

    @Override
    public VObject accept(Visitor visitor, Environment e) {
        return visitor.visit(this, e);
    }

    @Override
    public String toString() {
        return String.format("(* %s %s)",
                this.getLHS(),
                this.getRHS()
                );
    }

    @Override
    public String toFAEString() {
        return String.format("(mul %s %s)", 
                this.getLHS().toFAEString(),
                this.getRHS().toFAEString());
    }
}
