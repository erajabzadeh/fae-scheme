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

    public Environment union(final Environment env) {
        Environment result = new Environment(this);
        result.table.putAll(env.table);

        return result;
    }

    public String toFAEString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, VObject> e : this.table.entrySet()) {
            sb.append("(aSub ");
            sb.append("'" + e.getKey() + " ");
            sb.append(e.getValue().toFAEString() + " ");
        }
        sb.append("(mtSub))");

        return sb.toString();
    }

    private Map<String, VObject> table;
}
