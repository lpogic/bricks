package bricks.slab;

import bricks.Color;
import bricks.Located;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.trait.Pull;
import bricks.trait.Trait;
import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;

import java.util.function.Supplier;

public class CircleSlab extends Guest<Host> implements Slab, Printable {

    final NumberTrait x;
    final NumberTrait y;
    final NumberTrait r;
    final Trait<Color> color;

    public CircleSlab(Host host) {
        super(host);
        x = Traits.num(400);
        y = Traits.num(300);
        r = Traits.num(100);
        color = Traits.set(Color.PURE_GREEN);
    }

    @Override
    public NumberTrait width() {
        return new NumberTrait() {
            @Override
            public void let(Supplier<Number> s) {
                r.let(() -> s.get().floatValue() / 2);
            }

            @Override
            public Number get() {
                return r.getFloat() * 2;
            }
        };
    }

    @Override
    public NumberTrait height() {
        return width();
    }

    @Override
    public NumberTrait left() {
        return new NumberTrait() {
            @Override
            public void let(Supplier<Number> s) {
                x.let(() -> s.get().floatValue() + r.getFloat());
            }

            @Override
            public Number get() {
                return x.getFloat() - r.getFloat();
            }
        };
    }

    @Override
    public NumberTrait right() {
        return new NumberTrait() {
            @Override
            public void let(Supplier<Number> s) {
                x.let(() -> s.get().floatValue() - r.getFloat());
            }

            @Override
            public Number get() {
                return x.getFloat() + r.getFloat();
            }
        };
    }

    @Override
    public NumberTrait top() {
        return new NumberTrait() {
            @Override
            public void let(Supplier<Number> s) {
                y.let(() -> s.get().floatValue() + r.getFloat());
            }

            @Override
            public Number get() {
                return x.getFloat() - r.getFloat();
            }
        };
    }

    @Override
    public NumberTrait bottom() {
        return new NumberTrait() {
            @Override
            public void let(Supplier<Number> s) {
                y.let(() -> s.get().floatValue() - r.getFloat());
            }

            @Override
            public Number get() {
                return x.getFloat() + r.getFloat();
            }
        };
    }

    @Override
    public NumberTrait x() {
        return x;
    }

    @Override
    public NumberTrait y() {
        return y;
    }

    public Trait<Color> color() {
        return color;
    }

    public NumberTrait radius() {
        return r;
    }

    public boolean contains(Located p) {
        Number x = p.x().get();
        if(x == null) return false;
        Number y = p.y().get();
        if(y == null) return false;
        float xd = x().getFloat() - x.floatValue(), yd = y().getFloat() - y.floatValue(), r = radius().getFloat();
        return xd * xd + yd * yd <= r * r;
    }
}
