package bricks.input.keyboard;

import bricks.var.SupplierBasedPull;

import suite.suite.Subject;

import static org.lwjgl.glfw.GLFW.*;
import static suite.suite.$uite.$;

public class Key extends SupplierBasedPull<Key.Event> {

    public enum Code {
        SPACE(32, 0),
        APOSTROPHE(39, 1),
        COMMA(44, 2),
        MINUS(45, 3),
        PERIOD(46, 4),
        SLASH(47, 5),
        ZERO(48, 6),
        ONE(49, 7),
        TWO(50, 8),
        THREE(51, 9),
        FOUR(52, 10),
        FIVE(53, 11),
        SIX(54, 12),
        SEVEN(55, 13),
        EIGHT(56, 14),
        NINE(57, 15),
        SEMICOLON(59, 16),
        EQUAL(61, 17),
        A(65, 18),
        B(66, 19),
        C(67, 20),
        D(68, 21),
        E(69, 22),
        F(70, 23),
        G(71, 24),
        H(72, 25),
        I(73, 26),
        J(74, 27),
        K(75,28),
        L(76, 29),
        M(77, 30),
        N(78, 31),
        O(79, 32),
        P(80, 33),
        Q(81, 34),
        R(82, 35),
        S(83, 36),
        T(84, 37),
        U(85, 38),
        V(86, 39),
        W(87, 40),
        X(88, 41),
        Y(89, 42),
        Z(90, 43),
        LEFT_BRACKET(91, 44),
        BACKSLASH(92, 45),
        RIGHT_BRACKET(93, 46),
        GRAVE_ACCENT(96, 47),
        WORLD_1(161, 48),
        WORLD_2(162, 49),
        ESCAPE(256, 50),
        ENTER(257, 51),
        TAB(258, 52),
        BACKSPACE(259, 53),
        INSERT(260, 54),
        DELETE(261, 55),
        RIGHT(262, 56),
        LEFT(263, 57),
        DOWN(264, 58),
        UP(265, 59),
        PAGE_UP(266, 60),
        PAGE_DOWN(267, 61),
        HOME(268, 62),
        END(269, 63),
        CAPS_LOCK(280, 64),
        SCROLL_LOCK(281, 65),
        NUM_LOCK(282, 66),
        PRINT_SCREEN(283, 67),
        PAUSE(284, 68),
        F1(290, 69),
        F2(291, 70),
        F3(292, 71),
        F4(293, 72),
        F5(294, 73),
        F6(295, 74),
        F7(296, 75),
        F8(297, 76),
        F9(298, 77),
        F10(299, 78),
        F11(300, 79),
        F12(301, 80),
        F13(302, 81),
        F14(303, 82),
        F15(304, 83),
        F16(305, 84),
        F17(306, 85),
        F18(307, 86),
        F19(308, 87),
        F20(309, 88),
        F21(310, 89),
        F22(311, 90),
        F23(312, 91),
        F24(313, 92),
        F25(314, 93),
        NUM_0_INSERT(320, 94, 0b1),
        NUM_1_END(321, 95, 0b1),
        NUM_2_DOWN(322, 96, 0b1),
        NUM_3_PAGE_DOWN(323, 97, 0b1),
        NUM_4_LEFT(324, 98, 0b1),
        NUM_5_CENTER(325, 99, 0b1),
        NUM_6_RIGHT(326, 100, 0b1),
        NUM_7_HOME(327, 101, 0b1),
        NUM_8_UP(328, 102, 0b1),
        NUM_9_PAGE_UP(329, 103, 0b1),
        NUM_DECIMAL_DELETE(330, 104, 0b1),
        NUM_DIVIDE(331, 105),
        NUM_MULTIPLY(332, 106),
        NUM_SUBTRACT(333, 107),
        NUM_ADD(334, 108),
        NUM_ENTER(335, 109),
        NUM_EQUAL(336, 110),
        LEFT_SHIFT(340, 111),
        LEFT_CONTROL(341, 112),
        LEFT_ALT(342, 113),
        LEFT_SUPER(343, 114),
        RIGHT_SHIFT(344, 115),
        RIGHT_CONTROL(345, 116),
        RIGHT_ALT(346, 117),
        RIGHT_SUPER(347, 118),
        MENU(348, 119),
        LAST(MENU),
        UNKNOWN(-1, 120);

        static Subject $scan = $();
        static {
            for(var c : Code.values()) {
                if($scan.absent(c.value)) $scan.put(c.value, c);
            }
        }

        private final int value;
        private final int keybit;
        private final int subClass;

        Code(int value, int keybit) {
            this.value = value;
            this.keybit = keybit;
            subClass = 0;
        }

        Code(int value, int keybit, int subClass) {
            this.value = value;
            this.keybit = keybit;
            this.subClass = subClass;
        }

        Code(Code that) {
            this.value = that.value;
            this.keybit = that.keybit;
            this.subClass = that.subClass;
        }

        public boolean isNumPad() {
            return (subClass & 0b1) != 0;
        }

        public static Code valueOf(int code) {
            return $scan.in(code).orGiven(UNKNOWN);
        }

        public int getScancode() {
            return value;
        }

        public int getKeybit() {
            return keybit;
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
        return value != null && (value.getType() == GLFW_PRESS || value.getType() == GLFW_REPEAT);
    }

    public static boolean pressing(Event pastState, Event newState) {
        return pastState.getType() != GLFW_PRESS && newState.getType() == GLFW_PRESS;
    }

    public static boolean holding(Event pastState, Event newState) {
        if(pastState == newState) return false;
        return newState.getType() != GLFW_RELEASE;
    }
}
