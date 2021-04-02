package bricks.input;

import bricks.trade.Guest;
import bricks.wall.Wall;

public class Clipboard extends Guest<Wall> {

    public Clipboard(Wall host) {
        super(host);
    }

    public void set(String str) {
        getHost().setClipboardString(str);
    }

    public String get() {
        return getHost().getClipboardString();
    }
}
