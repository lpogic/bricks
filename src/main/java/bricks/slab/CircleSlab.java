package bricks.slab;

import bricks.Color;
import bricks.Located;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Pull;
import bricks.var.Var;
import bricks.var.special.NumPull;

import java.util.function.Supplier;

public class CircleSlab extends Guest<Host> implements Slab, Printable {

    final NumPull x;
    final NumPull y;
    final NumPull r;
    final Pull<Color> color;

    public CircleSlab(Host host) {
        super(host);
        x = Var.num(400);
        y = Var.num(300);
        r = Var.num(100);
        color = Var.pull(Color.PURE_GREEN);
    }

    @Override
    public NumPull width() {
        return new NumPull() {
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
    public NumPull height() {
        return width();
    }

    @Override
    public NumPull left() {
        return new NumPull() {
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
    public NumPull right() {
        return new NumPull() {
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
    public NumPull top() {
        return new NumPull() {
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
    public NumPull bottom() {
        return new NumPull() {
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
    public NumPull x() {
        return x;
    }

    @Override
    public NumPull y() {
        return y;
    }

    public Pull<Color> color() {
        return color;
    }

    public NumPull radius() {
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
