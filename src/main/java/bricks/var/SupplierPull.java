package bricks.var;

import java.util.function.Supplier;

public class SupplierPull<T> implements Pull<T> {

    Supplier<T> supplier;

    public SupplierPull() {
        set(null);
    }

    public SupplierPull(T value) {
        set(value);
    }

    public SupplierPull(Supplier<T> supplier) {
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
