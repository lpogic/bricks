package bricks.var.impulse;

public class Constant implements Impulse {

    private static final Constant instance = new Constant();

    public static Constant getInstance() {
        return instance;
    }

    @Override
    public boolean occur() {
        return false;
    }
}
