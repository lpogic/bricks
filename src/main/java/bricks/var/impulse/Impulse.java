package bricks.var.impulse;

@FunctionalInterface
public interface Impulse {
    boolean occur();

    static ManualImpulse manual() {
        return new ManualImpulse();
    }
}
