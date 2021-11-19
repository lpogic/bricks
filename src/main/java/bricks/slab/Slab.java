package bricks.slab;

import bricks.Located;
import bricks.Sized;
import bricks.var.Var;
import bricks.var.num.NumPull;

import java.util.function.Supplier;

public interface Slab extends Shape {

    NumPull width();
    NumPull height();
    NumPull left();
    NumPull right();
    NumPull top();
    NumPull bottom();
    NumPull x();
    NumPull y();

    default void aim(Located p) {
        x().let(p.x());
        y().let(p.y());
    }

    default void adjust(Sized s) {
        width().let(s.width());
        height().let(s.height());
    }

    default void fill(Shape r) {
        aim(r);
        adjust(r);
    }

    class Centroid implements Slab {
        final NumPull x;
        final NumPull y;
        final NumPull width;
        final NumPull height;

        public Centroid() {
            x = Var.num(400);
            y = Var.num(300);
            width = Var.num(100);
            height = Var.num(100);
        }

        public NumPull width() {
            return width;
        }

        public NumPull height() {
            return height;
        }

        public NumPull left() {
            return new NumPull() {
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

        public NumPull right() {
            return new NumPull() {
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

        public NumPull top() {
            return new NumPull() {
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

        public NumPull bottom() {
            return new NumPull() {
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

        public NumPull x() {
            return x;
        }

        public NumPull y() {
            return y;
        }
    }
}
