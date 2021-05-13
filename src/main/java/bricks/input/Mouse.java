package bricks.input;

import bricks.Coordinate;
import bricks.Coordinated;
import bricks.var.Source;
import bricks.var.SupVar;
import bricks.var.Var;
import bricks.var.Vars;
import suite.suite.Subject;

import static org.lwjgl.glfw.GLFW.*;
import static suite.suite.$.set$;

public class Mouse {

    public static class ButtonEvent {
        public final Button.Code button;
        private final Button.Event event;

        public ButtonEvent(Button.Code code, Button.Event event) {
            this.button = code;
            this.event = event;
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

    public static class Button extends SupVar<Button.Event> {

        public Button() {
            super(new Button.Event(GLFW_RELEASE, 0));
        }

        public Button(Event state) {
            super(state);
        }

        public enum Code {
            LEFT(GLFW_MOUSE_BUTTON_LEFT),
            RIGHT(GLFW_MOUSE_BUTTON_RIGHT),
            CENTER(GLFW_MOUSE_BUTTON_MIDDLE);

            static Subject $scan = set$();
            static {
                for(var c : Code.values()) {
                    $scan.sate(c.value, set$(c));
                }
            }

            private final int value;

            Code(int value) {
                this.value = value;
            }

            Code(Code that) {
                this.value = that.value;
            }

            public static Code valueOf(int code) {
                return $scan.in(code).asExpected();
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

        public boolean isPressed() {
            Event value = get();
            return value != null && value.getAction() == GLFW_PRESS;
        }

        public static boolean pressing(Event pastState, Event newState) {
            return pastState.getAction() != GLFW_PRESS && newState.getAction() == GLFW_PRESS;
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
    Subject $events = set$();
    Subject $buttons = set$();

    public void update() {
        $events.unset();
    }

    public void reportPositionEvent(long w, double posX, double posY) {
        position.x().set(posX);
        position.y().set(posY);
    }

    public void reportScrollEvent(long wglid, double offsetX, double offsetY) {
        if(offsetX != 0.0)scroll.x.set(new ScrollEvent(offsetX));
        if(offsetY != 0.0)scroll.y.set(new ScrollEvent(offsetY));
    }

    public void reportMouseButtonEvent(long wglid, int button, int action, int modifiers) {
        Button.Event event = new Button.Event(action, modifiers);
        Button.Code code = Button.Code.valueOf(button);
        button(code).set(event);
        $events.set(new ButtonEvent(code, event));
    }

    public Scroll getScroll() {
        return scroll;
    }


    public Coordinated position() {
        return position;
    }

    public Button leftButton() {
        return button(Button.Code.LEFT);
    }

    public Button rightButton() {
        return button(Button.Code.RIGHT);
    }

    public Button button(Button.Code code) {
        var $ = $buttons.in(code).set();
        if($.absent()) {
            $.set(new Button());
        }
        return $.asExpected();
    }

    public Subject getEvents() {
        return $events;
    }
}
