package bricks.trait.sensor;

import java.util.function.Supplier;

public class DiversitySensor<C> extends DifferentialSensor<C> {

    public DiversitySensor(Supplier<C> supplier, C cache) {
        super(supplier, cache);
    }

    @Override
    public boolean check() {
        C o = supplier.get();
        if(o == cache) {
            return false;
        } else {
            cache = o;
            return true;
        }
    }
}
