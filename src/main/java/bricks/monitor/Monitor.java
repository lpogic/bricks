package bricks.monitor;

public interface Monitor {
    void use();
    void cancel();

    static void useAll(Monitor ... m) {
        for(var monitor : m) monitor.use();
    }

    static void cancelAll(Monitor ... m) {
        for(var monitor : m) monitor.cancel();
    }
}
