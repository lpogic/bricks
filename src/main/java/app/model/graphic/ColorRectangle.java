package app.model.graphic;


import app.model.Color;
import app.model.Point;
import app.model.XOrigin;
import app.model.YOrigin;
import app.model.trade.Guest;
import app.model.trade.Host;
import app.model.var.Source;
import app.model.var.Var;
import app.model.var.Vars;

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

    public ColorRectangle setWidth(Number width) {
        this.width.set(width);
        return this;
    }

    public Var<Number> width() {
        return width;
    }

    public ColorRectangle setHeight(Number height) {
        this.height.set(height);
        return this;
    }

    public Var<Number> height() {
        return height;
    }

    public ColorRectangle setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public ColorRectangle setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public float getWidth() {
        return width.get().floatValue();
    }

    public float getHeight() {
        return height.get().floatValue();
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

    public boolean contains(Source<Point> pointSource) {
        return pointSource.present() && contains(pointSource.get());
    }

    public boolean contains(Point point) {
        Point position = getPosition();
        float width = getWidth();
        float hd = point.getY() - position.getY();
        if(Math.abs(hd) > width / 2) return false;
        float height = getHeight();
        float vd = point.getX() - position.getX();
        return Math.abs(vd) <= height / 2;
    }
}
