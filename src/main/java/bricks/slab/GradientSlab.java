package bricks.slab;

import bricks.Color;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Pull;
import bricks.var.Var;

import java.util.function.Supplier;

public class GradientSlab extends Guest<Host> implements WithSlab, Printable {

    final Centroid body;
    final Pull<Color> colorLeftTop;
    final Pull<Color> colorLeftBottom;
    final Pull<Color> colorRightTop;
    final Pull<Color> colorRightBottom;

    public GradientSlab(Host host) {
        super(host);
        body = new Centroid();
        colorLeftTop = Var.pull(Color.PURE_GREEN);
        colorLeftBottom = Var.pull(Color.PURE_GREEN);
        colorRightTop = Var.pull(Color.PURE_GREEN);
        colorRightBottom = Var.pull(Color.PURE_GREEN);
    }

    @Override
    public Slab getShape() {
        return body;
    }

    public Pull<Color> colorLeftTop() {
        return colorLeftTop;
    }

    public Pull<Color> colorLeftBottom() {
        return colorLeftBottom;
    }

    public Pull<Color> colorRightTop() {
        return colorRightTop;
    }

    public Pull<Color> colorRightBottom() {
        return colorRightBottom;
    }

    public Pull<Color> colorTop() {
        return new Pull<>() {
            @Override
            public void let(Supplier<Color> s) {
                colorLeftTop.let(s);
                colorRightTop.let(s);
            }

            @Override
            public Color get() {
                return colorLeftTop.get();
            }
        };
    }

    public Pull<Color> colorBottom() {
        return new Pull<>() {
            @Override
            public void let(Supplier<Color> s) {
                colorLeftBottom.let(s);
                colorRightBottom.let(s);
            }

            @Override
            public Color get() {
                return colorLeftBottom.get();
            }
        };
    }

    public Pull<Color> colorLeft() {
        return new Pull<>() {
            @Override
            public void let(Supplier<Color> s) {
                colorLeftTop.let(s);
                colorLeftBottom.let(s);
            }

            @Override
            public Color get() {
                return colorLeftTop.get();
            }
        };
    }

    public Pull<Color> colorRight() {
        return new Pull<>() {
            @Override
            public void let(Supplier<Color> s) {
                colorRightTop.let(s);
                colorRightBottom.let(s);
            }

            @Override
            public Color get() {
                return colorRightTop.get();
            }
        };
    }
}
