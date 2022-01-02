package bricks.trait;

import suite.suite.Subject;
import suite.suite.action.Statement;

import static suite.suite.$uite.$;

public abstract class PushVar<T> implements Push<T>, Var<T> {

    public static<S> StoredPushVar<S> store() {
        return new StoredPushVar<>();
    }

    public static<S> StoredPushVar<S> store(S value) {
        var spv =  new StoredPushVar<S>();
        spv.set(value);
        return spv;
    }

    private final Subject listeners;

    public PushVar() {
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
    public void set(T t) {
        for (var s : listeners.eachIn().each(Statement.class)) {
            s.play();
        }
    }
}
