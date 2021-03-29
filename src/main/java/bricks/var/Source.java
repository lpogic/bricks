package bricks.var;

import bricks.var.impulse.DifferentialImpulse;
import bricks.var.impulse.Impulse;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface Source<T> extends Sup<T> {
    T getOr(T reserve);
    default Sup<T> or(T reserve) {
        return () -> getOr(reserve);
    }

    default Impulse willGive(BiPredicate<T, T> will) {
        return new DifferentialImpulse<>(this, get()) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean testResult = will.test(cache, o);
                cache = o;
                return testResult;
            }
        };
    }

    default Impulse wontGive(BiPredicate<T, T> wont) {
        return new DifferentialImpulse<>(this, get()) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean testResult = wont.test(cache, o);
                cache = o;
                return !testResult;
            }
        };
    }
}
