package app.model.trade;

import app.model.graphic.ColorLine;
import app.model.graphic.ColorRectangle;
import app.model.graphic.ColorText;
import app.model.graphic.ImageRectangle;

public interface Composite extends Host {

    default ColorText text() {
        return new ColorText(this);
    }

    default ColorRectangle rect() {
        return new ColorRectangle(this);
    }

    default ImageRectangle image() {
        return new ImageRectangle(this);
    }

    default ColorLine line() {
        return new ColorLine(this);
    }
}
