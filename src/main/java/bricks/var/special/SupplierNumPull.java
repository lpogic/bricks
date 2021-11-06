package bricks.var.special;

import java.util.function.Supplier;

public class SupplierNumPull implements NumPull {

    Supplier<Number> supplier;

    public SupplierNumPull() {
        set(null);
    }

    public SupplierNumPull(Number value) {
        set(value);
    }

    public SupplierNumPull(Supplier<Number> s) {
        this.supplier = s;
    }

    public void let(Supplier<Number> s) {
        this.supplier = s;
    }

    @Override
    public Number get() {
        return supplier.get();
    }
}
