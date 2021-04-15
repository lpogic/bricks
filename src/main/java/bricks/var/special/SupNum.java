package bricks.var.special;

import java.util.function.Supplier;

public class SupNum implements Num {

    Supplier<Number> supplier;

    public SupNum() {
        set(null);
    }

    public SupNum(Number value) {
        set(value);
    }

    public SupNum(Supplier<Number> s) {
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
