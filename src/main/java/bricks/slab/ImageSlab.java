package bricks.slab;

import bricks.image.Image;
import bricks.image.ImageManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.trait.Pull;
import bricks.trait.Trait;
import bricks.trait.Traits;

public class ImageSlab extends Guest<Host> implements WithSlab, Printable {

    final Centroid body;
    final Trait<Image> image;

    public ImageSlab(Host host) {
        super(host);
        image = Traits.let();
        body = new Centroid();
        body.width().let(Traits.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getLoaded(img).getWidth();
        }, image));
        body.height().let(Traits.let(() -> {
            Image img = image.get();
            if(img == null) return 0;
            return order(ImageManager.class).getLoaded(img).getHeight();
        }, image));
    }

    @Override
    public Slab getShape() {
        return body;
    }

    public Trait<Image> image() {
        return image;
    }
}
