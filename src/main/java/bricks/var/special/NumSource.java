package bricks.var.special;

import bricks.var.Source;
import bricks.var.impulse.DifferentialImpulse;
import bricks.var.impulse.Impulse;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface NumSource extends Source<Number> {
    default float getFloat() {
        return get().floatValue();
    }
    default int getInt() {
        return get().intValue();
    }
    default double getDouble() {
        return get().doubleValue();
    }
    default byte getByte() {
        return get().byteValue();
    }
    default long getLong() {
        return get().longValue();
    }
    default short getShort() {
        return get().shortValue();
    }

    default NumSource perFloat(Function<Float, Number> per) {
        return () -> per.apply(getFloat());
    }
}
