package bricks.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static suite.suite.$uite.$;

public interface Var<T> extends Source<T>, Target<T> {

    void let(Supplier<T> supplier);
    void let(Consumer<T> consumer);
    void let(Consumer<T> consumer, Supplier<T> supplier);
    void reset(T value);
    default void let(Supplier<T> sup, Supplier<?> ... roots) {
        let(sup, Suite.set((Object[]) roots));
    }
    default void let(Supplier<T> sup, Subject $roots) {
        let(new Preserve<>(sup, $roots));
    }
}
