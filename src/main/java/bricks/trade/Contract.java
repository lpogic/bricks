package bricks.trade;

public interface Contract<T> {
    static<O> Contract<O> emit() {
        return new Contract<>() {};
    }
}
