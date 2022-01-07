package bricks.slab;

import bricks.*;
import bricks.font.Font;
import bricks.font.FontManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.trait.Trait;
import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;
import bricks.trait.number.NumberSource;

import java.util.function.Supplier;

public class TextSlab extends Guest<Host> implements Shape, Printable {

    final Trait<String> text;
    final Trait<Color> color;
    final Trait<Font> font;

    final NumberTrait height;
    final NumberSource width;
    final NumberTrait left;
    final NumberTrait bottom;
    boolean hideEol;

    public TextSlab(Host host) {
        super(host);
        text = Traits.set("");
        color = Traits.set(Color.mix(1,1,1));
        font = Traits.set(Font.JETBRAINS_MONO_REGULAR);

        height = Traits.num(20);
        width = Traits.num(() -> order(FontManager.class).getFont(font.get())
                        .getStringWidth(text.get(), height.get().floatValue(), hideEol),
                text, height, font);

        left = Traits.num(400);
        bottom = Traits.num(300);
        hideEol = true;
    }

    public NumberTrait height() {
        return height;
    }

    public NumberSource width() {
        return width;
    }

    public NumberTrait left() {
        return left;
    }

    public NumberTrait right() {
        return new NumberTrait() {
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

    public NumberTrait top() {
        return new NumberTrait() {
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

    public NumberTrait bottom() {
        return bottom;
    }

    public NumberTrait x() {
        return new NumberTrait() {
            @Override
            public void let(Supplier<Number> s) {
                left.let(() -> s.get().floatValue() - width.getFloat() / 2);
            }

            @Override
            public Number get() {
                return left.getFloat() + width.getFloat() / 2;
            }
        };
    }

    public NumberTrait y() {
        return new NumberTrait() {
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

    public Trait<String> text() {
        return text;
    }

    public Trait<Color> color() {
        return color;
    }

    public Trait<Font> font() {
        return font;
    }

    public void aim(Located p) {
        x().let(p.x());
        y().let(p.y());
    }

    public boolean isHideEol() {
        return hideEol;
    }

    public void setHideEol(boolean hideEol) {
        this.hideEol = hideEol;
    }
}