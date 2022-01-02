package bricks.trait.sensor;

import java.util.Objects;
import java.util.function.Supplier;

public class InequalitySensor<C> extends DifferentialSensor<C> {

    public InequalitySensor(Supplier<C> supplier, C cache) {
        super(supplier, cache);
    }

    @Override
    public boolean check() {
        C o = supplier.get();
        boolean equals = Objects.equals(o, cache);
        cache = o;
        return !equals;
    }
}
