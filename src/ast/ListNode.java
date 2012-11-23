package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

public class ListNode extends SimpleNode {

    @Override
    public VObject accept(final Visitor visitor, Environment e) {
        return visitor.visit(this, e);
    }

}

