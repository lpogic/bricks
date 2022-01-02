package bricks.trait.number;

import bricks.trait.Source;
import java.util.function.Function;

@FunctionalInterface
public interface NumberSource extends Source<Number> {

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

    default NumberSource perFloat(Function<Float, Number> per) {
        return () -> per.apply(getFloat());
    }

    default NumberSource scale(Number scale) {
        return () -> getFloat() * scale.floatValue();
    }

    default NumberSource plus(Number add) {
        return () -> getFloat() + add.floatValue();
    }
}
