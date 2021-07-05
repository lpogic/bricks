package bricks.wall;

import bricks.graphic.*;
import bricks.trade.Agent;
import suite.suite.Subject;

import static suite.suite.$uite.set$;

public class WallPrinter extends Agent<Brick<?>> implements Printer {

    ColorRectanglePrinter colorRectanglePrinter;
    ColorLinePrinter colorLinePrinter;
    ColorTextPrinter colorTextPrinter;
    ImageRectanglePrinter imageRectanglePrinter;
    ColorfulRectanglePrinter colorfulRectanglePrinter;
    Subject $drawables;

    public WallPrinter(Brick<?> host) {
        super(host);
        this.colorRectanglePrinter = new ColorRectanglePrinter(null);
        this.colorLinePrinter = new ColorLinePrinter(null);
        this.colorTextPrinter = new ColorTextPrinter(this, null);
        this.imageRectanglePrinter = new ImageRectanglePrinter(this, null);
        this.colorfulRectanglePrinter = new ColorfulRectanglePrinter(null);
        this.$drawables = set$();
    }

    public void preparePrinters() {
        var host = getHost();
        var width = host.width().getFloat();
        var height = host.height().getFloat();
        colorRectanglePrinter.setWallSize(width, height);
        colorLinePrinter.setWallSize(width, height);
        colorTextPrinter.setWallSize(width, height);
        imageRectanglePrinter.setWallSize(width, height);
        colorfulRectanglePrinter.setWallSize(width, height);
    }

    public void print(Printable p) {
        if(p instanceof RectangleBrick rectangleBrick) {
            colorRectanglePrinter.print(rectangleBrick);
        } else if(p instanceof GradientBrick gradientBrick) {
            colorfulRectanglePrinter.print(gradientBrick);
        } else if(p instanceof TextBrick textBrick) {
            colorTextPrinter.print(textBrick, getHost().height().getFloat());
        } else if(p instanceof LineBrick lineBrick) {
            colorLinePrinter.print(lineBrick);
        } else if(p instanceof ImageBrick imageBrick) {
            imageRectanglePrinter.print(imageBrick);
        }
    }
}
