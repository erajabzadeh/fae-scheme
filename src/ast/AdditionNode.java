package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitable;
import interp.Visitor;

public class AdditionNode extends SimpleNode implements Visitable {

    public AdditionNode (final ASTNode lhs, final ASTNode rhs) {
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
        return this.getLHS() + " + " + this.getRHS();
    }

    @Override
    public String toFAEString() {
        return String.format("(add %s %s)", 
                this.getLHS().toFAEString(),
                this.getRHS().toFAEString());
    }
}
