package interp;

public class ClosureV implements VObject {

    public ClosureV (final String id, final String param, final Environment env) {
        this.id = id;
        this.param = param;
        this.env = env;
    }

    @Override
    public Object evaluate (final Environment env) {
        return null;
    }

    private String id;
    private String param;
    private Environment env;
}
