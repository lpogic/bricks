package bricks.input;

import bricks.input.keyboard.Key;
import bricks.input.keyboard.Keyboard;
import bricks.input.mouse.MouseButton;
import bricks.input.mouse.Mouse;
import bricks.wall.Updatable;
import suite.suite.Subject;
import suite.suite.util.Sequence;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static suite.suite.$uite.$;

public class Input implements Updatable {

    public InputState state = new InputState(new int[5], 0.0, 0.0);
    Subject $events = $();

    @Override
    public void update() {
        $events.unset();
    }

    public void reportMousePositionEvent(long glid, double posX, double posY) {
        $events.set(new Mouse.PositionEvent(posX, posY, state));
        state = InputState.setCursorPosition(state, posX, posY);
    }

    public void reportMouseScrollEvent(long glid, double offsetX, double offsetY) {
        $events.set(new Mouse.ScrollEvent(offsetX, offsetY, state));
    }

    public void reportMouseButtonEvent(long glid, int button, int action, int modifiers) {
        MouseButton.Event event = new MouseButton.Event(action, modifiers);
        MouseButton.Code code = MouseButton.Code.valueOf(button);
        $events.set(new Mouse.ButtonEvent(code, event, state));
        if(action == GLFW_PRESS) {
            state = InputState.setButton(state, code, true);
        } else if(action == GLFW_RELEASE) {
            state = InputState.setButton(state, code, false);
        }
    }

    public void reportKeyEvent(long glid, int keyCode, int scanCode, int eventType, int modifiers) {
        Key.Event keyEvent = new Key.Event(eventType, modifiers);
        Key.Code code = Key.Code.valueOf(keyCode);
        $events.set(new Keyboard.KeyEvent(code, keyEvent, state));
        if(eventType == GLFW_PRESS) {
            state = InputState.setKey(state, code, true);
        } else if(eventType == GLFW_RELEASE) {
            state = InputState.setKey(state, code, false);
        } else if(eventType == GLFW_REPEAT) {
            state = InputState.setKey(state, code, true);
        }
    }

    public void reportCharEvent(long glid, int codepoint, int modifiers) {
        $events.set(new Keyboard.CharEvent(codepoint, modifiers, state));
    }

    public Sequence<InputEvent> getEvents(boolean includeSuppressed) {
        return includeSuppressed ? $events.eachAs(InputEvent.class) : getEvents();
    }

    public Sequence<InputEvent> getEvents() {
        return $events.eachAs(InputEvent.class).select(InputEvent::released);
    }

    public Sequence<Keyboard.KeyEvent> getKeyEvents() {
        return getEvents().select(Keyboard.KeyEvent.class);
    }

    public Sequence<Mouse.ButtonEvent> getButtonEvents() {
        return getEvents().select(Mouse.ButtonEvent.class);
    }

    public Sequence<Keyboard.KeyEvent> getEvents(Key.Code code) {
        return getEvents().select(Keyboard.KeyEvent.class, e -> e.key == code);
    }

    public Sequence<Mouse.ButtonEvent> getEvents(MouseButton.Code code) {
        return getEvents().select(Mouse.ButtonEvent.class, e -> e.button == code);
    }
}
