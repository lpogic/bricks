package bricks.var;

import java.util.function.BiConsumer;

public abstract class OverriddenPush<T> implements Push<T> {

    private final Push<T> origin;

    public OverriddenPush(Push<T> origin) {
        this.origin = origin;
    }

    @Override
    public void act(Object key, BiConsumer<T, T> listener) {
        origin.act(key, listener);
    }

    @Override
    public void quit(Object o) {
        origin.quit(o);
    }

    @Override
    public void set(T t) {
        origin.set(t);
    }

    @Override
    public T get() {
        return origin.get();
    }
}
