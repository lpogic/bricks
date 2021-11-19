package bricks;

import bricks.var.num.NumSource;

public interface Located {
    NumSource x();
    NumSource y();

    static Located of(double x, double y) {
        return new Located() {
            @Override
            public NumSource x() {
                return () -> x;
            }

            @Override
            public NumSource y() {
                return () -> y;
            }
        };
    }
}
