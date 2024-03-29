package bricks.input.mouse;

import suite.suite.Subject;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_SUPER;
import static suite.suite.$uite.$;

public class MouseButton {

    public enum Code {
        LEFT(GLFW_MOUSE_BUTTON_LEFT, 121),
        RIGHT(GLFW_MOUSE_BUTTON_RIGHT, 122),
        CENTER(GLFW_MOUSE_BUTTON_MIDDLE, 123),
        LAST(GLFW_MOUSE_BUTTON_LAST, 124),
        BUTTON1(GLFW_MOUSE_BUTTON_1, 125),
        BUTTON2(GLFW_MOUSE_BUTTON_2, 126),
        BUTTON3(GLFW_MOUSE_BUTTON_3, 127),
        BUTTON4(GLFW_MOUSE_BUTTON_4, 128),
        BUTTON5(GLFW_MOUSE_BUTTON_5, 129),
        BUTTON6(GLFW_MOUSE_BUTTON_6, 130),
        BUTTON7(GLFW_MOUSE_BUTTON_7, 131),
        BUTTON8(GLFW_MOUSE_BUTTON_8, 132),
        ;

        static Subject $scan = $();
        static {
            for(var c : Code.values()) {
                if($scan.absent(c.value)) $scan.put(c.value, c);
            }
        }

        private final int value;
        private final int keybit;

        Code(int value, int keybit) {
            this.value = value;
            this.keybit = keybit;
        }

        Code(Code that) {
            this.value = that.value;
            this.keybit = that.keybit;
        }

        public static Code valueOf(int code) {
            return $scan.in(code).asExpected();
        }

        public int getKeybit() {
            return keybit;
        }
    }

    public static class Event {
        int action;
        int modifiers;

        public Event(int action, int modifiers) {
            this.action = action;
            this.modifiers = modifiers;
        }

        public int getAction() {
            return action;
        }

        public int getModifiers() {
            return modifiers;
        }

        public boolean isPress() {
            return action == GLFW_PRESS;
        }

        public boolean isHold() {
            return action == GLFW_PRESS || action == GLFW_REPEAT;
        }

        public boolean isRelease() {
            return action == GLFW_RELEASE;
        }

        public boolean isNumLocked() {
            return (modifiers & GLFW_MOD_NUM_LOCK) != 0;
        }

        public boolean isCapsLocked() {
            return (modifiers & GLFW_MOD_CAPS_LOCK) != 0;
        }

        public boolean isAltered() {
            return (modifiers & GLFW_MOD_ALT) != 0;
        }

        public boolean isControlled() {
            return (modifiers & GLFW_MOD_CONTROL) != 0;
        }

        public boolean isShifted() {
            return (modifiers & GLFW_MOD_SHIFT) != 0;
        }

        public boolean isSupered() {
            return (modifiers & GLFW_MOD_SUPER) != 0;
        }
    }
}
