package app.model;

public class Point {

    public static Point zero() {
        return new Point(0,0);
    }

    float x;
    float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Number x, Number y) {
        this.x = x.floatValue();
        this.y = y.floatValue();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x[ " + x +
                " ] y[ " + y +
                " ]";
    }
}
