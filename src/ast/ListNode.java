package ast;

import interp.Visitor;

public class ListNode extends SimpleNode {

    @Override
    public Object accept(final Visitor visitor) {
        return visitor.visit(this);
    }

}

