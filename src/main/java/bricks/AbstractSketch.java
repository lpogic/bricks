package bricks;


public interface AbstractSketch<T> {

    default T release(T t) {
        for(var f : t.getClass().getFields()) {
            try {
                f.set(t, f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
