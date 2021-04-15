package bricks.graphic;

import bricks.Color;
import bricks.Coordinate;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class ColorLine extends Guest<Host> {

    Num thick;
    Coordinate beginPosition;
    Coordinate endPosition;
    Var<Color> color;

    public ColorLine(Host host) {
        super(host);
        thick = Vars.num(10);
        beginPosition = new Coordinate.Cartesian();
        beginPosition.x().set(100);
        beginPosition.y().set(100);
        endPosition = new Coordinate.Cartesian();
        endPosition.x().set(200);
        endPosition.y().set(500);
        color = Vars.set(Color.PURE_GREEN);
    }

    public Num thick() {
        return thick;
    }

    public Coordinate begin() {
        return beginPosition;
    }

    public Coordinate end() {
        return endPosition;
    }

    public Var<Color> color() {
        return color;
    }
}
