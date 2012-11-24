package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

public class ListNode extends SimpleNode {

    @Override
    public VObject accept(final Visitor visitor, Environment e) {
        return visitor.visit(this, e);
    }

    public ASTNode getFunction() {
        return this.getChildAt(0);
    }

    public ASTNode getArgument() {
        return this.getChildAt(1);
    }

    @Override
    public String toFAEString() {
        return String.format("(app %s %s)",
                this.getFunction().toFAEString(),
                this.getArgument().toFAEString());
    }

}

