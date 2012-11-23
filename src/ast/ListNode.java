package ast;

import interp.VObject;
import interp.Visitor;

public class ListNode extends SimpleNode {

    @Override
    public VObject accept(final Visitor visitor) {
        return visitor.visit(this);
    }

}

