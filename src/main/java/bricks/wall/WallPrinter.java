package bricks.wall;

import bricks.graphic.*;
import bricks.trade.Agent;
import suite.suite.Subject;

import static suite.suite.$.set$;

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
        if(p instanceof ColorRectangle colorRectangle) {
            colorRectanglePrinter.print(colorRectangle);
        } else if(p instanceof ColorfulRectangle colorfulRectangle) {
            colorfulRectanglePrinter.print(colorfulRectangle);
        } else if(p instanceof ColorText colorText) {
            colorTextPrinter.print(colorText, getHost().height().getFloat());
        } else if(p instanceof ColorLine colorLine) {
            colorLinePrinter.print(colorLine);
        } else if(p instanceof ImageRectangle imageRectangle) {
            imageRectanglePrinter.print(imageRectangle);
        }
    }
}
