package interp;

public interface Visitable {
    Object accept(final Visitor visitor);
}
