package bricks.trade;


import suite.suite.Subject;
import suite.suite.action.Impression;

import static suite.suite.$.set$;

public abstract class Guest<H extends Host> {

    protected final H host;

    public Guest(H host) {
        this.host = host;
    }

    protected<T> T order(Class<T> trade) {
        return order(set$(trade)).as(trade);
    }

    protected Subject order(Subject trade) {
        return host.order(trade);
    }

    protected Thread order(Subject trade, Impression callback) {
        Thread thread = new Thread(() -> callback.play(order(trade)));
        thread.start();
        return thread;
    }

    public H getHost() {
        return host;
    }
}
