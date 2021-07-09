package bricks.image;


import suite.suite.Subject;

import static suite.suite.$uite.$;

public class ImageManager {

    Subject $images = $();

    public LoadedImage getLoaded(Image image) {
        var $i = $images.in(image).set();
        if($i.absent()) {
            $i.set(new LoadedImage(image));
        }
        return $i.asExpected();
    }
}
