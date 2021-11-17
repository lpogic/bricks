package bricks.var.impulse;

public class ManualImpulse implements Impulse {
    private boolean called;

    public ManualImpulse() {
        called = false;
    }

    @Override
    public boolean occur() {
        var c = called;
        called = false;
        return c;
    }

    public void call() {
        called = true;
    }
}
