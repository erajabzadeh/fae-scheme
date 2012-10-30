package interp;

public abstract class ClosureV implements VObject {

    public ClosureV (final String id, final String param, final Environment env) {
        this.id = id;
        this.param = param;
        this.env = env;
    }

    public String getId() {
        return this.id;
    }
    public String getParam() {
        return this.param;
    }
    public Environment getEnv() {
        return this.env;
    }

    private String id;
    private String param;
    private Environment env;
}
