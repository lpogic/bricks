package bricks.var.special;

import bricks.var.Preserve;
import suite.suite.Subject;

import java.util.function.Supplier;

public class NumPreserve extends Preserve<Number> implements NumSource {

    public NumPreserve(Supplier<Number> supplier, Subject $roots) {
        super(supplier, $roots);
    }
}
