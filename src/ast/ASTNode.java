package ast;

import interp.Visitable;

import java.util.List;

public interface ASTNode extends Visitable {
    void addChild(final ASTNode node);
    ASTNode getChildAt(int i);
    List<ASTNode> getChildren();
    String toFAEString();
}
