package bricks.var;

import bricks.var.impulse.Impulse;
import bricks.var.impulse.InequalityImpulse;
import suite.suite.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PreservativeVar<T> implements Source<T> {

    protected T cache;
    protected Supplier<T> supplier;
    protected final Impulse[] impulses;
    protected boolean cached;

    public PreservativeVar(Supplier<T> supplier, Subject $roots) {
        this.supplier = supplier;
        this.cached = false;
        List<Impulse> impulses = new ArrayList<>();
        for (var $r : $roots) {
            if($r.is(Impulse.class)) {
                impulses.add($r.asExpected());
            } else if($r.is(Supplier.class)) {
                Supplier<Object> s = $r.asExpected();
                impulses.add(new InequalityImpulse<>(s, s.get()));
            }
        }
        this.impulses = impulses.toArray(new Impulse[0]);
    }

    protected void update() {
        if(cached) {
            for (var i : impulses) {
                if(i.occur()) cached = false;
            }
        }
    }

    @Override
    public T getOr(T reserve) {
        update();
        if(!cached) {
            if(supplier == null) return reserve;
            cache = supplier.get();
            cached = true;
        }
        return cache != null ? cache : reserve;
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
