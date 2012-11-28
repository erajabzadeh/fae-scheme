package interp;

import java.util.*;

public class Environment {

    public Environment () {
        this (Collections.<String, VObject>emptyMap());
    }

    public Environment (final Environment env) {
        this (env.table);
    }

    private Environment (final Map<String, VObject> table) {
        this.table = new LinkedHashMap<String, VObject>(table);
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
        if (this.table.isEmpty())
            return "(mtSub)";

        StringBuilder sb = new StringBuilder(),
                      end= new StringBuilder();

        for (Map.Entry<String, VObject> e : this.table.entrySet()) {
            sb.append("(aSub ")
              .append("'" + e.getKey() + " ")
              .append(e.getValue().toFAEString() + " ");

            end.append(")");
        }
        sb.append("(mtSub)").append(end.toString());

        return sb.toString();
    }

    private Map<String, VObject> table;
}
