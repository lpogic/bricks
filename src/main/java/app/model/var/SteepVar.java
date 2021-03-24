package app.model.var;

import suite.suite.Subject;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static suite.suite.$uite.$;

public class SteepVar<T> implements SteepSource<T> {
    Subject v;

    public SteepVar() {
        v = $();
    }

    public SteepVar(T value) {
        this.v = $(value);
    }

    public void set(T value) {
        this.v.reset(value);
    }

    public void let(Supplier<T> supplier) {
        this.v.reset(supplier);
    }

    public<A> void let(Supplier<A> sup, Function<A, T> fun) {
        let(() -> fun.apply(sup.get()));
    }

    public<A, B> void let(Supplier<A> sup1, Supplier<B> sup2, BiFunction<A, B, T> fun) {
        let(() -> fun.apply(sup1.get(), sup2.get()));
    }

    @Override
    public T getOr(T reserve) {
        if(v.is(Supplier.class)) {
            Supplier<T> supplier = v.asExpected();
            return supplier.get();
        } else return v.orGiven(reserve);
    }

    @Override
    public boolean present() {
        return v.present();
    }
}
