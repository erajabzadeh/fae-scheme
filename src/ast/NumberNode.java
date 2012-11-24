package ast;

import interp.Environment;
import interp.VObject;
import interp.Visitor;

import java.util.*;

public class NumberNode implements ASTNode {

    public NumberNode (final Number value) {
        this.value = value;
    }

    public Number getValue () {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public VObject accept (final Visitor visitor, Environment e) {
        return visitor.visit(this, null);
    }

    @Override
    public String toFAEString() {
        return String.format("(numV %s)", this.getValue());
    }

    // TODO: throw unsupportedOperation
    public void addChild(final ASTNode node) {}
    public ASTNode getChildAt(int i) { return null; }
    public List<ASTNode> getChildren() { return null; }

    private Number value;
}

