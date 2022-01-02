package bricks.trait;

import suite.suite.action.Statement;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Push<T> extends Source<T> {
    void act(Object key, Statement statement);
    void quit(Object o);
    default void act(Statement statement) {
        act(statement, statement);
    }

    default void act(Consumer<T> consumer) {
        var statement = new Statement(){

            @Override
            public void revel() {
                var p = Push.this.get();
                consumer.accept(p);
            }
        };
        act(statement, statement);
    }

    default void act(T prev, BiConsumer<T, T> consumer) {
        var statement = new Statement(){
            T previous = prev;

            @Override
            public void revel() {
                var p = previous;
                previous = Push.this.get();
                consumer.accept(p, previous);
            }
        };
        act(statement, statement);
    }
}
