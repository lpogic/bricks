package bricks.graphic;

import bricks.Color;
import bricks.Point;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;

public class ColorLine extends Guest<Host> {

    Var<Number> thick;
    Var<Point> beginPosition;
    Var<Point> endPosition;
    Var<Color> color;

    public ColorLine(Host host) {
        super(host);
        thick = Vars.set(10);
        beginPosition = Vars.set(new Point(100, 100));
        endPosition = Vars.set(new Point(200, 500));
        color = Vars.set(Color.PURE_GREEN);
    }

    public Var<Number> thick() {
        return thick;
    }

    public float getThick() {
        return thick.get().floatValue();
    }

    public ColorLine setThick(Number thick) {
        this.thick.set(thick);
        return this;
    }

    public Point getBeginPosition() {
        return beginPosition.get();
    }


    public Var<Point> beginPosition() {
        return beginPosition;
    }

    public ColorLine setBeginPosition(Point position) {
        this.beginPosition.set(position);
        return this;
    }

    public ColorLine setBeginPosition(Number x, Number y) {
        this.beginPosition.set(new Point(x, y));
        return this;
    }

    public Var<Point> endPosition() {
        return endPosition;
    }

    public Point getEndPosition() {
        return endPosition.get();
    }

    public ColorLine setEndPosition(Point position) {
        this.endPosition.set(position);
        return this;
    }

    public ColorLine setEndPosition(Number x, Number y) {
        this.endPosition.set(new Point(x, y));
        return this;
    }

    public Var<Color> color() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public ColorLine setColor(Color color) {
        this.color.set(color);
        return this;
    }
}
