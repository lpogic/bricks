package bricks.slab;

import bricks.Located;
import bricks.Sized;
import bricks.var.special.NumPull;
import bricks.var.special.NumSource;

public interface Shape extends Sized, Located {

    NumSource left();
    NumSource right();
    NumSource top();
    NumSource bottom();

    default boolean contains(Located p) {
        Number x = p.x().get();
        if(x == null) return false;
        Number y = p.y().get();
        if(y == null) return false;
        return left().getFloat() < x.floatValue() &&
                right().getFloat() > x.floatValue() &&
                top().getFloat() < y.floatValue() &&
                bottom().getFloat() > y.floatValue();
    }

    static Shape relative(Shape rect, float widthOffset, float heightOffset) {
        return new Shape() {
            @Override
            public NumSource left() {
                return rect.left().plus(widthOffset / -2);
            }

            @Override
            public NumSource right() {
                return rect.right().plus(widthOffset / 2);
            }

            @Override
            public NumSource top() {
                return rect.top().plus(heightOffset / -2);
            }

            @Override
            public NumSource bottom() {
                return NumPull.sum(heightOffset / 2, rect.bottom());
            }

            @Override
            public NumSource x() {
                return rect.x();
            }

            @Override
            public NumSource y() {
                return rect.y();
            }

            @Override
            public NumSource width() {
                return NumPull.sum(widthOffset, rect.width());
            }

            @Override
            public NumSource height() {
                return NumPull.sum(heightOffset, rect.height());
            }
        };
    }
}
