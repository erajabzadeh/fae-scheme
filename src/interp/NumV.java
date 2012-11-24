package interp;

public class NumV implements VObject {

    public NumV (final String id, final Number value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toFAEString() {
        if (this.value != null)
            return "(numV " + this.value + ")";
        else
            return "(id '" + this.id + ")";
    }

    @Override
    public String toString() {
        return "[type=NumV, value=" + getValue() + "]";
    }

    // need this because java doesn't unbox java.lang.Number
    public static NumV add (final NumV l, final NumV r) {
        Number lhs = l.getValue();
        Number rhs = r.getValue();

        if (lhs instanceof Double || rhs instanceof Double)
            return new NumV(
                    null,
                    Double.valueOf(lhs.doubleValue() + rhs.doubleValue())
                    );
        else
            return new NumV(
                    null,
                    Integer.valueOf(lhs.intValue() + rhs.intValue())
                    );
    }

    public static NumV sub (final NumV l, final NumV r) {
        Number lhs = l.getValue();
        Number rhs = r.getValue();

        if (lhs instanceof Double || rhs instanceof Double)
            return new NumV(
                    null,
                    Double.valueOf(lhs.doubleValue() - rhs.doubleValue())
                    );
        else
            return new NumV(
                    null,
                    Integer.valueOf(lhs.intValue() - rhs.intValue())
                    );
    }

    public static NumV mul (final NumV l, final NumV r) {
        Number lhs = l.getValue();
        Number rhs = r.getValue();

        if (lhs instanceof Double || rhs instanceof Double)
            return new NumV(
                    null,
                    Double.valueOf(lhs.doubleValue() * rhs.doubleValue())
                    );
        else
            return new NumV(
                    null,
                    Integer.valueOf(lhs.intValue() * rhs.intValue())
                    );
    }

    public static NumV div (final NumV l, final NumV r) {
        Number lhs = l.getValue();
        Number rhs = r.getValue();

        if (lhs instanceof Double || rhs instanceof Double)
            return new NumV(
                    null,
                    Double.valueOf(lhs.doubleValue() / rhs.doubleValue())
                    );
        else
            return new NumV(
                    null,
                    Integer.valueOf(lhs.intValue() / rhs.intValue())
                    );
    }

    private String  id;
    private Number value;
}
