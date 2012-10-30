package ast;

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
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
