package bricks.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static suite.suite.$uite.$;

public class Var<T> implements Source<T>, Target<T> {

    protected T value;
    protected Supplier<T> supplier;

    public Var() {}

    public Var(T value) {
        this.value = value;
    }

    @Override
    public void set(T value) {
        supplier = null;
        this.value = value;
    }

    public void let(Supplier<T> supplier) {
        value = null;
        this.supplier = supplier;
    }

    public void let(Supplier<T> sup, Supplier<?> ... roots) {
        let(sup, Suite.set((Object[]) roots));
    }

    public void let(Supplier<T> sup, Subject $roots) {
        let(new PreservativeVar<>(sup, $roots));
    }

    @Override
    public T get() {
        return supplier != null ? supplier.get() : value;
    }
}
