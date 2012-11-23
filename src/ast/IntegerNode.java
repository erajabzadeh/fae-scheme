package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

import java.util.*;

public class IntegerNode implements ASTNode {

    public IntegerNode (final String value) {
        this.value = value;
    }

    public Integer getValue () {
        return Integer.parseInt(this.value);
    }

    @Override
    public String toString() {
    	return this.value;
    }
    
    @Override
    public VObject accept (final Visitor visitor, Environment e) {
        return visitor.visit(this, null);
    }

    public    void addChild(final ASTNode node) {}
    public    ASTNode getChildAt(int i) { return null; }
    public    List<ASTNode> getChildren() { return null; }

    private String value;
}

