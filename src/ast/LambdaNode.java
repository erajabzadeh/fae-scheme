package ast;

import interp.VObject;
import interp.Visitor;

public class LambdaNode extends SimpleNode {

    public LambdaNode (final ASTNode params, final ASTNode body) {
        this.children.add(params);
        this.children.add(body);
    }

    public SymbolNode getParam() {
        return (SymbolNode) this.getChildAt(0);
    }

    public ASTNode getBody() {
        return this.getChildAt(1);
    }

    @Override
    public VObject accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
