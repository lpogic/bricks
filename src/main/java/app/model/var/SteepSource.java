package app.model.var;

public interface SteepSource<T> {
    boolean present();
    T getOr(T reserve);
    default Sup<T> or(T reserve) {
        return () -> getOr(reserve);
    }
}
