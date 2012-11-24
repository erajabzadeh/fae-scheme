package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

import java.util.*;

public class SymbolNode implements ASTNode {

    public SymbolNode (final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public VObject accept (final Visitor visitor, Environment e) {
        return visitor.visit(this, e);
    }

    @Override
    public String toString() {
    	return this.id;
    }
    
    public void addChild(final ASTNode node) {}
    public ASTNode getChildAt(int i) { return null; }
    public List<ASTNode> getChildren() { return null; }

    private String id;

	@Override
	public String toFAEString() {
		return String.format("(id '%s)", this.getId());
	}

}

