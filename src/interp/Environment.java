package interp;

import java.util.*;

public class Environment {

    public Environment () {
        this.table = new HashMap<String, VObject>();
    }

    public Environment (final Environment env) {
        this (env.table);
    }

    private Environment (final Map<String, VObject> table) {
        this.table = new HashMap<String, VObject>(table);
    }

    public VObject lookUp (final String id) {
        return this.table.get(id);
    }

    private Map<String, VObject> table;
}
