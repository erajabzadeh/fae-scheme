package ast;

import java.util.List;

public interface ASTNode {
    void addChild(final ASTNode node);
    ASTNode getChildAt(int i);
    List<ASTNode> getChildren();
}
