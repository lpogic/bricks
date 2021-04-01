package bricks.var;

public interface SteepSource<T> {
    T getOr(T reserve);
    default Source<T> or(T reserve) {
        return () -> getOr(reserve);
    }
}
