package bricks.slab;

import bricks.Located;
import bricks.Sized;
import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;
import bricks.trait.number.NumberSource;

public interface Shape extends Sized, Located {

    NumberSource left();
    NumberSource right();
    NumberSource top();
    NumberSource bottom();

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
            public NumberSource left() {
                return rect.left().plus(widthOffset / -2);
            }

            @Override
            public NumberSource right() {
                return rect.right().plus(widthOffset / 2);
            }

            @Override
            public NumberSource top() {
                return rect.top().plus(heightOffset / -2);
            }

            @Override
            public NumberSource bottom() {
                return Traits.sum(heightOffset / 2, rect.bottom());
            }

            @Override
            public NumberSource x() {
                return rect.x();
            }

            @Override
            public NumberSource y() {
                return rect.y();
            }

            @Override
            public NumberSource width() {
                return Traits.sum(widthOffset, rect.width());
            }

            @Override
            public NumberSource height() {
                return Traits.sum(heightOffset, rect.height());
            }
        };
    }
}
