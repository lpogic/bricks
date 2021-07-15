package bricks;

import bricks.trade.Contract;
import bricks.var.Vars;
import bricks.var.special.Num;

public interface Location extends Located {

    Num x();
    Num y();

    default void aim(Located c) {
        x().let(c.x());
        y().let(c.y());
    }

    class Cartesian implements Location {
        Num x;
        Num y;

        public Cartesian() {
            x = Vars.num();
            y = Vars.num();
        }

        public Cartesian(Number x, Number y) {
            this.x = Vars.num(x);
            this.y = Vars.num(y);
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
