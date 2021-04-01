package bricks.graphic;

import bricks.*;
import bricks.image.Image;
import bricks.image.ImageManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;

public class ImageRectangle extends Guest<Host> implements Rectangular {

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

    public Var<Number> width() {
        return width;
    }

    public ImageRectangle setWidth(Number width) {
        this.width.set(width);
        return this;
    }

    public Var<Number> height() {
        return height;
    }

    public ImageRectangle setHeight(Number height) {
        this.height.set(height);
        return this;
    }

    public Var<Point> position() {
        return position;
    }

    public ImageRectangle setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public ImageRectangle setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public Var<XOrigin> xOrigin() {
        return xOrigin;
    }

    public ImageRectangle setXOrigin(XOrigin xOrigin) {
        this.xOrigin.set(xOrigin);
        return this;
    }

    public Var<YOrigin> yOrigin() {
        return yOrigin;
    }

    public ImageRectangle setYOrigin(YOrigin yOrigin) {
        this.yOrigin.set(yOrigin);
        return this;
    }

    public ImageRectangle setOrigin(XOrigin xOrigin, YOrigin yOrigin) {
        return setXOrigin(xOrigin).setYOrigin(yOrigin);
    }

    public Var<Image> image() {
        return image;
    }

    public Image getImage() {
        return image.get();
    }

    public ImageRectangle setImage(Image image) {
        this.image.set(image);
        return this;
    }
}
