package bricks.var;

public interface SteepSource<T> {
    T getOr(T reserve);
    default Sup<T> or(T reserve) {
        return () -> getOr(reserve);
    }
}
