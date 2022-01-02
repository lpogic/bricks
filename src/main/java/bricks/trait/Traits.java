package bricks.trait;

import bricks.trait.number.NumberSource;
import bricks.trait.number.NumberTrait;
import bricks.trait.number.ComplexNumberTrait;
import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public interface Traits {

    static<T> Trait<T> set() {
        return new Trait<>();
    }

    static<T> Trait<T> set(T value) {
        var v = new Trait<T>();
        v.set(value);
        return v;
    }

    static<T> Trait<T> let() {
        return new Trait<>();
    }

    static<T> Trait<T> let(Supplier<T> supplier) {
        var v = new Trait<T>();
        v.let(supplier);
        return v;
    }

    static<T> ComplexTrait<T> let(Supplier<T> sup, Supplier<?> ... roots) {
        return new ComplexTrait<>(sup, Suite.set((Object[]) roots));
    }

    static<T> ComplexTrait<T> let(Supplier<T> sup, Subject $roots) {
        return new ComplexTrait<>(sup, $roots);
    }

    static NumberTrait num() {
        return new NumberTrait();
    }

    static NumberTrait num(Number n) {
        var v = new NumberTrait();
        v.set(n);
        return v;
    }

    static NumberTrait num(Supplier<Number> supplier) {
        var v = new NumberTrait();
        v.let(supplier);
        return v;
    }

    static ComplexNumberTrait num(Supplier<Number> sup, Supplier<?> ... roots) {
        return new ComplexNumberTrait(sup, Suite.set((Object[]) roots));
    }

    static ComplexNumberTrait num(Supplier<Number> sup, Subject $roots) {
        return new ComplexNumberTrait(sup, $roots);
    }

    static NumberSource trim(Number value, Supplier<Number> min, Supplier<Number> max) {
        return () -> {
            var v = value.doubleValue();
            var mn = min.get().doubleValue();
            if(v > mn) {
                return Math.min(v, max.get().doubleValue());
            } else return mn;
        };
    }

    static NumberSource trim(Supplier<Number> value, Supplier<Number> min, Supplier<Number> max) {
        return () -> trim(value.get(), min, max).get();
    }

    @SafeVarargs
    static NumberSource max(Number n0, Supplier<Number> ... n){
        return () -> {
            double max = n0.doubleValue();
            for(var ni : n) {
                double d = ni.get().doubleValue();
                if(d > max) max = d;
            }
            return max;
        };
    }

    @SafeVarargs
    static NumberSource max(Supplier<Number> n0, Supplier<Number> ... n){
        return () -> max(n0.get(), n).get();
    }

    @SafeVarargs
    static NumberSource sum(Number n0, Supplier<Number> ... n){
        return () -> {
            float sum = n0.floatValue();
            for(var ni : n) {
                sum += ni.get().floatValue();
            }
            return sum;
        };
    }

    @SafeVarargs
    static NumberSource sum(Supplier<Number> n0, Supplier<Number> ... n){
        return () -> sum(n0.get(), n).get();
    }
}
