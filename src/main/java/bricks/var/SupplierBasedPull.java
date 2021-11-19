package bricks.var;

import java.util.function.Supplier;

public class SupplierBasedPull<T> implements Pull<T> {

    Supplier<T> supplier;

    public SupplierBasedPull() {
        set(null);
    }

    public SupplierBasedPull(T value) {
        set(value);
    }

    public SupplierBasedPull(Supplier<T> supplier) {
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
