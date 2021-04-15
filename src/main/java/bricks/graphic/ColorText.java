package bricks.graphic;

import bricks.*;
import bricks.font.Font;
import bricks.font.FontManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.special.Num;
import bricks.var.special.NumSource;

import java.util.function.Supplier;

public class ColorText extends Guest<Host> implements Rectangular {

    Var<String> string;
    Var<Color> color;
    Var<Font> font;

    Num height;
    NumSource width;
    Num left;
    Num bottom;

    public ColorText(Host host) {
        super(host);
        string = Vars.set("");
        color = Vars.set(Color.mix(1,1,1));
        font = Vars.set(Font.TREBUC);

        height = Vars.num(20);
        width = Vars.num(() -> order(FontManager.class).getFont(font.get())
                        .getStringWidth(string.get(), height.get().floatValue()), string, height, font);

        left = Vars.num(400);
        bottom = Vars.num(300);
    }

    public Num height() {
        return height;
    }

    public NumSource width() {
        return width;
    }

    public Num left() {
        return left;
    }

    public Num right() {
        return new Num() {
            @Override
            public void let(Supplier<Number> s) {
                left.let(() -> s.get().floatValue() - width.getFloat());
            }

            @Override
            public Number get() {
                return left.getFloat() + width.getFloat();
            }
        };
    }

    public Num top() {
        return new Num() {
            @Override
            public void let(Supplier<Number> s) {
                bottom.let(() -> s.get().floatValue() + height.getFloat());
            }

            @Override
            public Number get() {
                return bottom.getFloat() - height.getFloat();
            }
        };
    }

    public Num bottom() {
        return bottom;
    }

    public Num x() {
        return new Num() {
            @Override
            public void let(Supplier<Number> s) {
                left.let(() -> s.get().floatValue() - width.getFloat() / 2);
            }

            @Override
            public Number get() {
                return left.getFloat() + width.getFloat();
            }
        };
    }

    public Num y() {
        return new Num() {
            @Override
            public void let(Supplier<Number> s) {
                bottom.let(() -> s.get().floatValue() + height.getFloat() / 2);
            }

            @Override
            public Number get() {
                return bottom.getFloat() - height.getFloat() / 2;
            }
        };
    }

    public Var<String> string() {
        return string;
    }

    public Var<Color> color() {
        return color;
    }

    public Var<Font> font() {
        return font;
    }

    public void aim(Coordinated p) {
        x().let(p.x());
        y().let(p.y());
    }

    public static abstract class Sketch extends ColorText implements AbstractSketch<ColorText> {

        public Sketch() {
            super(null);
        }
    }
}