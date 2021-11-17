package bricks.trade;


import suite.suite.Subject;
import suite.suite.action.Impression;
import suite.suite.action.Statement;

import static suite.suite.$uite.$;

public abstract class Guest<H extends Host> {

    protected final H host;

    public Guest(H host) {
        this.host = host;
    }

    protected<T> T order(Class<T> trade) {
        return order($(trade)).as(trade);
    }

    protected<T> T order(Contract<T> trade) {
        return order($(trade)).asExpected();
    }

    protected Subject order(Subject trade) {
        return host.order(trade);
    }

    protected Thread order(Subject trade, Impression callback) {
        Thread thread = new Thread(() -> callback.play(order(trade)));
        thread.start();
        return thread;
    }

    protected Thread order(Subject trade, Statement callback) {
        Thread thread = new Thread(() -> callback.play(order(trade)));
        thread.start();
        return thread;
    }

    public H getHost() {
        return host;
    }
}
