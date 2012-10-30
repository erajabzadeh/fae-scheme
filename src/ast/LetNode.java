package ast;

import interp.Visitor;

public class LetNode extends SimpleNode {

    public LetNode (final ASTNode bindings, final ASTNode body) {
        this.children.add(bindings);
        this.children.add(body);
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
