package bricks;

import bricks.var.Source;

public interface Related {
    Source<XOrigin> xOrigin();
    Source<YOrigin> yOrigin();

    default XOrigin getXOrigin() {
        return xOrigin().get();
    }

    default YOrigin getYOrigin() {
        return yOrigin().get();
    }
}
