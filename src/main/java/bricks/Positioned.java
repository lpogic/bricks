package bricks;

import bricks.var.Source;

public interface Positioned {
    Source<Point> position();
    default Point getPosition() {
        return position().get();
    }
}
