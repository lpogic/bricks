package bricks.var.special;

import bricks.var.Source;
import java.util.function.Function;

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

    default NumSource scale(Number scale) {
        return () -> getFloat() * scale.floatValue();
    }

    default NumSource plus(Number add) {
        return () -> getFloat() + add.floatValue();
    }
}
