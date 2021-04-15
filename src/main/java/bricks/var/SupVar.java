package bricks.var;

import java.util.function.Supplier;

public class SupVar<T> implements Var<T> {

    Supplier<T> supplier;

    public SupVar() {
        set(null);
    }

    public SupVar(T value) {
        set(value);
    }

    public SupVar(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public void let(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        return supplier.get();
    }

    @Override
    public String toString() {
        return "SupVar{" +
                "supplier=" + supplier +
                '}';
    }
}
