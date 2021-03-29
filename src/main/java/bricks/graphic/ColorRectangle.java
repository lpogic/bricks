package bricks.graphic;


import bricks.Color;
import bricks.Point;
import bricks.XOrigin;
import bricks.YOrigin;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;

public class ColorRectangle extends Guest {

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

    public float getWidth() {
        return width.get().floatValue();
    }

    public ColorRectangle setWidth(Number width) {
        this.width.set(width);
        return this;
    }

    public Var<Number> width() {
        return width;
    }

    public float getHeight() {
        return height.get().floatValue();
    }

    public ColorRectangle setHeight(Number height) {
        this.height.set(height);
        return this;
    }

    public Var<Number> height() {
        return height;
    }

    public ColorRectangle setSize(Number width, Number height) {
        return setWidth(width).setHeight(height);
    }

    public ColorRectangle setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public ColorRectangle setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public Var<Point> position() {
        return position;
    }

    public Point getPosition() {
        return position.get();
    }

    public Color getColor() {
        return color.get();
    }

    public ColorRectangle setColor(Color color) {
        this.color.set(color);
        return this;
    }

    public Var<Color> color() {
        return color;
    }

    public XOrigin getXOrigin() {
        return xOrigin.get();
    }

    public ColorRectangle setXOrigin(XOrigin origin) {
        this.xOrigin.set(origin);
        return this;
    }

    public Var<XOrigin> xOrigin() {
        return xOrigin;
    }

    public YOrigin getYOrigin() {
        return yOrigin.get();
    }

    public ColorRectangle setYOrigin(YOrigin origin) {
        this.yOrigin.set(origin);
        return this;
    }

    public Var<YOrigin> yOrigin() {
        return yOrigin;
    }

    public ColorRectangle setOrigin(XOrigin xOrigin, YOrigin yOrigin) {
        return setXOrigin(xOrigin).setYOrigin(yOrigin);
    }

    public boolean contains(Source<Point> pointSource) {
        Point p = pointSource.get();
        return p != null && contains(pointSource.get());
    }

    public boolean contains(Point point) {
        Point position = getPosition();
        float width = getWidth();
        float hd = point.getX() - position.getX();
        if(Math.abs(hd) > width / 2) return false;
        float height = getHeight();
        float vd = point.getY() - position.getY();
        return Math.abs(vd) <= height / 2;
    }
}
