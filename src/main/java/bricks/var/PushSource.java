package bricks.var;

import suite.suite.action.Statement;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface PushSource<T> extends Source<T> {
    void act(Object key, BiConsumer<T, T> listener);
    void quit(Object o);
    default void act(BiConsumer<T, T> listener) {
        act(listener, listener);
    }
    default void act(Object key, Consumer<T> consumer) {
        act(key, (p, n) -> consumer.accept(n));
    }
    default void act(Consumer<T> consumer) {
        act(consumer, (p, n) -> consumer.accept(n));
    }
    default void act(Object key, Statement statement) {
        act(key, (p, n) -> statement.play());
    }
    default void act(Statement statement) {
        act(statement, (p, n) -> statement.play());
    }
}
