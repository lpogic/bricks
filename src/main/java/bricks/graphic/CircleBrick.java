package bricks.graphic;

import bricks.Color;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;
import bricks.var.special.NumSource;

import java.util.function.Supplier;

public class CircleBrick extends Guest<Host> implements Rectangle, Printable {

    final Num x;
    final Num y;
    final Num r;
    final Var<Color> color;

    public CircleBrick(Host host) {
        super(host);
        x = Vars.num(400);
        y = Vars.num(300);
        r = Vars.num(100);
        color = Vars.set(Color.PURE_GREEN);
    }

    @Override
    public Num width() {
        return new Num() {
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
    public Num height() {
        return width();
    }

    @Override
    public Num left() {
        return new Num() {
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
    public Num right() {
        return new Num() {
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
    public Num top() {
        return new Num() {
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
    public Num bottom() {
        return new Num() {
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
    public Num x() {
        return x;
    }

    @Override
    public Num y() {
        return y;
    }

    public Var<Color> color() {
        return color;
    }

    public Num radius() {
        return r;
    }
}
