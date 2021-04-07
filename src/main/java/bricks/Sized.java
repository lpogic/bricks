package bricks;

import bricks.var.Source;
import bricks.var.Vars;

public interface Sized {
    Source<Number> width();
    Source<Number> height();

    default float getWidth() {
        return width().get().floatValue();
    }

    default float getHeight() {
        return height().get().floatValue();
    }

    default Source<Point> center() {
        return Vars.let(() -> new Point(getWidth() / 2f, getHeight() / 2f), width(), height());
    }
}
