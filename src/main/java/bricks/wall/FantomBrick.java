package bricks.wall;

import bricks.Located;
import bricks.trade.Host;
import suite.suite.Subject;

public class FantomBrick extends Brick<Host> implements MouseClient {

    protected CursorOver cursorOver;

    public FantomBrick(Host host) {
        super(host);
        cursorOver = CursorOver.NO;
    }

    public Subject bricks() {
        return $bricks;
    }

    @Override
    public CursorOver acceptCursor(Located crd) {
        CursorOver brickCursorOver = CursorOver.NO;
        for (var mo : $bricks.reverse().list().selectAs(MouseClient.class)) {
            if (brickCursorOver != CursorOver.NO) mo.depriveCursor();
            else brickCursorOver = mo.acceptCursor(crd);
        }
        return cursorOver = brickCursorOver == CursorOver.NO ? CursorOver.NO : CursorOver.INDIRECT;
    }

    @Override
    public void depriveCursor() {
        for(var mc : $bricks.list().selectAs(MouseClient.class)) {
            mc.depriveCursor();
        }
        cursorOver = CursorOver.NO;
    }

    @Override
    public CursorOver cursorOver() {
        return cursorOver;
    }
}
