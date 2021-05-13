package bricks;

import bricks.var.special.NumSource;

import java.util.function.Supplier;

public interface Sized {
    NumSource width();
    NumSource height();

    static Sized relative(Sized sized, float offset) {
        return new Sized() {
            @Override
            public NumSource width() {
                return sized.width().perFloat(w -> w + offset);
            }

            @Override
            public NumSource height() {
                return sized.height().perFloat(h -> h + offset);
            }
        };
    }

    static Sized relative(Sized sized, Supplier<Number> offset) {
        return new Sized() {
            @Override
            public NumSource width() {
                return () -> sized.width().getFloat() + offset.get().floatValue();
            }

            @Override
            public NumSource height() {
                return () -> sized.height().getFloat() + offset.get().floatValue();
            }
        };
    }

    static Sized relative(Sized sized, float widthOffset, float heightOffset) {
        return new Sized() {
            @Override
            public NumSource width() {
                return sized.width().perFloat(w -> w + widthOffset);
            }

            @Override
            public NumSource height() {
                return sized.height().perFloat(h -> h + heightOffset);
            }
        };
    }

    static Sized relative(Sized sized, Supplier<Number> widthOffset, Supplier<Number> heightOffset) {
        return new Sized() {
            @Override
            public NumSource width() {
                return () -> sized.width().getFloat() + widthOffset.get().floatValue();
            }

            @Override
            public NumSource height() {
                return () -> sized.height().getFloat() + heightOffset.get().floatValue();
            }
        };
    }
}
