package bricks.slab;

import bricks.Color;
import bricks.Location;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Pull;
import bricks.var.Var;
import bricks.var.num.NumPull;

public class LineSlab extends Guest<Host> implements Printable {

    final NumPull thick;
    final Location beginPosition;
    final Location endPosition;
    final Pull<Color> color;

    public LineSlab(Host host) {
        super(host);
        thick = Var.num(10);
        beginPosition = new Location.Cartesian();
        beginPosition.x().set(100);
        beginPosition.y().set(100);
        endPosition = new Location.Cartesian();
        endPosition.x().set(200);
        endPosition.y().set(500);
        color = Var.pull(Color.PURE_GREEN);
    }

    public NumPull thick() {
        return thick;
    }

    public Location begin() {
        return beginPosition;
    }

    public Location end() {
        return endPosition;
    }

    public Pull<Color> color() {
        return color;
    }
}
