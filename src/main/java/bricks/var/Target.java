package bricks.var;

import java.util.function.Consumer;

@FunctionalInterface
public interface Target<T> extends Consumer<T> {
    void set(T t);
    default void accept(T t) {
        set(t);
    }
}
