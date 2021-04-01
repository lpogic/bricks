package bricks.trade;

import bricks.var.Var;
import bricks.var.Vars;
import suite.suite.Subject;
import suite.suite.action.Impression;

import static suite.suite.$uite.$;

public abstract class Guest<H extends Host> {

    protected final Var<H> host;

    public Guest(H host) {
        this.host = Vars.set(host);
    }

    protected<T> T order(Class<T> trade) {
        return host.get().order($(trade)).as(trade);
    }

    protected Subject order(Subject trade) {
        return host.get().order(trade);
    }

    protected Thread order(Subject trade, Impression callback) {
        Thread thread = new Thread(() -> callback.play(host.get().order(trade)));
        thread.start();
        return thread;
    }

    public H getHost() {
        return host.get();
    }

    public void setHost(H host) {
        this.host.set(host);
    }
}
