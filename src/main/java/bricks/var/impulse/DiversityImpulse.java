package bricks.var.impulse;

import java.util.function.Supplier;

public class DiversityImpulse<C> extends DifferentialImpulse<C> {

    public DiversityImpulse(Supplier<C> supplier, C cache) {
        super(supplier, cache);
    }

    @Override
    public boolean occur() {
        C o = supplier.get();
        if(o == cache) {
            return false;
        } else {
            cache = o;
            return true;
        }
    }
}
