package bricks.input.keyboard;

import bricks.input.InputEvent;
import bricks.input.InputState;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {

    public static class KeyEvent extends InputEvent {
        public final Key.Code key;
        private final Key.Event event;
        public InputState state;

        public KeyEvent(Key.Code code, Key.Event event, InputState state) {
            this.key = code;
            this.event = event;
            this.state = state;
        }

        public boolean isPress() {
            return event.getType() == GLFW_PRESS;
        }

        public boolean isHold() {
            return event.getType() == GLFW_PRESS || event.getType() == GLFW_REPEAT;
        }

        public boolean isRelease() {
            return event.getType() == GLFW_RELEASE;
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

    public static class CharEvent extends InputEvent {
        final int codepoint;
        final int modifiers;
        final InputState state;

        public CharEvent(int codepoint, int modifiers, InputState state) {
            this.codepoint = codepoint;
            this.modifiers = modifiers;
            this.state = state;
        }

        public int getCodepoint() {
            return codepoint;
        }

        public int getModifiers() {
            return modifiers;
        }

        public InputState getState() {
            return state;
        }
    }
}
