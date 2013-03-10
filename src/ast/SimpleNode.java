package ast;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleNode implements ASTNode {

    public SimpleNode() {
        children = new ArrayList<ASTNode>();
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
