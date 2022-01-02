package bricks.trait;

import bricks.trait.sensor.Sensor;
import bricks.trait.sensor.InequalitySensor;
import suite.suite.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ComplexTrait<T> extends Trait<T> {

    protected T cache;
    protected final Sensor[] sensors;
    protected boolean cached;

    public ComplexTrait(Supplier<T> supplier, Subject $roots) {
        this.supplier = supplier;
        this.cached = false;
        List<Sensor> sensors = new ArrayList<>();
        for (var $r : $roots) {
            if($r.is(Sensor.class)) {
                sensors.add($r.asExpected());
            } else if($r.is(Supplier.class)) {
                Supplier<Object> s = $r.asExpected();
                sensors.add(new InequalitySensor<>(s, s.get()));
            }
        }
        this.sensors = sensors.toArray(new Sensor[0]);
    }

    protected void update() {
        if(cached) {
            for (var i : sensors) {
                if(i.check()) cached = false;
            }
        }
    }

    @Override
    public T get() {
        update();
        if(!cached) {
            if(supplier == null) return null;
            cache = supplier.get();
            cached = true;
        }
        return cache;
    }
}
