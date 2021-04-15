package bricks;

import bricks.var.special.NumSource;

import java.util.function.Supplier;

public interface Sized {
    NumSource width();
    NumSource height();

    default Sized margin(float margin) {
        return new Sized() {
            @Override
            public NumSource width() {
                return Sized.this.width().perFloat(w -> w + margin);
            }

            @Override
            public NumSource height() {
                return Sized.this.height().perFloat(h -> h + margin);
            }
        };
    }

    default Sized margin(Supplier<Number> margin) {
        return new Sized() {
            @Override
            public NumSource width() {
                return () -> Sized.this.width().getFloat() + margin.get().floatValue();
            }

            @Override
            public NumSource height() {
                return () -> Sized.this.height().getFloat() + margin.get().floatValue();
            }
        };
    }

    default Sized margin(float widthMargin, float heightMargin) {
        return new Sized() {
            @Override
            public NumSource width() {
                return Sized.this.width().perFloat(w -> w + widthMargin);
            }

            @Override
            public NumSource height() {
                return Sized.this.height().perFloat(h -> h + heightMargin);
            }
        };
    }

    default Sized margin(Supplier<Number> widthMargin, Supplier<Number> heightMargin) {
        return new Sized() {
            @Override
            public NumSource width() {
                return () -> Sized.this.width().getFloat() + widthMargin.get().floatValue();
            }

            @Override
            public NumSource height() {
                return () -> Sized.this.height().getFloat() + heightMargin.get().floatValue();
            }
        };
    }
}
