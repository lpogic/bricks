package bricks.trade;

import bricks.graphic.ColorLine;
import bricks.graphic.ColorRectangle;
import bricks.graphic.ColorText;
import bricks.graphic.ImageRectangle;

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
