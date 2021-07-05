package bricks.graphic;

import bricks.*;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class RectangleBrick extends Guest<Host> implements WithRectangleBody, Printable {

    final Centroid body;
    final Var<Color> color;

    public RectangleBrick(Host host) {
        super(host);
        body = new Centroid();
        color = Vars.set(Color.PURE_GREEN);
    }

    @Override
    public Rectangle getBody() {
        return body;
    }

    public Var<Color> color() {
        return color;
    }
}
