package bricks.trait.sensor;

import java.util.function.Supplier;

public abstract class DifferentialSensor<C> implements Sensor {

    protected Supplier<C> supplier;
    protected C cache;

    public DifferentialSensor(Supplier<C> supplier, C cache) {
        this.supplier = supplier;
        this.cache = cache;
    }

}
