package bricks.graphic;

import bricks.Point;
import bricks.XOrigin;
import bricks.YOrigin;
import bricks.image.Image;
import bricks.image.ImageManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;

public class ImageRectangle extends Guest<Host> {

    Var<Number> width;
    Var<Number> height;
    Var<Point> position;
    Var<XOrigin> xOrigin;
    Var<YOrigin> yOrigin;
    Var<Image> image;

    public ImageRectangle(Host host) {
        super(host);
        image = Vars.get();
        width = Vars.get(Number.class);
        width.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getImage(img).getWidth();
        }, image);
        height = Vars.get(Number.class);
        height.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getImage(img).getHeight();
        }, image);
        position = Vars.set(new Point(400, 300));
        xOrigin = Vars.set(XOrigin.CENTER);
        yOrigin = Vars.set(YOrigin.CENTER);
    }

    public float getWidth() {
        return width.get().floatValue();
    }

    public ImageRectangle setWidth(Number width) {
        this.width.set(width);
        return this;
    }

    public Var<Number> width() {
        return width;
    }

    public float getHeight() {
        return height.get().floatValue();
    }

    public ImageRectangle setHeight(Number height) {
        this.height.set(height);
        return this;
    }

    public Var<Number> height() {
        return height;
    }

    public ImageRectangle setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public Point getPosition() {
        return position.get();
    }

    public ImageRectangle setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public Var<Point> position() {
        return position;
    }

    public XOrigin getXOrigin() {
        return xOrigin.get();
    }

    public ImageRectangle setXOrigin(XOrigin xOrigin) {
        this.xOrigin.set(xOrigin);
        return this;
    }

    public Var<XOrigin> xOrigin() {
        return xOrigin;
    }

    public YOrigin getYOrigin() {
        return yOrigin.get();
    }

    public ImageRectangle setYOrigin(YOrigin yOrigin) {
        this.yOrigin.set(yOrigin);
        return this;
    }

    public Var<YOrigin> yOrigin() {
        return yOrigin;
    }

    public ImageRectangle setOrigin(XOrigin xOrigin, YOrigin yOrigin) {
        return setXOrigin(xOrigin).setYOrigin(yOrigin);
    }

    public Image getImage() {
        return image.get();
    }

    public ImageRectangle setImage(Image image) {
        this.image.set(image);
        return this;
    }

    public Var<Image> image() {
        return image;
    }

    public boolean contains(Source<Point> pointSource) {
        Point p = pointSource.get();
        return p != null && contains(pointSource.get());
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
