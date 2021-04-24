package bricks.wall;

import bricks.graphic.*;
import bricks.trade.Agent;
import bricks.trade.Host;
import suite.suite.Subject;

import static suite.suite.$uite.set$;

public class WallPainter extends Agent<Host> {

    interface Drawable {
        void draw();
    }

    Wall wall;
    ColorRectanglePainter colorRectanglePainter;
    ColorLinePainter colorLinePainter;
    ColorTextPainter colorTextPainter;
    ImageRectanglePainter imageRectanglePainter;
    ColorfulRectanglePainter colorfulRectanglePainter;
    Subject $drawables;

    public WallPainter(Wall wall) {
        super(wall);
        this.wall = wall;
        this.colorRectanglePainter = new ColorRectanglePainter(null);
        this.colorLinePainter = new ColorLinePainter(null);
        this.colorTextPainter = new ColorTextPainter(this, null);
        this.imageRectanglePainter = new ImageRectanglePainter(this, null);
        this.colorfulRectanglePainter = new ColorfulRectanglePainter(null);
        this.$drawables = set$();
    }

    public void paint() {
        colorRectanglePainter.setWallSize(wall.width().getFloat(), wall.height().getFloat());
        colorLinePainter.setWallSize(wall.width().getFloat(), wall.height().getFloat());
        colorTextPainter.setWallSize(wall.width().getFloat(), wall.height().getFloat());
        imageRectanglePainter.setWallSize(wall.width().getFloat(), wall.height().getFloat());
        colorfulRectanglePainter.setWallSize(wall.width().getFloat(), wall.height().getFloat());
        for(var d : $drawables.eachIn().eachAs(Drawable.class)) {
            d.draw();
        }
    }

    public void set(ColorRectangle colorRectangle) {
        $drawables.put(colorRectangle, (Drawable) () -> colorRectanglePainter.paint(colorRectangle));
    }

    public void set(ColorLine colorLine) {
        $drawables.put(colorLine, (Drawable) () -> colorLinePainter.paint(colorLine));
    }

    public void set(ColorText colorText) {
        $drawables.put(colorText, (Drawable) () -> colorTextPainter.paint(colorText, wall.height().getFloat()));
    }

    public void set(ImageRectangle imageRectangle) {
        $drawables.put(imageRectangle, (Drawable) () -> imageRectanglePainter.paint(imageRectangle));
    }

    public void set(ColorfulRectangle colorfulRectangle) {
        $drawables.put(colorfulRectangle, (Drawable) () -> colorfulRectanglePainter.paint(colorfulRectangle));
    }

    public void set(ColorRectangle colorRectangle, Object sequent) {
        $drawables.aimedPut(sequent, colorRectangle, (Drawable) () -> colorRectanglePainter.paint(colorRectangle));
    }

    public void set(ColorLine colorLine, Object sequent) {
        $drawables.aimedPut(sequent, colorLine, (Drawable) () -> colorLinePainter.paint(colorLine));
    }

    public void set(ColorText colorText, Object sequent) {
        $drawables.aimedPut(sequent, colorText, (Drawable) () -> colorTextPainter.paint(colorText, wall.height().getFloat()));
    }

    public void set(ImageRectangle imageRectangle, Object sequent) {
        $drawables.aimedPut(sequent, imageRectangle, (Drawable) () -> imageRectanglePainter.paint(imageRectangle));
    }

    public void set(ColorfulRectangle colorfulRectangle, Object sequent) {
        $drawables.aimedPut(sequent, colorfulRectangle, (Drawable) () -> colorfulRectanglePainter.paint(colorfulRectangle));
    }

    public void unset(Object draw) {
        $drawables.unset(draw);
    }

    public Subject unset() {
        Subject $ = $drawables;
        $drawables = set$();
        return $;
    }
}
