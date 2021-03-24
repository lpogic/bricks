package app.model.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static suite.suite.$uite.$;

public class Var<T> implements Source<T> {
    Subject $v;

    public Var() {
        $v = $();
    }

    public Var(T value) {
        this.$v = $(value);
    }

    public Var<T> set(T value) {
        this.$v.reset(value);
        return this;
    }

    public Var<T> let(Supplier<T> supplier) {
        this.$v.reset(supplier);
        return this;
    }

    public<A> Var<T> let(Supplier<A> sup, Function<A, T> fun) {
        let(() -> fun.apply(sup.get()));
        return this;
    }

    public<A, B> Var<T> let(Supplier<A> sup1, Supplier<B> sup2, BiFunction<A, B, T> fun) {
        let(() -> fun.apply(sup1.get(), sup2.get()));
        return this;
    }

    public Var<T> preserve(Supplier<T> sup, Supplier<?> ... roots) {
        return preserve(sup, Suite.set((Object[]) roots));
    }

    public Var<T> preserve(Supplier<T> sup, Subject $roots) {
        let(new PreservativeVar<>(sup, $roots));
        return this;
    }

    @Override
    public T get() {
        if($v.is(Supplier.class)) {
            Supplier<T> supplier = $v.asExpected();
            return supplier.get();
        } else return $v.orGiven(null);
    }

    @Override
    public T getOr(T reserve) {
        if($v.is(Supplier.class)) {
            Supplier<T> supplier = $v.asExpected();
            return supplier.get();
        } else return $v.orGiven(reserve);
    }

    @Override
    public boolean present() {
        return $v.present();
    }
}
