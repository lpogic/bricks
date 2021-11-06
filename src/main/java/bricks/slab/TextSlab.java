package bricks.slab;

import bricks.*;
import bricks.font.Font;
import bricks.font.FontManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Pull;
import bricks.var.Var;
import bricks.var.special.NumPull;
import bricks.var.special.NumSource;

import java.util.function.Supplier;

public class TextSlab extends Guest<Host> implements Shape, Printable {

    final Pull<String> text;
    final Pull<Color> color;
    final Pull<Font> font;

    final NumPull height;
    final NumSource width;
    final NumPull left;
    final NumPull bottom;

    public TextSlab(Host host) {
        super(host);
        text = Var.pull("");
        color = Var.pull(Color.mix(1,1,1));
        font = Var.pull(Font.TREBUC);

        height = Var.num(20);
        width = Var.num(() -> order(FontManager.class).getFont(font.get())
                        .getStringWidth(text.get(), height.get().floatValue()),
                text, height, font);

        left = Var.num(400);
        bottom = Var.num(300);
    }

    public NumPull height() {
        return height;
    }

    public NumSource width() {
        return width;
    }

    public NumPull left() {
        return left;
    }

    public NumPull right() {
        return new NumPull() {
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

    public NumPull top() {
        return new NumPull() {
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

    public NumPull bottom() {
        return bottom;
    }

    public NumPull x() {
        return new NumPull() {
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

    public NumPull y() {
        return new NumPull() {
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

    public Pull<String> text() {
        return text;
    }

    public Pull<Color> color() {
        return color;
    }

    public Pull<Font> font() {
        return font;
    }

    public void aim(Located p) {
        x().let(p.x());
        y().let(p.y());
    }
}