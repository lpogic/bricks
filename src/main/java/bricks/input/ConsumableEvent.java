package bricks.input;

public abstract class ConsumableEvent {
    boolean consumed;

    public void consume() {
        consumed = true;
    }

    public boolean notConsumed() {
        return !consumed;
    }

    public boolean isConsumed() {
        return consumed;
    }
}
