package bricks.slab;

import bricks.Color;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.trait.Pull;
import bricks.trait.PullVar;
import bricks.trait.Trait;
import bricks.trait.Traits;
import suite.suite.action.Statement;

import java.util.function.Supplier;

public class GradientSlab extends Guest<Host> implements WithSlab, Printable {

    final Centroid body;
    final Trait<Color> colorLeftTop;
    final Trait<Color> colorLeftBottom;
    final Trait<Color> colorRightTop;
    final Trait<Color> colorRightBottom;

    public GradientSlab(Host host) {
        super(host);
        body = new Centroid();
        colorLeftTop = Traits.set(Color.PURE_GREEN);
        colorLeftBottom = Traits.set(Color.PURE_GREEN);
        colorRightTop = Traits.set(Color.PURE_GREEN);
        colorRightBottom = Traits.set(Color.PURE_GREEN);
    }

    @Override
    public Slab getShape() {
        return body;
    }

    public Trait<Color> colorLeftTop() {
        return colorLeftTop;
    }

    public Trait<Color> colorLeftBottom() {
        return colorLeftBottom;
    }

    public Trait<Color> colorRightTop() {
        return colorRightTop;
    }

    public Trait<Color> colorRightBottom() {
        return colorRightBottom;
    }

    public PullVar<Color> colorTop() {
        return new PullVar<>() {
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

    public PullVar<Color> colorBottom() {
        return new PullVar<>() {
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

    public PullVar<Color> colorLeft() {
        return new PullVar<>() {
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

    public PullVar<Color> colorRight() {
        return new PullVar<>() {
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
