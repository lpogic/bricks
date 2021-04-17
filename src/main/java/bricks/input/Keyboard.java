package bricks.input;


import suite.suite.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static suite.suite.$uite.set$;

public class Keyboard {

    public static class KeyEvent {
        public final Key.Code key;
        private final Key.Event event;

        public KeyEvent(Key.Code code, Key.Event event) {
            this.key = code;
            this.event = event;
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

    private final Subject $keys = set$();
    private final List<KeyEvent> events = new ArrayList<>();
    private final List<CharEvent> charEvents = new ArrayList<>();

    public void update() {
        events.clear();
        charEvents.clear();
    }

    public void reportKeyEvent(long glid, int keyCode, int scanCode, int eventType, int modifiers) {
        Key.Event keyEvent = new Key.Event(eventType, modifiers);
        Key.Code code = Key.Code.valueOf(keyCode);
        key(code).set(keyEvent);
        events.add(new KeyEvent(code, keyEvent));
    }

//    public Var<KeyEvent> getKeyEvent() {
//        return keyEvent;
//    }

    public void reportCharEvent(long glid, int codepoint, int modifiers) {
        charEvents.add(new CharEvent(codepoint, modifiers));
    }

    public Collection<KeyEvent> getEvents() {
        return events;
    }

    public Collection<CharEvent> getCharEvents() {
        return charEvents;
    }

    public Key key(int keyCode) {
        return key(Key.Code.valueOf(keyCode));
    }

    public Key key(Key.Code scancode) {
        var $ = $keys.in(scancode).set();
        if($.absent()) {
            $.set(new Key());
        }
        return $.asExpected();
    }
}
