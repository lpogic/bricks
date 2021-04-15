package bricks.graphic;

import bricks.Coordinated;
import bricks.Sized;
import bricks.var.Vars;
import bricks.var.special.Num;

import java.util.function.Supplier;

public interface Rectangle extends Rectangular {

    Num width();
    Num height();
    Num left();
    Num right();
    Num top();
    Num bottom();
    Num x();
    Num y();

    default void aim(Coordinated p) {
        x().let(p.x());
        y().let(p.y());
    }

    default void adjust(Sized s) {
        width().let(s.width());
        height().let(s.height());
    }

    default void fill(Rectangular r) {
        aim(r);
        adjust(r);
    }

    class Centroid implements Rectangle {
        final Num x;
        final Num y;
        final Num width;
        final Num height;

        public Centroid() {
            x = Vars.num(400);
            y = Vars.num(300);
            width = Vars.num(100);
            height = Vars.num(100);
        }

        public Num width() {
            return width;
        }

        public Num height() {
            return height;
        }

        public Num left() {
            return new Num() {
                @Override
                public void let(Supplier<Number> s) {
                    x.let(() -> s.get().floatValue() + width.getFloat() / 2);
                }

                @Override
                public Number get() {
                    return x.getFloat() - width.getFloat() / 2;
                }
            };
        }

        public Num right() {
            return new Num() {
                @Override
                public void let(Supplier<Number> s) {
                    x.let(() -> s.get().floatValue() - width.getFloat() / 2);
                }

                @Override
                public Number get() {
                    return x.getFloat() + width.getFloat() / 2;
                }
            };
        }

        public Num top() {
            return new Num() {
                @Override
                public void let(Supplier<Number> s) {
                    y.let(() -> s.get().floatValue() + height.getFloat() / 2);
                }

                @Override
                public Number get() {
                    return y.getFloat() - height.getFloat() / 2;
                }
            };
        }

        public Num bottom() {
            return new Num() {
                @Override
                public void let(Supplier<Number> s) {
                    y.let(() -> s.get().floatValue() - height.getFloat() / 2);
                }

                @Override
                public Number get() {
                    return y.getFloat() + height.getFloat() / 2;
                }
            };
        }

        public Num x() {
            return x;
        }

        public Num y() {
            return y;
        }
    }
}
