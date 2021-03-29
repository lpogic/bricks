package bricks.var;

@FunctionalInterface
public interface Target<T> {
    void set(T t);
}
