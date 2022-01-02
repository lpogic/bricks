package bricks.trait.number;

import bricks.trait.ComplexTrait;
import suite.suite.Subject;

import java.util.function.Supplier;

public class ComplexNumberTrait extends ComplexTrait<Number> implements NumberSource {

    public ComplexNumberTrait(Supplier<Number> supplier, Subject $roots) {
        super(supplier, $roots);
    }
}
