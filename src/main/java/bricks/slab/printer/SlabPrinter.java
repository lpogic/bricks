package bricks.slab.printer;

import bricks.slab.*;
import bricks.trade.Agent;
import bricks.wall.Brick;
import bricks.wall.Wall;
import suite.suite.Subject;

import static suite.suite.$uite.$;

public class SlabPrinter extends Agent<Wall> implements Printer {

    RectanglePrinter rectanglePrinter;
    LinePrinter linePrinter;
    TextPrinter textPrinter;
    ImagePrinter imagePrinter;
    GradientPrinter gradientPrinter;
    CirclePrinter circlePrinter;
    BluntLinePrinter bluntLinePrinter;
    Subject $drawables;

    public SlabPrinter(Wall host) {
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

    public void update() {
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
        if(p instanceof RectangleSlab rectangleSlab) {
            rectanglePrinter.print(rectangleSlab);
        } else if(p instanceof GradientSlab gradientSlab) {
            gradientPrinter.print(gradientSlab);
        } else if(p instanceof TextSlab textSlab) {
            textPrinter.print(textSlab, getHost().height().getFloat());
        } else if(p instanceof LineSlab lineSlab) {
            linePrinter.print(lineSlab);
        } else if(p instanceof ImageSlab imageSlab) {
            imagePrinter.print(imageSlab);
        } else if(p instanceof CircleSlab circleSlab) {
            circlePrinter.print(circleSlab);
        } else if(p instanceof BluntLineSlab bluntLineSlab) {
            bluntLinePrinter.print(bluntLineSlab);
        }
    }
}
