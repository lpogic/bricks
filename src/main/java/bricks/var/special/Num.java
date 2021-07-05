package bricks.var.special;

import bricks.var.Preserve;
import bricks.var.Source;
import bricks.var.Target;
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

    static float max(float f0, float ... f){
        float max = f0;
        for(int i = f.length - 1;i >= 0;--i) {
            if(f[i] > max) max = f[i];
        }
        return max;
    }

    static NumSource sum(Source<Number> n1, Source<Number> n2) {
        return () -> n1.get().floatValue() + n2.get().floatValue();
    }

    static NumSource sum(Source<Number> n1, Number n2) {
        return () -> n1.get().floatValue() + n2.floatValue();
    }
}
