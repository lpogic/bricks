package bricks.font;

public record Font(String location) {

    public static final Font TREBUC = new Font("ttf/trebuc.ttf");
    public static final Font HACK_REGULAR = new Font("ttf/Hack-Regular.ttf");

    public String getLocation() {
        return location;
    }
}
