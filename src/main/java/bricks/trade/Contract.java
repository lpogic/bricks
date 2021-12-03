package bricks.trade;

import suite.suite.Subject;

public interface Contract<T> {
    default T unbox(Subject box){
        return box.asExpected();
    }

    static<O> Contract<O> emit() {
        return new Contract<>() {};
    }
}
