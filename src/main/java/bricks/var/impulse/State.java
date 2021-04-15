package bricks.var.impulse;

import bricks.var.Var;
import bricks.var.Vars;

import java.util.Objects;
import java.util.function.Supplier;

public class State<C> implements Var<C> {

    Var<C> input;
    C state;

    public State(C cache) {
        input = Vars.set(cache);
        this.state = cache;
    }

    @Override
    public void let(Supplier<C> s) {
        input.let(s);
    }

    @Override
    public C get() {
        return state;
    }

    public C getState() {
        return state;
    }

    public void setState(C state) {
        this.state = state;
    }

    public C getInput() {
        return input.get();
    }

    public Impulse signal() {
        return new InequalityImpulse<>(input, input.get());
    }
}
