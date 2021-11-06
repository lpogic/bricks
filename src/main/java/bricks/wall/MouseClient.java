package bricks.wall;

import bricks.Located;

public interface MouseClient {
    enum CursorOver {DIRECT, INDIRECT, NO}
    CursorOver acceptCursor(Located cursorPosition);
    void depriveCursor();
    CursorOver cursorOver();
    default boolean seeCursor() {
        var hasMouse = cursorOver();
        return hasMouse == CursorOver.DIRECT || hasMouse == CursorOver.INDIRECT;
    }
    default boolean seeCursor(boolean direct) {
        return direct ? cursorOver() == CursorOver.DIRECT : seeCursor();
    }
}
