package bricks.graphic;

import bricks.Color;
import bricks.Location;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class LineBrick extends Guest<Host> implements Printable {

    final Num thick;
    final Location beginPosition;
    final Location endPosition;
    final Var<Color> color;

    public LineBrick(Host host) {
        super(host);
        thick = Vars.num(10);
        beginPosition = new Location.Cartesian();
        beginPosition.x().set(100);
        beginPosition.y().set(100);
        endPosition = new Location.Cartesian();
        endPosition.x().set(200);
        endPosition.y().set(500);
        color = Vars.set(Color.PURE_GREEN);
    }

    public Num thick() {
        return thick;
    }

    public Location begin() {
        return beginPosition;
    }

    public Location end() {
        return endPosition;
    }

    public Var<Color> color() {
        return color;
    }
}
