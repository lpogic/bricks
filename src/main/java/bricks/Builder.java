package bricks;

import bricks.var.Source;
import bricks.var.Var;

import java.util.function.Function;

public class Builder<T> {

    T t;

    public static<I> Builder<I> of(I i) {
        var b = new Builder<I>();
        b.t = i;
        return b;
    }

    public<A> Builder<T> set(Function<T, Var<A>> c, A a) {
        c.apply(t).set(a);
        return this;
    }

    public<A> Builder<T> let(Function<T, Var<A>> c, Source<A> s) {
        c.apply(t).let(s);
        return this;
    }

    public T get() {
        return t;
    }
}
