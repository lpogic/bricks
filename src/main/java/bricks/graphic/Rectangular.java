package bricks.graphic;

import bricks.*;
import bricks.var.Source;

public interface Rectangular extends Sized, Positioned, Related {

    default boolean contains(Source<Point> pointSource) {
        Point p = pointSource.get();
        return p != null && contains(pointSource.get());
    }

    default boolean contains(Point point) {
        Point position = getPosition();
        float width = getWidth();
        float hd = point.getX() - position.getX();
        if(switch (getXOrigin()) {
            case LEFT -> hd < 0 && hd > width;
            case CENTER -> Math.abs(hd) > width / 2;
            case RIGHT -> hd > 0 && hd < -width;
        }) return false;

        float height = getHeight();
        float vd = point.getY() - position.getY();
        return switch (getYOrigin()) {
            case TOP -> vd >= 0 && vd >= height;
            case CENTER -> Math.abs(vd) <= height / 2;
            case BOTTOM -> vd <= 0 && vd >= -height;
        };
    }
}
