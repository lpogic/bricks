package bricks;

import bricks.var.special.NumSource;

public interface Coordinated {
    NumSource x();
    NumSource y();

    static Coordinated of(double x, double y) {
        return new Coordinated() {
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
