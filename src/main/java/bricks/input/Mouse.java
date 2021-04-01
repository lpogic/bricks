package bricks.input;

import bricks.Point;
import bricks.var.Source;
import bricks.var.Var;
import bricks.wall.Wall;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse {

    public static class Button extends Var<Integer> {

        public Button() {
            super(GLFW_RELEASE);
        }

        public Button(int state) {
            super(state);
        }

        public static boolean pressed(int pastState, int newState) {
            return pastState == GLFW_RELEASE && newState == GLFW_PRESS;
        }

        public static boolean released(int pastState, int newState) {
            return pastState == GLFW_PRESS && newState == GLFW_RELEASE;
        }

        public boolean isPressed() {
            return get() == GLFW_PRESS;
        }
    }

    public static class Scroll {
        Var<Double> x = new Var<>();
        Var<Double> y = new Var<>();

        public Var<Double> getX() {
            return x;
        }

        public Var<Double> getY() {
            return y;
        }
    }

    Wall wall;
    Var<Point> position = new Var<>();
    Scroll scroll = new Scroll();
    Button leftButton = new Button();
    Button rightButton = new Button();

    public Mouse(Wall wall) {
        this.wall = wall;
    }

    public void reportPositionEvent(long w, double posX, double posY) {
        position.set(new Point(posX, posY));
    }

    public void reportScrollEvent(long wglid, double offsetX, double offsetY) {
        if(offsetX != 0.0)scroll.x.set(offsetX);
        if(offsetY != 0.0)scroll.y.set(offsetY);
    }

    public void reportMouseButtonEvent(long wglid, int button, int action, int modifiers) {
        switch (button) {
            case GLFW_MOUSE_BUTTON_1 -> leftButton.set(action);
            case GLFW_MOUSE_BUTTON_2 -> rightButton.set(action);
        }
    }

    public Scroll getScroll() {
        return scroll;
    }


    public Source<Point> position() {
        return position;
    }

    public Button leftButton() {
        return leftButton;
    }

    public Button rightButton() {
        return rightButton;
    }
}
