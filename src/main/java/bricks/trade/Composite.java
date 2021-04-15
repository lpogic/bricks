package bricks.trade;

import bricks.graphic.ColorLine;
import bricks.graphic.ColorRectangle;
import bricks.graphic.ColorText;
import bricks.graphic.ImageRectangle;

public interface Composite extends Host {

    default ColorText text() {
        return new ColorText(this);
    }

    default ColorText release(ColorText.Sketch sketch) {
        return sketch.release(text());
    }

    default ColorRectangle rect() {
        return new ColorRectangle(this);
    }

    default ColorRectangle release(ColorRectangle.Sketch sketch) {
        return sketch.release(new ColorRectangle(this));
    }

    default ImageRectangle image() {
        return new ImageRectangle(this);
    }

    default ColorLine line() {
        return new ColorLine(this);
    }


}
