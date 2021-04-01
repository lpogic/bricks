package bricks;

import java.util.function.BiConsumer;

public class Builder<T> {

    T t;

    public Builder(T t) {
        this.t = t;
    }

    public static<B> Builder<B> of(B b) {
        return new Builder<>(b);
    }

    public<O> Builder<T> and(BiConsumer<T, O> c, O o) {
        c.accept(t, o);
        return this;
    }

    public T get() {
        return t;
    }
}
