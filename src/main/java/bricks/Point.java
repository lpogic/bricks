package bricks;

public record Point(float x, float y) {

    public static Point zero() {
        return new Point(0,0);
    }

    public Point(Number x, Number y) {
        this(x.floatValue(), y.floatValue());
    }

    @Override
    public String toString() {
        return "x[ " + x +
                " ] y[ " + y +
                " ]";
    }
}
