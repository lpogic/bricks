package bricks.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Vars {

    public static<T> Var<T> get() {
        return new TwoWayVar<>();
    }

    public static<T> Var<T> get(Class<T> type) {
        return new TwoWayVar<>();
    }

    public static<T> Var<T> set(T t) {
        return new TwoWayVar<>(t);
    }

    public static<T> Var<T> let(Supplier<T> supplier) {
        var v = new TwoWayVar<T>();
        v.let(supplier);
        return v;
    }

    public static<T> Preserve<T> let(Supplier<T> sup, Supplier<?> ... roots) {
        return new Preserve<>(sup, Suite.set((Object[]) roots));
    }

    public static<T> Preserve<T> let(Supplier<T> sup, Subject $roots) {
        return new Preserve<>(sup, $roots);
    }

    public static<T> TwoWayVar<T> let(Consumer<T> consumer, Supplier<T> supplier) {
        return new TwoWayVar<>(consumer, supplier);
    }
}
