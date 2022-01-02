package bricks.trait.sensor;

public class Constant implements Sensor {

    private static final Constant instance = new Constant();

    public static Constant getInstance() {
        return instance;
    }

    @Override
    public boolean check() {
        return false;
    }
}
