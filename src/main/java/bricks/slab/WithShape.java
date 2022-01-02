package bricks.slab;

import bricks.Located;
import bricks.trait.number.NumberSource;

public interface WithShape extends Shape {
    Shape getShape();

    @Override
    default NumberSource width() {
        return getShape().width();
    }
    @Override
    default NumberSource height() {
        return getShape().height();
    }
    @Override
    default NumberSource left() {
        return getShape().left();
    }
    @Override
    default NumberSource right() {
        return getShape().right();
    }
    @Override
    default NumberSource top() {
        return getShape().top();
    }
    @Override
    default NumberSource bottom() {
        return getShape().bottom();
    }
    @Override
    default NumberSource x() {
        return getShape().x();
    }
    @Override
    default NumberSource y() {
        return getShape().y();
    }

    @Override
    default boolean contains(Located p) {
        return getShape().contains(p);
    }
}
