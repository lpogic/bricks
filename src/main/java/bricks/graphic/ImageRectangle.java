package bricks.graphic;

import bricks.image.Image;
import bricks.image.ImageManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;

public class ImageRectangle extends Guest<Host> implements Rectangle {

    Centroid body;
    Var<Image> image;

    public ImageRectangle(Host host) {
        super(host);
        image = Vars.get();
        body = new Centroid();
        body.width().let(Vars.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getImage(img).getWidth();
        }, image));
        body.height().let(Vars.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getImage(img).getHeight();
        }, image));
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

    public void image(Source<Image> i) {
        image.let(i);
    }

    public void setImage(Image i ) {
        image(() -> i);
    }

    public Image getImage() {
        return image.get();
    }
}
