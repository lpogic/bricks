package bricks.graphic;


import bricks.*;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;

public class ColorRectangle extends Guest<Host> implements Rectangular {

    Var<Number> width;
    Var<Number> height;
    Var<Point> position;
    Var<Color> color;
    Var<XOrigin> xOrigin;
    Var<YOrigin> yOrigin;

    public ColorRectangle(Host host) {
        super(host);
        width = Vars.set(100);
        height = Vars.set(100);
        position = Vars.set(new Point(400, 300));
        color = Vars.set(Color.PURE_GREEN);
        xOrigin = Vars.set(XOrigin.CENTER);
        yOrigin = Vars.set(YOrigin.CENTER);
    }

    public Var<Number> width() {
        return width;
    }

    public ColorRectangle setWidth(Number width) {
        this.width.set(width);
        return this;
    }

    public Var<Number> height() {
        return height;
    }

    public ColorRectangle setHeight(Number height) {
        this.height.set(height);
        return this;
    }

    public ColorRectangle setSize(Number width, Number height) {
        return setWidth(width).setHeight(height);
    }

    public Var<Point> position() {
        return position;
    }

    public ColorRectangle setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public ColorRectangle setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public Var<Color> color() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public ColorRectangle setColor(Color color) {
        this.color.set(color);
        return this;
    }

    public Var<XOrigin> xOrigin() {
        return xOrigin;
    }

    public ColorRectangle setXOrigin(XOrigin origin) {
        this.xOrigin.set(origin);
        return this;
    }

    public Var<YOrigin> yOrigin() {
        return yOrigin;
    }

    public ColorRectangle setYOrigin(YOrigin origin) {
        this.yOrigin.set(origin);
        return this;
    }

    public ColorRectangle setOrigin(XOrigin xOrigin, YOrigin yOrigin) {
        return setXOrigin(xOrigin).setYOrigin(yOrigin);
    }
}
