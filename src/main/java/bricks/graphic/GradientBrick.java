package bricks.graphic;

import bricks.Color;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class GradientBrick extends Guest<Host> implements WithRectangleBody, Printable {

    final Centroid body;
    final Var<Color> colorLeftTop;
    final Var<Color> colorLeftBottom;
    final Var<Color> colorRightTop;
    final Var<Color> colorRightBottom;

    public GradientBrick(Host host) {
        super(host);
        body = new Centroid();
        colorLeftTop = Vars.set(Color.PURE_GREEN);
        colorLeftBottom = Vars.set(Color.PURE_GREEN);
        colorRightTop = Vars.set(Color.PURE_GREEN);
        colorRightBottom = Vars.set(Color.PURE_GREEN);
    }

    @Override
    public Rectangle getBody() {
        return body;
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
