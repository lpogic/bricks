package bricks.graphic;

import bricks.*;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class ColorRectangle extends Guest<Host> implements Rectangle {

    final Centroid body;
    final Var<Color> color;

    public ColorRectangle(Host host) {
        super(host);
        body = new Centroid();
        color = Vars.set(Color.PURE_GREEN);
    }

    @Override
    public Num width() {
        return body.width();
    }

    @Override
    public Num height() {
        return body.height();
    }

    @Override
    public Num left() {
        return body.left();
    }

    @Override
    public Num right() {
        return body.right();
    }

    @Override
    public Num top() {
        return body.top();
    }

    @Override
    public Num bottom() {
        return body.bottom();
    }

    @Override
    public Num x() {
        return body.x();
    }

    @Override
    public Num y() {
        return body.y();
    }

    public Var<Color> color() {
        return color;
    }

    public static abstract class Sketch extends ColorRectangle implements AbstractSketch<ColorRectangle> {

        public Sketch() {
            super(null);
        }
    }
}
