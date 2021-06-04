package bricks.wall;

import bricks.Coordinated;
import bricks.var.Source;

public interface MouseObserver {
    enum HasMouse {DIRECT, INDIRECT, NO}
    HasMouse acceptMouse(Coordinated mousePosition);
    void resetMouse();
    Source<HasMouse> hasMouse();
    default boolean mouseIn() {
        var hasMouse = hasMouse().get();
        return hasMouse == HasMouse.DIRECT || hasMouse == HasMouse.INDIRECT;
    }
    default boolean mouseIn(boolean direct) {
        return direct ? hasMouse().get() == HasMouse.DIRECT : mouseIn();
    }
}
