package bricks.trait.sensor;

@FunctionalInterface
public interface Sensor {
    boolean check();

    static ManualSensor manual() {
        return new ManualSensor();
    }
}
