package bricks.slab;

import bricks.trade.Host;
import bricks.var.num.NumPull;

public class TextRectSlab extends TextSlab implements Slab {
    NumPull width;

    public TextRectSlab(Host host) {
        super(host);
    }

    @Override
    public NumPull width() {
        return width;
    }
}
