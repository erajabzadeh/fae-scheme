package ast;

import interp.Visitor;

import java.util.*;

public class SymbolNode implements ASTNode {

    public SymbolNode (final String symbol) {}

    @Override
    public Object accept (final Visitor visitor) {
        return visitor.visit(this);
    }

    public    void addChild(final ASTNode node) {}
    public    ASTNode getChildAt(int i) { return null; }
    public    List<ASTNode> getChildren() { return null; }

}

