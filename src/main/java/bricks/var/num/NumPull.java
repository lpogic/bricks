package bricks.var.num;

import bricks.var.ReactivePull;
import bricks.var.Pull;
import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public interface NumPull extends NumSource, Pull<Number> {

    void let(Supplier<Number> s);
    default void let(Supplier<Number> s, Supplier<?> ... roots) {
        let(s, Suite.set((Object[]) roots));
    }
    default void let(Supplier<Number> s, Subject $roots) {
        let(new ReactivePull<>(s, $roots));
    }
    default void set(Number t) {
        let(() -> t);
    }

    static int trim(int value, int min, int max) {
        return value > min ? value < max ? value: max : min;
    }

    static float trim(float value, float min, float max) {
        return value > min ? value < max ? value: max : min;
    }

    static double trim(double value, double min, double max) {
        return value > min ? value < max ? value: max : min;
    }

    static NumSource trim(Number value, Supplier<Number> min, Supplier<Number> max) {
        return () -> {
            var v = value.doubleValue();
            var mn = min.get().doubleValue();
            if(v > mn) {
                return Math.min(v, max.get().doubleValue());
            } else return mn;
        };
    }

    static NumSource trim(Supplier<Number> value, Supplier<Number> min, Supplier<Number> max) {
        return () -> trim(value.get(), min, max).get();
    }

    @SafeVarargs
    static NumSource max(Number n0, Supplier<Number> ... n){
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
    static NumSource max(Supplier<Number> n0, Supplier<Number> ... n){
        return () -> max(n0.get(), n).get();
    }

    @SafeVarargs
    static NumSource sum(Number n0, Supplier<Number> ... n){
        return () -> {
            float sum = n0.floatValue();
            for(var ni : n) {
                sum += ni.get().floatValue();
            }
            return sum;
        };
    }

    @SafeVarargs
    static NumSource sum(Supplier<Number> n0, Supplier<Number> ... n){
        return () -> sum(n0.get(), n).get();
    }
}
