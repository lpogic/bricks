package bricks.var;

import suite.suite.Subject;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.BiConsumer;

import static suite.suite.$uite.$;

public class SubjectBasedPush<T> implements Push<T>{

    private final Deque<T> subject;
    private final Subject listeners;

    public SubjectBasedPush(T subject) {
        this.subject = new LinkedList<>();
        listeners = $();
        this.subject.add(subject);
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
        subject.add(t);
        if(subject.size() == 2) {
            while(subject.size() > 1) {
                var previous = subject.poll();
                var next = subject.peek();
                for (var l : listeners.eachIn()) {
                    BiConsumer<T, T> c = l.asExpected();
                    c.accept(previous, next);
                }
            }
        }
    }

    @Override
    public T get() {
        return subject.getLast();
    }
}
