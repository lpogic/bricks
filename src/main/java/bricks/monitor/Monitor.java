package bricks.monitor;

public interface Monitor {
    boolean use();
    void cancel();

    static void use(Monitor ... m) {
        for(var monitor : m) monitor.use();
    }

    static void cancel(Monitor ... m) {
        for(var monitor : m) monitor.cancel();
    }
}
