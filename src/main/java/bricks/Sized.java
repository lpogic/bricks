package bricks;

import bricks.var.Source;

public interface Sized {
    Source<Number> width();
    Source<Number> height();

    default float getWidth() {
        return width().get().floatValue();
    }

    default float getHeight() {
        return height().get().floatValue();
    }
}
