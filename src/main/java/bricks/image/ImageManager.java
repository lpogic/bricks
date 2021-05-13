package bricks.image;


import suite.suite.Subject;

import static suite.suite.$.set$;

public class ImageManager {

    Subject $images = set$();

    public LoadedImage getImage(Image image) {
        var $i = $images.in(image).set();
        if($i.absent()) {
            $i.set(new LoadedImage(image));
        }
        return $i.asExpected();
    }
}
