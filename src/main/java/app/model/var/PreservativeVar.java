package app.model.var;

import app.model.var.impulse.Impulse;
import app.model.var.impulse.InequalityImpulse;
import suite.suite.Subject;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class PreservativeVar<T> implements Source<T>{

    private final List<Impulse> impulses = new LinkedList<>();
    private Supplier<T> sup;
    private T cache;
    private boolean cached;

    public PreservativeVar() {
        this.cached = false;
    }

    public PreservativeVar(Supplier<T> sup) {
        this.sup = sup;
        this.cached = false;
    }

    public PreservativeVar(Supplier<T> sup, Subject $roots) {
        this.sup = sup;
        this.cached = false;
        for (var $r : $roots) {
            if($r.is(Impulse.class)) {
                this.impulses.add($r.asExpected());
            } else if($r.is(Supplier.class)) {
                Supplier<Object> s = $r.asExpected();
                this.impulses.add(new InequalityImpulse<>(s, s.get()));
            }
        }
    }

    private void update() {
        if(cached) {
            for (var i : impulses) {
                if(i.occur()) cached = false;
            }
        }
    }

    @Override
    public boolean present() {
        update();
        return (cached && cache != null) || sup != null;
    }

    @Override
    public T getOr(T reserve) {
        update();
        if(!cached) {
            if(sup == null) return reserve;
            cache = sup.get();
            cached = true;
        }
        return cache != null ? cache : reserve;
    }

    @Override
    public T get() {
        update();
        if(!cached) {
            if(sup == null) return null;
            cache = sup.get();
            cached = true;
        }
        return cache;
    }

    public PreservativeVar<T> let(Supplier<T> sup) {
        this.sup = sup;
        this.cached = false;
        return this;
    }
}
