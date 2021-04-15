package bricks.graphic;

import bricks.Coordinated;
import bricks.Sized;
import bricks.var.special.NumSource;

public interface Rectangular extends Sized, Coordinated {

    NumSource left();
    NumSource right();
    NumSource top();
    NumSource bottom();

    default boolean contains(Coordinated p) {
        Number x = p.x().get();
        if(x == null) return false;
        Number y = p.y().get();
        if(y == null) return false;
        return left().getFloat() < x.floatValue() &&
                right().getFloat() > x.floatValue() &&
                top().getFloat() < y.floatValue() &&
                bottom().getFloat() > y.floatValue();
    }
}
