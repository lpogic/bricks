package bricks.input;

import bricks.Located;
import bricks.Location;
import bricks.input.keyboard.Key;
import bricks.input.mouse.MouseButton;

import java.util.Arrays;

public class InputState {

    final int[] keybits;
    final double mouseCursorX;
    final double mouseCursorY;

    InputState(int[] keybits, double mouseCursorX, double mouseCursorY) {
        this.keybits = keybits;
        this.mouseCursorX = mouseCursorX;
        this.mouseCursorY = mouseCursorY;
    }

    public boolean isPressed(Key.Code code) {
        var offset = code.getKeybit();
        return (keybits[offset / 32] & (1 << (offset % 32))) != 0;
    }

    public boolean isPressed(MouseButton.Code code) {
        var offset = code.getKeybit();
        return (keybits[offset / 32] & (1 << (offset % 32))) != 0;
    }

    public double mouseCursorX() {
        return mouseCursorX;
    }

    public double mouseCursorY() {
        return mouseCursorY;
    }

    public Located mouseCursor() {
        return new Location.Cartesian(mouseCursorX, mouseCursorY);
    }

    public static InputState setKey(InputState state, Key.Code code, boolean newKeyState) {
        return setKeybit(state, code.getKeybit(), newKeyState);
    }

    public static InputState setButton(InputState state, MouseButton.Code code, boolean newButtonState) {
        return setKeybit(state, code.getKeybit(), newButtonState);
    }

    private static InputState setKeybit(InputState state, int offset, boolean newKeybitState) {
        if(((state.keybits[offset / 32] & (1 << (offset % 32))) != 0) == newKeybitState) return state;
        var keyBits = Arrays.copyOf(state.keybits, state.keybits.length);
        if(newKeybitState) keyBits[offset / 32] |= 1 << (offset % 32);
        else keyBits[offset / 32] &= ~(1 << (offset % 32));
        return new InputState(
                keyBits,
                state.mouseCursorX,
                state.mouseCursorY
        );
    }

    public static InputState setCursorPosition(InputState state, double cursorX, double cursorY) {
        if(state.mouseCursorX == cursorX && state.mouseCursorY == cursorY) return state;
        return new InputState(
                state.keybits,
                cursorX,
                cursorY
        );
    }
}
