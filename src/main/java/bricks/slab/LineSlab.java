package bricks.slab;

import bricks.Color;
import bricks.Location;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.trait.Pull;
import bricks.trait.Trait;
import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;

public class LineSlab extends Guest<Host> implements Printable {

    final NumberTrait thick;
    final Location beginPosition;
    final Location endPosition;
    final Trait<Color> color;

    public LineSlab(Host host) {
        super(host);
        thick = Traits.num(10);
        beginPosition = new Location.Cartesian();
        beginPosition.x().set(100);
        beginPosition.y().set(100);
        endPosition = new Location.Cartesian();
        endPosition.x().set(200);
        endPosition.y().set(500);
        color = Traits.set(Color.PURE_GREEN);
    }

    public NumberTrait thick() {
        return thick;
    }

    public Location begin() {
        return beginPosition;
    }

    public Location end() {
        return endPosition;
    }

    public Trait<Color> color() {
        return color;
    }
}
