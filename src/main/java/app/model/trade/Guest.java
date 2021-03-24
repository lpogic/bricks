package app.model.trade;

import suite.suite.Subject;
import suite.suite.action.Impression;

import static suite.suite.$uite.$;

public abstract class Guest {

    private Host host;

    public Guest(Host host) {
        this.host = host;
    }

    protected<T> T order(Class<T> trade) {
        return host.order($(trade)).as(trade);
    }

    protected Subject order(Subject trade) {
        return host.order(trade);
    }

    protected Thread order(Subject trade, Impression callback) {
        Thread thread = new Thread(() -> callback.play(host.order(trade)));
        thread.start();
        return thread;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
