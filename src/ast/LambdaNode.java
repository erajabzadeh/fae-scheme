package ast;

public class LambdaNode extends SimpleNode {

    public LambdaNode (final ASTNode params, final ASTNode body) {
        this.children.add(params);
        this.children.add(body);
    }

}
