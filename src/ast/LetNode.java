package ast;

import interp.Visitor;

public class LetNode extends SimpleNode {

    public LetNode (final ASTNode bindings, final ASTNode body) {
        this.children.add(bindings);
        this.children.add(body);
    }

    public ASTNode getBindings() {
        return this.getChildAt(0);
    }

    public ASTNode getBody() {
        return this.getChildAt(1);
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
