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
                return Num.sum(rect.left(), widthOffset / -2);
            }

            @Override
            public NumSource right() {
                return Num.sum(rect.right(), widthOffset / 2);
            }

            @Override
            public NumSource top() {
                return Num.sum(rect.top(), heightOffset / -2);
            }

            @Override
            public NumSource bottom() {
                return Num.sum(rect.bottom(), heightOffset / 2);
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
                return Num.sum(rect.width(), widthOffset);
            }

            @Override
            public NumSource height() {
                return Num.sum(rect.height(), heightOffset);
            }
        };
    }
}
