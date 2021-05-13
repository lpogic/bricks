package bricks;

import java.util.regex.Pattern;

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

    public static Color hex(String hex){
        if(hex.startsWith("#")) hex = hex.substring(1);
        int h = Integer.parseInt(hex, 16);
        int hexLength = hex.length();
        if(hexLength > 6) {
            int a = h & 0xff;
            int b = (h >> 8) & 0xff;
            int g = (h >> 16) & 0xff;
            int r = (h >> 24) & 0xff;
            return new Color(r / 256f, g / 256f, b / 256f, a / 256f);
        } else if(hexLength > 4) {
            int b = h & 0xff;
            int g = (h >> 8) & 0xff;
            int r = (h >> 16) & 0xff;
            return new Color(r / 256f, g / 256f, b / 256f);
        } else if(hexLength > 2) {
            float a = (h & 0xff) / 256f;
            float c = ((h >> 8) & 0xff) / 256f;
            return new Color(c,c,c,a);
        } else {
            float c = (h & 0xff) / 256f;
            return new Color(c,c,c);
        }
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
