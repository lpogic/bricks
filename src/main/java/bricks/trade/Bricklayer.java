package bricks.trade;

import bricks.Color;
import bricks.graphic.*;

public interface Bricklayer extends Host {

    default ColorRectangle rect() {
        return new ColorRectangle(this);
    }

    default ColorRectangle rect(Color color) {
        var cr = new ColorRectangle(this);
        cr.color().set(color);
        return cr;
    }

    default ColorLine line() {
        return new ColorLine(this);
    }

    default ColorText text() {
        return new ColorText(this);
    }

    default ImageRectangle image() {
        return new ImageRectangle(this);
    }

    default ColorfulRectangle gradient() {
        return new ColorfulRectangle(this);
    }
}
