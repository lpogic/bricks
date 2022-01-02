package bricks.trait;

public class StoredPushVar<V> extends PushVar<V>{
    V value;

    @Override
    public V get() {
        return value;
    }

    @Override
    public void set(V v) {
        value = v;
        super.set(v);
    }
}
