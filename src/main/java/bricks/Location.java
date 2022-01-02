package bricks;

import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;

public interface Location extends Located {

    NumberTrait x();
    NumberTrait y();

    default void aim(Located c) {
        x().let(c.x());
        y().let(c.y());
    }

    class Cartesian implements Location {
        NumberTrait x;
        NumberTrait y;

        public Cartesian() {
            x = Traits.num();
            y = Traits.num();
        }

        public Cartesian(Number x, Number y) {
            this.x = Traits.num(x);
            this.y = Traits.num(y);
        }

        @Override
        public NumberTrait x() {
            return x;
        }

        @Override
        public NumberTrait y() {
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
