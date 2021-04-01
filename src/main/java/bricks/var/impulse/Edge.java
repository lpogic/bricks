package bricks.var.impulse;

public class Edge {

    public static boolean rising(boolean p, boolean n) {
        return !p && n;
    }

    public static boolean falling(boolean p, boolean n) {
        return p && !n;
    }
}
