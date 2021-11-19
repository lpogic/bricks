package bricks.slab;

import bricks.Located;
import bricks.var.num.NumSource;

public interface WithShape extends Shape {
    Shape getShape();

    @Override
    default NumSource width() {
        return getShape().width();
    }
    @Override
    default NumSource height() {
        return getShape().height();
    }
    @Override
    default NumSource left() {
        return getShape().left();
    }
    @Override
    default NumSource right() {
        return getShape().right();
    }
    @Override
    default NumSource top() {
        return getShape().top();
    }
    @Override
    default NumSource bottom() {
        return getShape().bottom();
    }
    @Override
    default NumSource x() {
        return getShape().x();
    }
    @Override
    default NumSource y() {
        return getShape().y();
    }

    @Override
    default boolean contains(Located p) {
        return getShape().contains(p);
    }
}
