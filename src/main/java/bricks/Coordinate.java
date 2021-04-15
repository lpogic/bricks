package bricks;

import bricks.var.Vars;
import bricks.var.special.Num;

public interface Coordinate extends Coordinated {
    Num x();
    Num y();

    default void aim(Coordinated c) {
        x().let(c.x());
        y().let(c.y());
    }

    class Cartesian implements Coordinate {
        Num x;
        Num y;

        public Cartesian() {
            x = Vars.num();
            y = Vars.num();
        }

        @Override
        public Num x() {
            return x;
        }

        @Override
        public Num y() {
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
