package app.model;

public class Color {

    public static final Color PURE_GREEN = new Color(0, 1, 0);

    float red;
    float green;
    float blue;
    float alpha;

    public static Color mix(float red, float green, float blue, float alpha) {
        return new Color(red, green, blue, alpha);
    }

    public static Color mix(float red, float green, float blue) {
        return mix(red, green, blue, 1f);
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1f);
    }

    public static Color mix(Number red, Number green, Number blue, Number alpha) {
        return new Color(red, green, blue, alpha);
    }

    public static Color mix(Number red, Number green, Number blue) {
        return mix(red, green, blue, 1f);
    }

    public Color(Number red, Number green, Number blue, Number alpha) {
        this.red = red.floatValue();
        this.green = green.floatValue();
        this.blue = blue.floatValue();
        this.alpha = alpha.floatValue();
    }

    public Color(Number red, Number green, Number blue) {
        this(red, green, blue, 1f);
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }

    @Override
    public String toString() {
        return String.format("@[Color]red[%s]green[%s]blue[%s]", red, green,blue);
    }

}
