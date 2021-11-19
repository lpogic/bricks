package bricks.var;

import bricks.var.num.NumPull;
import bricks.var.num.NumReactivePull;
import bricks.var.num.SupplierNumPull;
import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public interface Var<V> extends Source<V>, Target<V>{

    static<T> Pull<T> pull() {
        return new SupplierBasedPull<>();
    }

    static<T> Pull<T> pull(T t) {
        return new SupplierBasedPull<>(t);
    }

    static<T> Pull<T> let() {
        return new SupplierBasedPull<>();
    }

    static<T> Pull<T> let(Supplier<T> supplier) {
        var v = new SupplierBasedPull<T>();
        v.let(supplier);
        return v;
    }

    static<T> ReactivePull<T> let(Supplier<T> sup, Supplier<?> ... roots) {
        return new ReactivePull<>(sup, Suite.set((Object[]) roots));
    }

    static<T> ReactivePull<T> let(Supplier<T> sup, Subject $roots) {
        return new ReactivePull<>(sup, $roots);
    }

    static<T> Push<T> push() {
        return new SubjectBasedPush<>(null);
    }

    static<T> Push<T> push(T t) {
        return new SubjectBasedPush<>(t);
    }

    static NumPull num() {
        return new SupplierNumPull();
    }

    static NumPull num(Number n) {
        return new SupplierNumPull(n);
    }

    static NumPull num(Supplier<Number> supplier) {
        var v = new SupplierNumPull();
        v.let(supplier);
        return v;
    }

    static NumReactivePull num(Supplier<Number> sup, Supplier<?> ... roots) {
        return new NumReactivePull(sup, Suite.set((Object[]) roots));
    }

    static NumReactivePull num(Supplier<Number> sup, Subject $roots) {
        return new NumReactivePull(sup, $roots);
    }
}
