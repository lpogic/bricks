package bricks.input.mouse;

import bricks.input.InputEvent;
import bricks.input.InputState;

import static org.lwjgl.glfw.GLFW.*;
import static suite.suite.$uite.$;

public class Mouse {

    public static class PositionEvent extends InputEvent {
        public double x;
        public double y;
        public InputState state;

        public PositionEvent(double x, double y, InputState state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }

    public static class ScrollEvent extends InputEvent {
        public double x;
        public double y;
        public InputState state;

        public ScrollEvent(double x, double y, InputState state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }

    public static class ButtonEvent extends InputEvent {
        public final MouseButton.Code button;
        private final MouseButton.Event event;
        public InputState state;

        public ButtonEvent(MouseButton.Code code, MouseButton.Event event, InputState state) {
            this.button = code;
            this.event = event;
            this.state = state;
        }

        public boolean isPress() {
            return event.getAction() == GLFW_PRESS;
        }

        public boolean isRelease() {
            return event.getAction() == GLFW_RELEASE;
        }

        public boolean isNumLocked() {
            return (event.getModifiers() & GLFW_MOD_NUM_LOCK) != 0;
        }

        public boolean isCapsLocked() {
            return (event.getModifiers() & GLFW_MOD_CAPS_LOCK) != 0;
        }

        public boolean isAltered() {
            return (event.getModifiers() & GLFW_MOD_ALT) != 0;
        }

        public boolean isControlled() {
            return (event.getModifiers() & GLFW_MOD_CONTROL) != 0;
        }

        public boolean isShifted() {
            return (event.getModifiers() & GLFW_MOD_SHIFT) != 0;
        }

        public boolean isSupered() {
            return (event.getModifiers() & GLFW_MOD_SUPER) != 0;
        }
    }
}
