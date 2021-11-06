package bricks.slab;

import bricks.image.Image;
import bricks.image.ImageManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Pull;
import bricks.var.Var;

public class ImageSlab extends Guest<Host> implements WithSlab, Printable {

    final Centroid body;
    final Pull<Image> image;

    public ImageSlab(Host host) {
        super(host);
        image = Var.let();
        body = new Centroid();
        body.width().let(Var.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getLoaded(img).getWidth();
        }, image));
        body.height().let(Var.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getLoaded(img).getHeight();
        }, image));
    }

    @Override
    public Slab getShape() {
        return body;
    }

    public Pull<Image> image() {
        return image;
    }
}
