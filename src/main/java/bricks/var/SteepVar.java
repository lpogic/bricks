package bricks.var;

import java.util.function.Supplier;

public class SteepVar<T> implements SteepSource<T> {

    protected T value;
    protected Supplier<T> supplier;

    public SteepVar() {}

    public SteepVar(T value) {
        this.value = value;
    }

    public void set(T value) {
        supplier = null;
        this.value = value;
    }

    public void let(Supplier<T> supplier) {
        this.value = null;
        this.supplier = supplier;
    }

    @Override
    public T getOr(T reserve) {
        T t = supplier != null ? supplier.get() : value;
        return t != null ? t : reserve;
    }
}
