package bricks.var.impulse;

import java.util.function.Supplier;

public abstract class DifferentialImpulse<C> implements Impulse {
    protected Supplier<C> supplier;
    protected C cache;

    public DifferentialImpulse(Supplier<C> supplier, C cache) {
        this.supplier = supplier;
        this.cache = cache;
    }

}
