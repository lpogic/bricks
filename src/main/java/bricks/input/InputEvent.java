package bricks.input;

public abstract class InputEvent {
    boolean suppressed;

    public boolean suppress() {
        if(suppressed) return false;
        return suppressed = true;
    }

    public boolean release() {
        if(!suppressed) return false;
        suppressed = false;
        return true;
    }

    public boolean suppressed() {
        return suppressed;
    }

    public boolean released() {
        return !suppressed;
    }
}
