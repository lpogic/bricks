package bricks.wall;

import bricks.graphic.*;
import bricks.trade.Agent;
import suite.suite.Subject;

import static suite.suite.$uite.$;

public class WallPrinter extends Agent<Brick<?>> implements Printer {

    RectanglePrinter rectanglePrinter;
    LinePrinter linePrinter;
    TextPrinter textPrinter;
    ImagePrinter imagePrinter;
    GradientPrinter gradientPrinter;
    CirclePrinter circlePrinter;
    BluntLinePrinter bluntLinePrinter;
    Subject $drawables;

    public WallPrinter(Brick<?> host) {
        super(host);
        this.rectanglePrinter = new RectanglePrinter(null);
        this.linePrinter = new LinePrinter(null);
        this.textPrinter = new TextPrinter(this, null);
        this.imagePrinter = new ImagePrinter(this, null);
        this.gradientPrinter = new GradientPrinter(null);
        this.circlePrinter = new CirclePrinter(null);
        this.bluntLinePrinter = new BluntLinePrinter(null);
        this.$drawables = $();
    }

    public void preparePrinters() {
        var host = getHost();
        var width = host.width().getFloat();
        var height = host.height().getFloat();
        rectanglePrinter.setWallSize(width, height);
        linePrinter.setWallSize(width, height);
        textPrinter.setWallSize(width, height);
        imagePrinter.setWallSize(width, height);
        gradientPrinter.setWallSize(width, height);
        circlePrinter.setWallSize(width, height);
        bluntLinePrinter.setWallSize(width, height);
    }

    public void print(Printable p) {
        if(p instanceof RectangleBrick rectangleBrick) {
            rectanglePrinter.print(rectangleBrick);
        } else if(p instanceof GradientBrick gradientBrick) {
            gradientPrinter.print(gradientBrick);
        } else if(p instanceof TextBrick textBrick) {
            textPrinter.print(textBrick, getHost().height().getFloat());
        } else if(p instanceof LineBrick lineBrick) {
            linePrinter.print(lineBrick);
        } else if(p instanceof ImageBrick imageBrick) {
            imagePrinter.print(imageBrick);
        } else if(p instanceof CircleBrick circleBrick) {
            circlePrinter.print(circleBrick);
        } else if(p instanceof BluntLineBrick bluntLineBrick) {
            bluntLinePrinter.print(bluntLineBrick);
        }
    }
}
