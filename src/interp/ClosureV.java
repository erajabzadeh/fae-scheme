package interp;

import ast.ASTNode;

public class ClosureV implements VObject {

    public ClosureV (final String id,
                     final String param,
                     final ASTNode body,
                     final Environment env) {
        this.id = id;
        this.param = param;
        this.body = body;
        this.env = env;
    }

    public String getId() {
        return this.id;
    }

    public String getParam() {
        return this.param;
    }

    public ASTNode getBody() {
        return this.body;
    }

    public Environment getEnv() {
        return this.env;
    }

    @Override
    public String toFAEString() {
        // right ) not necessary
        return String.format("(closureV '%s %s %s",
                this.getParam(),
                this.getBody().toFAEString(),
                this.getEnv().toFAEString()
                );
    }

    @Override
    public String toString() {
        return "[type=ClosureV, value=" +
            this.getParam() + " -> " +
            this.getBody() + "]";
    }

    private String id;
    private String param;
    private ASTNode body;
    private Environment env;
}
