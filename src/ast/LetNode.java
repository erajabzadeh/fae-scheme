package ast;

public class LetNode extends SimpleNode {

    public LetNode (final ASTNode bindings, final ASTNode body) {
        this.children.add(bindings);
        this.children.add(body);
    }

}
