package bricks.slab;

import bricks.*;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.trait.Pull;
import bricks.trait.Trait;
import bricks.trait.Traits;

public class RectangleSlab extends Guest<Host> implements WithSlab, Printable {

    final Centroid body;
    final Trait<Color> color;

    public RectangleSlab(Host host) {
        super(host);
        body = new Centroid();
        color = Traits.set(Color.PURE_GREEN);
    }

    @Override
    public Slab getShape() {
        return body;
    }

    public Trait<Color> color() {
        return color;
    }
}
