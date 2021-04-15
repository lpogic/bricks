package bricks.input;

import bricks.var.SupVar;
import suite.suite.Subject;

import static org.lwjgl.glfw.GLFW.*;
import static suite.suite.$uite.$;

public class Key extends SupVar<Key.Event> {

    public enum Code {
        SPACE(32),
        APOSTROPHE(39),
        COMMA(44),
        MINUS(45),
        PERIOD(46),
        SLASH(47),
        ZERO(48),
        ONE(49),
        TWO(50),
        THREE(51),
        FOUR(52),
        FIVE(53),
        SIX(54),
        SEVEN(55),
        EIGHT(56),
        NINE(57),
        SEMICOLON(59),
        EQUAL(61),
        A(65),
        B(66),
        C(67),
        D(68),
        E(69),
        F(70),
        G(71),
        H(72),
        I(73),
        J(74),
        K(75),
        L(76),
        M(77),
        N(78),
        O(79),
        P(80),
        Q(81),
        R(82),
        S(83),
        T(84),
        U(85),
        V(86),
        W(87),
        X(88),
        Y(89),
        Z(90),
        LEFT_BRACKET(91),
        BACKSLASH(92),
        RIGHT_BRACKET(93),
        GRAVE_ACCENT(96),
        WORLD_1(161),
        WORLD_2(162),
        ESCAPE(256),
        ENTER(257),
        TAB(258),
        BACKSPACE(259),
        INSERT(260),
        DELETE(261),
        RIGHT(262),
        LEFT(263),
        DOWN(264),
        UP(265),
        PAGE_UP(266),
        PAGE_DOWN(267),
        HOME(268),
        END(269),
        CAPS_LOCK(280),
        SCROLL_LOCK(281),
        NUM_LOCK(282),
        PRINT_SCREEN(283),
        PAUSE(284),
        F1(290),
        F2(291),
        F3(292),
        F4(293),
        F5(294),
        F6(295),
        F7(296),
        F8(297),
        F9(298),
        F10(299),
        F11(300),
        F12(301),
        F13(302),
        F14(303),
        F15(304),
        F16(305),
        F17(306),
        F18(307),
        F19(308),
        F20(309),
        F21(310),
        F22(311),
        F23(312),
        F24(313),
        F25(314),
        NUM_0_INSERT(320, 0b1),
        NUM_1_END(321, 0b1),
        NUM_2_DOWN(322, 0b1),
        NUM_3_PAGE_DOWN(323, 0b1),
        NUM_4_LEFT(324, 0b1),
        NUM_5_CENTER(325, 0b1),
        NUM_6_RIGHT(326, 0b1),
        NUM_7_HOME(327, 0b1),
        NUM_8_UP(328, 0b1),
        NUM_9_PAGE_UP(329, 0b1),
        NUM_DECIMAL_DELETE(330, 0b1),
        NUM_DIVIDE(331),
        NUM_MULTIPLY(332),
        NUM_SUBTRACT(333),
        NUM_ADD(334),
        NUM_ENTER(335),
        NUM_EQUAL(336),
        LEFT_SHIFT(340),
        LEFT_CONTROL(341),
        LEFT_ALT(342),
        LEFT_SUPER(343),
        RIGHT_SHIFT(344),
        RIGHT_CONTROL(345),
        RIGHT_ALT(346),
        RIGHT_SUPER(347),
        MENU(348),
        LAST(MENU),
        UNKNOWN(-1);

        static Subject $scan = $();
        static {
            for(var c : Code.values()) {
                $scan.sate(c.value, $(c));
            }
        }

        private final int value;
        private final int subClass;

        Code(int value) {
            this.value = value;
            subClass = 0;
        }

        Code(int value, int subClass) {
            this.value = value;
            this.subClass = subClass;
        }

        Code(Code that) {
            this.value = that.value;
            this.subClass = that.subClass;
        }

        public boolean isNumPad() {
            return (subClass & 0b1) != 0;
        }

        public static Code valueOf(int code) {
            return $scan.in(code).orGiven(UNKNOWN);
        }
    }

    public static class Event {
        int type;
        int modifiers;

        public Event(int type, int modifiers) {
            this.type = type;
            this.modifiers = modifiers;
        }

        public int getType() {
            return type;
        }

        public int getModifiers() {
            return modifiers;
        }

        public boolean isPress() {
            return type == GLFW_PRESS;
        }

        public boolean isHold() {
            return type == GLFW_PRESS || type == GLFW_REPEAT;
        }

        public boolean isRelease() {
            return type == GLFW_RELEASE;
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

    public Key() {
    }

    public Key(Event event) {
        super(event);
    }

    public boolean isPressed() {
        Event value = get();
        return value.getType() == GLFW_PRESS || value.getType() == GLFW_REPEAT;
    }

    public static boolean pressing(Event pastState, Event newState) {
        return pastState.getType() != GLFW_PRESS && newState.getType() == GLFW_PRESS;
    }

    public static boolean holding(Event pastState, Event newState) {
        if(pastState == newState) return false;
        return newState.getType() != GLFW_RELEASE;
    }
}
