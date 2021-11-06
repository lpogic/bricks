package bricks;

import bricks.var.Var;
import bricks.var.special.NumPull;

public interface Location extends Located {

    NumPull x();
    NumPull y();

    default void aim(Located c) {
        x().let(c.x());
        y().let(c.y());
    }

    class Cartesian implements Location {
        NumPull x;
        NumPull y;

        public Cartesian() {
            x = Var.num();
            y = Var.num();
        }

        public Cartesian(Number x, Number y) {
            this.x = Var.num(x);
            this.y = Var.num(y);
        }

        @Override
        public NumPull x() {
            return x;
        }

        @Override
        public NumPull y() {
            return y;
        }

        @Override
        public String toString() {
            return "x[ " + x +
                    " ] y[ " + y +
                    " ]";
        }
    }
}
