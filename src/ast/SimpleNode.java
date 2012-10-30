package ast;

import interp.Visitor;

import java.util.List;
import java.util.ArrayList;

public class SimpleNode implements ASTNode {

    public SimpleNode() {
        children = new ArrayList<ASTNode>();
    }

    @Override
    public Object accept(final Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void addChild(final ASTNode node) {
        this.children.add(node);
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public ASTNode getChildAt(int i) {
        return children.get(i);
    }

    @Override
    public String toString() {
        return children.toString();
    }

    protected List<ASTNode> children;
}
