package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitable;
import interp.Visitor;

public class DivisionNode extends SimpleNode implements Visitable {

    public DivisionNode (final ASTNode lhs, final ASTNode rhs) {
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
		return this.getLHS() + " / " + this.getRHS();
	}

	@Override
	public String toFAEString() {
		return String.format("(div %s %s)", 
				this.getLHS().toFAEString(),
				this.getRHS().toFAEString());
	}
}
