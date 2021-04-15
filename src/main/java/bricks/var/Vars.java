package bricks.var;

import bricks.var.special.Num;
import bricks.var.special.NumPreserve;
import bricks.var.special.SupNum;
import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public class Vars {

    public static<T> Var<T> get() {
        return new SupVar<>();
    }

    public static<T> Var<T> get(Class<T> type) {
        return new SupVar<>();
    }

    public static<T> Var<T> set(T t) {
        return new SupVar<>(t);
    }

    public static<T> Var<T> let(Supplier<T> supplier) {
        var v = new SupVar<T>();
        v.let(supplier);
        return v;
    }

    public static<T> Preserve<T> let(Supplier<T> sup, Supplier<?> ... roots) {
        return new Preserve<>(sup, Suite.set((Object[]) roots));
    }

    public static<T> Preserve<T> let(Supplier<T> sup, Subject $roots) {
        return new Preserve<>(sup, $roots);
    }

    public static Num num() {
        return new SupNum();
    }

    public static Num num(Number n) {
        return new SupNum(n);
    }

    public static Num num(Supplier<Number> supplier) {
        var v = new SupNum();
        v.let(supplier);
        return v;
    }

    public static NumPreserve num(Supplier<Number> sup, Supplier<?> ... roots) {
        return new NumPreserve(sup, Suite.set((Object[]) roots));
    }

    public static NumPreserve num(Supplier<Number> sup, Subject $roots) {
        return new NumPreserve(sup, $roots);
    }
}
