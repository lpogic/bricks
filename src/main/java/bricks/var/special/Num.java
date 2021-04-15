package bricks.var.special;

import bricks.var.Preserve;
import bricks.var.Target;
import bricks.var.Var;
import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public interface Num extends NumSource, Var<Number> {

    void let(Supplier<Number> s);
    default void let(Supplier<Number> s, Supplier<?> ... roots) {
        let(s, Suite.set((Object[]) roots));
    }
    default void let(Supplier<Number> s, Subject $roots) {
        let(new Preserve<>(s, $roots));
    }
    default void set(Number t) {
        let(() -> t);
    }

}
