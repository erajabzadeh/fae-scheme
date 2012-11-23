package interp;

public interface Visitable {
    VObject accept(final Visitor visitor, Environment e);
}
