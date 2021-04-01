package bricks.var;

public interface Source<T> extends Sup<T> {
    T getOr(T reserve);
    default Sup<T> or(T reserve) {
        return () -> getOr(reserve);
    }
}
