package bricks.slab;

import bricks.trade.Host;
import bricks.trait.number.NumberTrait;

public class TextRectSlab extends TextSlab implements Slab {
    NumberTrait width;

    public TextRectSlab(Host host) {
        super(host);
    }

    @Override
    public NumberTrait width() {
        return width;
    }
}
