package app.model.image;

import java.util.Objects;

public class Image {

    String filePath;

    public Image(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(filePath, image.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath);
    }

    public String getFilePath() {
        return filePath;
    }
}
