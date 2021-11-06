package bricks.var.special;

import bricks.var.ReactivePull;
import suite.suite.Subject;

import java.util.function.Supplier;

public class NumReactivePull extends ReactivePull<Number> implements NumSource {

    public NumReactivePull(Supplier<Number> supplier, Subject $roots) {
        super(supplier, $roots);
    }
}
