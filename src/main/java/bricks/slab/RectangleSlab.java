package bricks.slab;

import bricks.*;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Pull;
import bricks.var.Var;

public class RectangleSlab extends Guest<Host> implements WithSlab, Printable {

    final Centroid body;
    final Pull<Color> color;

    public RectangleSlab(Host host) {
        super(host);
        body = new Centroid();
        color = Var.pull(Color.PURE_GREEN);
    }

    @Override
    public Slab getShape() {
        return body;
    }

    public Pull<Color> color() {
        return color;
    }
}
