package bricks.trait;

import suite.suite.Subject;
import suite.suite.action.Statement;

import java.util.function.Supplier;

import static suite.suite.$uite.$;

public class Trait<T> implements Push<T>, Pull<T>, Var<T> {

    Supplier<T> supplier;
    private final Subject listeners;

    public Trait() {
        supplier = Source::returnNull;
        listeners = $();
    }

    @Override
    public void act(Object key, Statement statement) {
        listeners.put(key, statement);
    }

    @Override
    public void quit(Object o) {
        listeners.unset(o);
    }

    @Override
    public void let(Supplier<T> supplier) {
        this.supplier = supplier;
        for (var s : listeners.eachIn().each(Statement.class)) {
            s.play();
        }
    }

    @Override
    public T get() {
        return supplier.get();
    }
}
