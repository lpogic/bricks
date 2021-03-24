package app.model.font;

public class Font {

    public static final Font TREBUC = new Font("ttf/trebuc.ttf");


    private final String location;

    public Font(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
