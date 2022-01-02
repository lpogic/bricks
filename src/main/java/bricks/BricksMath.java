package bricks;

public interface BricksMath {
    static int trim(int value, int min, int max) {
        return value > min ? value < max ? value: max : min;
    }

    static float trim(float value, float min, float max) {
        return value > min ? value < max ? value: max : min;
    }

    static double trim(double value, double min, double max) {
        return value > min ? value < max ? value: max : min;
    }
}
