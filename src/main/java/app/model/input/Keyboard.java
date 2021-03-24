package app.model.input;

import app.model.var.Var;
import app.model.var.Vars;
import org.lwjgl.glfw.GLFW;
import suite.suite.Subject;
import suite.suite.Suite;

import static org.lwjgl.glfw.GLFW.*;
import static suite.suite.$uite.$;
import static suite.suite.$uite.$$;

public class Keyboard {

    public static class Key extends Var<Integer> {

        public Key() {
            super(GLFW_RELEASE);
        }

        public Key(Integer value) {
            super(value);
        }

        public boolean isPressed() {
            int value = get();
            return value == GLFW_PRESS || value == GLFW_REPEAT;
        }

        public static boolean click(int pastState, int newState) {
            return pastState != GLFW_PRESS && newState == GLFW_PRESS;
        }
    }

    public static class KeyEvent {
        int scanCode;
        int eventType;
        int modifiers;

        public KeyEvent(int scanCode, int eventType, int modifiers) {
            this.scanCode = scanCode;
            this.eventType = eventType;
            this.modifiers = modifiers;
        }

        public int getScanCode() {
            return scanCode;
        }

        public int getEventType() {
            return eventType;
        }

        public int getModifiers() {
            return modifiers;
        }
    }

    public static class CharEvent {
        int codepoint;
        int modifiers;

        public CharEvent(int codepoint, int modifiers) {
            this.codepoint = codepoint;
            this.modifiers = modifiers;
        }

        public int getCodepoint() {
            return codepoint;
        }

        public int getModifiers() {
            return modifiers;
        }
    }

    private final Subject $keys = Suite.thready();
    private final Var<KeyEvent> keyEvent = Vars.get();
    private final Var<CharEvent> charEvent = Vars.get();

    public void reportKeyEvent(long glid, int keyCode, int scanCode, int eventType, int modifiers) {
        keyEvent.set(new KeyEvent(scanCode, eventType, modifiers));
        keyScanCode(scanCode).set(eventType);
    }

    public Var<KeyEvent> getKeyEvent() {
        return keyEvent;
    }

    public void reportCharEvent(long glid, int codepoint, int modifiers) {
        charEvent.set(new CharEvent(codepoint, modifiers));
    }

    public Var<CharEvent> getCharEvent() {
        return charEvent;
    }

    public Key key(int keyCode) {
        int scanCode = GLFW.glfwGetKeyScancode(keyCode);
        return keyScanCode(scanCode);
    }

    public Key keyScanCode(int scanCode) {
        var $ = $keys.in(scanCode).set();
        if($.absent()) {
            $.set(new Key());
        }
        return $.asExpected();
    }
}
