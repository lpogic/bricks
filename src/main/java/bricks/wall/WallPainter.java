package bricks.wall;

import bricks.graphic.ColorLine;
import bricks.graphic.ColorRectangle;
import bricks.graphic.ColorText;
import bricks.graphic.ImageRectangle;
import bricks.trade.Agent;
import suite.suite.Subject;

import static suite.suite.$uite.$;

public class WallPainter extends Agent {

    interface Drawable {
        void draw();
    }

    Wall wall;
    ColorRectanglePainter colorRectanglePainter;
    ColorLinePainter colorLinePainter;
    ColorTextPainter colorTextPainter;
    ImageRectanglePainter imageRectanglePainter;
    Subject $drawables;

    public WallPainter(Wall wall) {
        super(wall);
        this.wall = wall;
        this.colorRectanglePainter = new ColorRectanglePainter(null);
        this.colorLinePainter = new ColorLinePainter(null);
        this.colorTextPainter = new ColorTextPainter(this, null);
        this.imageRectanglePainter = new ImageRectanglePainter(this, null);
        this.$drawables = $();
    }

    public void paint() {
        colorRectanglePainter.setWallSize(wall.getWidth(), wall.getHeight());
        colorLinePainter. setWallSize(wall.getWidth(), wall.getHeight());
        colorTextPainter.setWallSize(wall.getWidth(), wall.getHeight());
        imageRectanglePainter.setWallSize(wall.getWidth(), wall.getHeight());
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
        $drawables.put(colorText, (Drawable) () -> colorTextPainter.paint(colorText, wall.getHeight()));
    }

    public void set(ImageRectangle imageRectangle) {
        $drawables.put(imageRectangle, (Drawable) () -> imageRectanglePainter.paint(imageRectangle));
    }

    public void set(ColorRectangle colorRectangle, Object sequent) {
        $drawables.aimedPut(sequent, colorRectangle, (Drawable) () -> colorRectanglePainter.paint(colorRectangle));
    }

    public void set(ColorLine colorLine, Object sequent) {
        $drawables.aimedPut(sequent, colorLine, (Drawable) () -> colorLinePainter.paint(colorLine));
    }

    public void set(ColorText colorText, Object sequent) {
        $drawables.aimedPut(sequent, colorText, (Drawable) () -> colorTextPainter.paint(colorText, wall.getHeight()));
    }

    public void set(ImageRectangle imageRectangle, Object sequent) {
        $drawables.aimedPut(sequent, imageRectangle, (Drawable) () -> imageRectanglePainter.paint(imageRectangle));
    }

    public void unset(Object draw) {
        $drawables.unset(draw);
    }

    public Subject unset() {
        Subject $ = $drawables;
        $drawables = $();
        return $;
    }
}
