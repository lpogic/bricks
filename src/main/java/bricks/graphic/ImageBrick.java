package bricks.graphic;

import bricks.image.Image;
import bricks.image.ImageManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;

public class ImageBrick extends Guest<Host> implements WithRectangleBody, Printable {

    final Centroid body;
    final Var<Image> image;

    public ImageBrick(Host host) {
        super(host);
        image = Vars.get();
        body = new Centroid();
        body.width().let(Vars.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getLoaded(img).getWidth();
        }, image));
        body.height().let(Vars.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getLoaded(img).getHeight();
        }, image));
    }

    @Override
    public Rectangle getBody() {
        return body;
    }

    public Var<Image> image() {
        return image;
    }
}
