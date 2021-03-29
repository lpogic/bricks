package bricks.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static suite.suite.$uite.$;

public class Var<T> implements Source<T>, Target<T> {

    protected Consumer<T> consumer;
    protected Supplier<T> supplier;

    public Var() {
        AtomicReference<T> atomicReference = new AtomicReference<>();
        consumer = atomicReference::set;
        supplier = atomicReference::get;
    }

    public Var(T value) {
        AtomicReference<T> atomicReference = new AtomicReference<>(value);
        consumer = atomicReference::set;
        supplier = atomicReference::get;
    }

    public void eat(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void set(T value) {
        consumer.accept(value);
    }

    public void let(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public<A> void let(Supplier<A> sup, Function<A, T> fun) {
        let(() -> fun.apply(sup.get()));
    }

    public<A, B> void let(Supplier<A> sup1, Supplier<B> sup2, BiFunction<A, B, T> fun) {
        let(() -> fun.apply(sup1.get(), sup2.get()));
    }

    public void preserve(Supplier<T> sup, Supplier<?> ... roots) {
        preserve(sup, Suite.set((Object[]) roots));
    }

    public void preserve(Supplier<T> sup, Subject $roots) {
        let(new PreservativeVar<>(sup, $roots));
    }

    @Override
    public T get() {
        return supplier.get();
    }

    @Override
    public T getOr(T reserve) {
        T t = supplier.get();
        return t == null ? reserve : t;
    }
}
