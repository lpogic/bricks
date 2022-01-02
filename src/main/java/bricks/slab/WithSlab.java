package bricks.slab;

import bricks.trait.number.NumberTrait;

public interface WithSlab extends Slab, WithShape {
    Slab getShape();

    @Override
    default NumberTrait width() {
        return getShape().width();
    }
    @Override
    default NumberTrait height() {
        return getShape().height();
    }
    @Override
    default NumberTrait left() {
        return getShape().left();
    }
    @Override
    default NumberTrait right() {
        return getShape().right();
    }
    @Override
    default NumberTrait top() {
        return getShape().top();
    }
    @Override
    default NumberTrait bottom() {
        return getShape().bottom();
    }
    @Override
    default NumberTrait x() {
        return getShape().x();
    }
    @Override
    default NumberTrait y() {
        return getShape().y();
    }
}
