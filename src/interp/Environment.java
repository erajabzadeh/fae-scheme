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

    public void putIn(final String id, final VObject vo) {
        this.table.put(id, vo);
    }

    @Override
    public String toString() {
        return table.toString();
    }

    private Map<String, VObject> table;
}
