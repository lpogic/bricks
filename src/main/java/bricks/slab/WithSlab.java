package bricks.slab;

import bricks.var.num.NumPull;

public interface WithSlab extends Slab, WithShape {
    Slab getShape();

    @Override
    default NumPull width() {
        return getShape().width();
    }
    @Override
    default NumPull height() {
        return getShape().height();
    }
    @Override
    default NumPull left() {
        return getShape().left();
    }
    @Override
    default NumPull right() {
        return getShape().right();
    }
    @Override
    default NumPull top() {
        return getShape().top();
    }
    @Override
    default NumPull bottom() {
        return getShape().bottom();
    }
    @Override
    default NumPull x() {
        return getShape().x();
    }
    @Override
    default NumPull y() {
        return getShape().y();
    }
}
