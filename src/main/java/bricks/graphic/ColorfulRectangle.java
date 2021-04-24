package bricks.graphic;

import bricks.Color;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class ColorfulRectangle extends Guest<Host> implements Rectangle {

    final Centroid body;
    final Var<Color> colorLeftTop;
    final Var<Color> colorLeftBottom;
    final Var<Color> colorRightTop;
    final Var<Color> colorRightBottom;

    public ColorfulRectangle(Host host) {
        super(host);
        body = new Centroid();
        colorLeftTop = Vars.set(Color.PURE_GREEN);
        colorLeftBottom = Vars.set(Color.PURE_GREEN);
        colorRightTop = Vars.set(Color.PURE_GREEN);
        colorRightBottom = Vars.set(Color.PURE_GREEN);
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

    public Var<Color> colorLeftTop() {
        return colorLeftTop;
    }

    public Var<Color> colorLeftBottom() {
        return colorLeftBottom;
    }

    public Var<Color> colorRightTop() {
        return colorRightTop;
    }

    public Var<Color> colorRightBottom() {
        return colorRightBottom;
    }
}
