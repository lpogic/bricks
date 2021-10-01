package bricks.font;

import java.nio.file.Path;

public record Font(String location) {

    public static final Font TREBUC = new Font(Path.of(System.getProperty("java.home"), "rsc", "ttf", "trebuc.ttf").toString());
    public static final Font HACK_REGULAR = new Font(Path.of(System.getProperty("java.home"), "rsc", "ttf", "Hack-Regular.ttf").toString());

    public String getLocation() {
        return location;
    }
}
