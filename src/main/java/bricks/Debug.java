package bricks;

import suite.suite.Subject;

import java.util.Arrays;

import static suite.suite.$uite.$;

public class Debug {
    static final Subject $threads = $();

    public static void log(Object threadId, String str, long sleep) {
        var $thread = $threads.in(threadId).set();
        if($thread.present()) {
            Thread thread = $thread.asExpected();
            if(thread.isAlive()) return;
        }
        Thread thread = new Thread(() -> {
            System.out.println(str);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ignored) {}
        });
        thread.start();
        $thread.reset(thread);
    }

    public static void log(String str, long sleep) {
        log(Debug.class, str, sleep);
    }
    public static void log(Object threadId, String str) {
        log(threadId, str, 1000);
    }
    public static void log(Object ... str) {
        log(Debug.class, Arrays.toString(str), 1000);
    }
}
