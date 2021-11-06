package bricks.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public interface Pull<T> extends Var<T> {

    void let(Supplier<T> s);
    default void let(Supplier<T> s, Supplier<?> ... roots) {
        let(s, Suite.set((Object[]) roots));
    }
    default void let(Supplier<T> s, Subject $roots) {
        let(new ReactivePull<>(s, $roots));
    }
    default void set(T t) {
        let(() -> t);
    }
}
