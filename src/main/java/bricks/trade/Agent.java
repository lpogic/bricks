package bricks.trade;

import suite.suite.Subject;

public abstract class Agent<T extends Host> extends Guest<T> implements Host {

    public Agent(T host) {
        super(host);
    }

    @Override
    public Subject order(Subject trade) {
        return getHost().order(trade);
    }
}
