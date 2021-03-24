package app.model.var;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Sup<T> extends Supplier<T> {

    default<O> Sup<O> per(Function<T, O> per) {
        return () -> per.apply(get());
    }
}
