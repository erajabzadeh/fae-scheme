package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

public class LetNode extends SimpleNode {

    public LetNode (final ASTNode bindings, final ASTNode body) {
        this.children.add(bindings);
        this.children.add(body);
    }

    public ListNode getBindings() {
        return (ListNode) this.getChildAt(0);
    }

    public ASTNode getBody() {
        return this.getChildAt(1);
    }

    @Override
    public VObject accept(Visitor visitor, Environment e) {
        return visitor.visit(this, null);
    }

}
