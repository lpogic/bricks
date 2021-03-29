package bricks.var.impulse;

import java.util.Objects;
import java.util.function.Supplier;

public class InequalityImpulse<C> extends DifferentialImpulse<C> {

    public InequalityImpulse(Supplier<C> supplier, C cache) {
        super(supplier, cache);
    }

    @Override
    public boolean occur() {
        C o = supplier.get();
        boolean equals = Objects.equals(o, cache);
        cache = o;
        return !equals;
    }
}
