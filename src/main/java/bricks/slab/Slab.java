package bricks.slab;

import bricks.Located;
import bricks.Sized;
import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;

import java.util.function.Supplier;

public interface Slab extends Shape {

    NumberTrait width();
    NumberTrait height();
    NumberTrait left();
    NumberTrait right();
    NumberTrait top();
    NumberTrait bottom();
    NumberTrait x();
    NumberTrait y();

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
        final NumberTrait x;
        final NumberTrait y;
        final NumberTrait width;
        final NumberTrait height;

        public Centroid() {
            x = Traits.num(400);
            y = Traits.num(300);
            width = Traits.num(100);
            height = Traits.num(100);
        }

        public NumberTrait width() {
            return width;
        }

        public NumberTrait height() {
            return height;
        }

        public NumberTrait left() {
            return new NumberTrait() {
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

        public NumberTrait right() {
            return new NumberTrait() {
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

        public NumberTrait top() {
            return new NumberTrait() {
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

        public NumberTrait bottom() {
            return new NumberTrait() {
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

        public NumberTrait x() {
            return x;
        }

        public NumberTrait y() {
            return y;
        }
    }
}
