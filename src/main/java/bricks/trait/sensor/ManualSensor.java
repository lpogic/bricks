package bricks.trait.sensor;

public class ManualSensor implements Sensor {
    private boolean called;

    public ManualSensor() {
        called = false;
    }

    @Override
    public boolean check() {
        var c = called;
        called = false;
        return c;
    }

    public void call() {
        called = true;
    }
}
