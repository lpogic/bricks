package bricks.var;

import bricks.var.special.NumPull;
import bricks.var.special.NumReactivePull;
import bricks.var.special.SupplierNumPull;
import suite.suite.Subject;
import suite.suite.Suite;
import suite.suite.action.Statement;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Var<V> extends Source<V>, Target<V>{

    static<T> Pull<T> pull(T t) {
        return new SupplierPull<>(t);
    }

    static<T> Pull<T> let() {
        return new SupplierPull<>();
    }

    static<T> Pull<T> let(Supplier<T> supplier) {
        var v = new SupplierPull<T>();
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
        return new SubjectPush<>();
    }

    static<T> Push<T> push(T t) {
        return new SubjectPush<>(t);
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
