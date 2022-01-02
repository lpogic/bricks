package bricks;

import bricks.trait.number.NumberSource;

public interface Located {
    NumberSource x();
    NumberSource y();

    static Located of(double x, double y) {
        return new Located() {
            @Override
            public NumberSource x() {
                return () -> x;
            }

            @Override
            public NumberSource y() {
                return () -> y;
            }
        };
    }
}
