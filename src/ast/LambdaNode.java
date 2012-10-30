package ast;

import interp.Visitor;

public class LambdaNode extends SimpleNode {

    public LambdaNode (final ASTNode params, final ASTNode body) {
        this.children.add(params);
        this.children.add(body);
    }

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
