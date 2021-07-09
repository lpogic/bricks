package bricks.graphic;

import bricks.Located;
import bricks.Sized;
import bricks.var.special.Num;
import bricks.var.special.NumSource;

public interface Rectangular extends Sized, Located {

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

    static Rectangular relative(Rectangular rect, float widthOffset, float heightOffset) {
        return new Rectangular() {
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
                return Num.sum(heightOffset / 2, rect.bottom());
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
                return Num.sum(widthOffset, rect.width());
            }

            @Override
            public NumSource height() {
                return Num.sum(heightOffset, rect.height());
            }
        };
    }
}
