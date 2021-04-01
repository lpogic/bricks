package bricks.var;

import bricks.var.impulse.DifferentialImpulse;
import bricks.var.impulse.Impulse;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Source<T> extends Supplier<T> {

    default T getOr(T reserve) {
        T t = get();
        return t != null ? t : reserve;
    }

    default Source<T> or(T reserve) {
        return () -> getOr(reserve);
    }

    default<O> Source<O> per(Function<T, O> per) {
        return () -> per.apply(get());
    }

    default<F, V extends Source<F>> Source<F> at(Function<T, V> fun) {
        return () -> fun.apply(get()).get();
    }

    default Impulse willBe(BiPredicate<T, T> will) {
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

    default Impulse wontBe(BiPredicate<T, T> be) {
        return new DifferentialImpulse<>(this, get()) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean testResult = be.test(cache, o);
                cache = o;
                return !testResult;
            }
        };
    }

    default Impulse willBe(Predicate<T> be) {
        return new DifferentialImpulse<>(this, null) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && be.test(o);
            }
        };
    }

    default Impulse wontBe(Predicate<T> be) {
        return new DifferentialImpulse<>(this, null) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && !be.test(o);
            }
        };
    }

    default Impulse willGive(T sth) {
        return new DifferentialImpulse<>(this, null) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && Objects.equals(o, sth);
            }
        };
    }

    default Impulse wontGive(T sth) {
        return new DifferentialImpulse<>(this, null) {
            @Override
            public boolean occur() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && !Objects.equals(o, sth);
            }
        };
    }
}
