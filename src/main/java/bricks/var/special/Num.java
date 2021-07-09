package bricks.var.special;

import bricks.var.Preserve;
import bricks.var.Var;
import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.Supplier;

public interface Num extends NumSource, Var<Number> {

    void let(Supplier<Number> s);
    default void let(Supplier<Number> s, Supplier<?> ... roots) {
        let(s, Suite.set((Object[]) roots));
    }
    default void let(Supplier<Number> s, Subject $roots) {
        let(new Preserve<>(s, $roots));
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

    @SafeVarargs
    static NumSource max(Number n0, Supplier<Number> ... n){
        return () -> {
            float max = n0.floatValue();
            for(var ni : n) {
                float f = ni.get().floatValue();
                if(f > max) max = f;
            }
            return max;
        };
    }

    @SafeVarargs
    static NumSource max(Supplier<Number> n0, Supplier<Number> ... n){
        return () -> {
            float max = n0.get().floatValue();
            for(var ni : n) {
                float f = ni.get().floatValue();
                if(f > max) max = f;
            }
            return max;
        };
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
        return () -> {
            float sum = n0.get().floatValue();
            for(var ni : n) {
                sum += ni.get().floatValue();
            }
            return sum;
        };
    }
}
