package bricks;

public record Color(float red, float green, float blue, float alpha) {

    public static final Color PURE_GREEN = new Color(0, 1, 0);

    public static Color mix(float red, float green, float blue, float alpha) {
        return new Color(red, green, blue, alpha);
    }

    public static Color mix(float red, float green, float blue) {
        return mix(red, green, blue, 1f);
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
        this(red.floatValue(), green.floatValue(), blue.floatValue(), alpha.floatValue());
    }

    public Color(Number red, Number green, Number blue) {
        this(red, green, blue, 1f);
    }

    @Override
    public String toString() {
        return String.format("@[Color]red[%s]green[%s]blue[%s]", red, green,blue);
    }

}
