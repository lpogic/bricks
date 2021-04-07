package bricks.var;

import suite.suite.Subject;
import suite.suite.Suite;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TwoWayVar<T> implements Var<T> {

    Consumer<T> consumer;
    Supplier<T> supplier;

    public TwoWayVar() {
        reset(null);
    }

    public TwoWayVar(T value) {
        reset(value);
    }

    public TwoWayVar(Consumer<T> consumer, Supplier<T> supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
    }

    public void let(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void let(Supplier<T> supplier) {
        this.supplier = supplier;
        this.consumer = this::reset;
    }

    public void let(Consumer<T> consumer, Supplier<T> supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
    }

    public void reset(T value) {
        AtomicReference<T> atom = new AtomicReference<>(value);
        consumer = atom::set;
        supplier = atom::get;
    }

    @Override
    public void set(T t) {
        consumer.accept(t);
    }

    @Override
    public T get() {
        return supplier.get();
    }

    public void let(Supplier<T> sup, Supplier<?> ... roots) {
        let(sup, Suite.set((Object[]) roots));
    }

    public void let(Supplier<T> sup, Subject $roots) {
        let(new Preserve<>(sup, $roots));
    }
}
