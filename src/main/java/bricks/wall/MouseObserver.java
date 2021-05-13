package bricks.wall;

import bricks.Coordinated;
import bricks.var.Source;

public interface MouseObserver {
    boolean acceptMouse(Coordinated mousePosition);
    void resetMouse();
    Source<Boolean> hasMouse();
}
