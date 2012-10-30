package ast;

import java.util.*;

public class IntegerNode implements ASTNode {

    public IntegerNode (final String value) {
        this.value = value;
    }

    public Integer getValue () {
        return Integer.parseInt(this.value);
    }

public    void addChild(final ASTNode node) {}
public    ASTNode getChildAt(int i) { return null; }
public    List<ASTNode> getChildren() { return null; }

    private String value;
}
