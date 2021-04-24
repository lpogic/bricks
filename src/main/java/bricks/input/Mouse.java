package bricks.input;

import bricks.Coordinate;
import bricks.Coordinated;
import bricks.var.Source;
import bricks.var.SupVar;
import bricks.var.Var;
import bricks.var.Vars;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse {

    public static class Button extends SupVar<Boolean> {

        public Button() {
            super(false);
        }

        public Button(boolean state) {
            super(state);
        }
    }

    public static class Scroll {
        Var<ScrollEvent> x = Vars.get();
        Var<ScrollEvent> y = Vars.get();

        public Source<ScrollEvent> x() {
            return x;
        }

        public Source<ScrollEvent> y() {
            return y;
        }
    }

    public static class ScrollEvent {
        double offset;

        public ScrollEvent(double offset) {
            this.offset = offset;
        }

        public double getOffset() {
            return offset;
        }
    }

    Coordinate position = new Coordinate.Cartesian();
    Scroll scroll = new Scroll();
    Button leftButton = new Button();
    Button rightButton = new Button();

    public void reportPositionEvent(long w, double posX, double posY) {
        position.x().set(posX);
        position.y().set(posY);
    }

    public void reportScrollEvent(long wglid, double offsetX, double offsetY) {
        if(offsetX != 0.0)scroll.x.set(new ScrollEvent(offsetX));
        if(offsetY != 0.0)scroll.y.set(new ScrollEvent(offsetY));
    }

    public void reportMouseButtonEvent(long wglid, int button, int action, int modifiers) {
        switch (button) {
            case GLFW_MOUSE_BUTTON_1 -> leftButton.set(action == GLFW_PRESS);
            case GLFW_MOUSE_BUTTON_2 -> rightButton.set(action == GLFW_PRESS);
        }
    }

    public Scroll getScroll() {
        return scroll;
    }


    public Coordinated position() {
        return position;
    }

    public Button leftButton() {
        return leftButton;
    }

    public Button rightButton() {
        return rightButton;
    }
}
