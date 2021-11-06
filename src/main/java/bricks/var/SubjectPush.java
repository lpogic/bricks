package bricks.var;

import suite.suite.Subject;

import java.util.function.BiConsumer;

import static suite.suite.$uite.$;

public class SubjectPush<T> implements Push<T>{

    private T subject;
    private final Subject listeners;

    public SubjectPush() {
        listeners = $();
    }

    public SubjectPush(T subject) {
        this();
        this.subject = subject;
    }

    @Override
    public void act(Object key, BiConsumer<T, T> listener) {
        listeners.put(key, listener);
    }

    @Override
    public void quit(Object o) {
        listeners.unset(o);
    }

    @Override
    public void set(T t) {
        var previous = subject;
        subject = t;
        for(var l : listeners.eachIn()) {
            BiConsumer<T, T> c = l.asExpected();
            c.accept(previous, t);
        }
    }

    @Override
    public T get() {
        return subject;
    }
}
