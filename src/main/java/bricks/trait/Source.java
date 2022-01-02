package bricks.trait;

import bricks.trait.sensor.DifferentialSensor;
import bricks.trait.sensor.Sensor;
import bricks.trait.sensor.InequalitySensor;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
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

    default Sensor willChange() {
        return new InequalitySensor<>(this, get());
    }

    default Sensor willBe(BiPredicate<T, T> will) {
        return new DifferentialSensor<>(this, get()) {
            @Override
            public boolean check() {
                T o = supplier.get();
                boolean testResult = will.test(cache, o);
                cache = o;
                return testResult;
            }
        };
    }

    default Sensor wontBe(BiPredicate<T, T> be) {
        return new DifferentialSensor<>(this, get()) {
            @Override
            public boolean check() {
                T o = supplier.get();
                boolean testResult = be.test(cache, o);
                cache = o;
                return !testResult;
            }
        };
    }

    default Sensor willBe(Predicate<T> be) {
        return new DifferentialSensor<>(this, null) {
            @Override
            public boolean check() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && be.test(o);
            }
        };
    }

    default Sensor wontBe(Predicate<T> be) {
        return new DifferentialSensor<>(this, null) {
            @Override
            public boolean check() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && !be.test(o);
            }
        };
    }

    default Sensor willGive(T sth) {
        return new DifferentialSensor<>(this, null) {
            @Override
            public boolean check() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && Objects.equals(o, sth);
            }
        };
    }

    default Sensor wontGive(T sth) {
        return new DifferentialSensor<>(this, null) {
            @Override
            public boolean check() {
                T o = supplier.get();
                boolean equals = Objects.equals(o, cache);
                cache = o;
                return !equals && !Objects.equals(o, sth);
            }
        };
    }

    static<E> Source<E> wrap(Supplier<E> supplier) {
        return supplier::get;
    }

    static<T> T returnNull() {
        return null;
    }
}
