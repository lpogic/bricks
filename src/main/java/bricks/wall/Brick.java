package bricks.wall;

import bricks.Located;
import bricks.graphic.*;
import bricks.input.*;
import bricks.monitor.Monitor;
import bricks.trade.Agent;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.impulse.Edge;
import bricks.var.impulse.Impulse;
import bricks.var.impulse.InequalityImpulse;
import bricks.var.impulse.State;
import suite.suite.Subject;
import suite.suite.action.Action;
import suite.suite.action.Statement;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static suite.suite.$uite.*;


public abstract class Brick<W extends Host> extends Agent<W> implements
        Updatable, MouseObserver, Rectangular {

    @Override
    public Subject order(Subject trade) {
        if(trade.is(Class.class)) {
            Class<?> type = trade.asExpected();
            if(Director.class.equals(type)) {
                return $(new Director() {
                    @Override
                    public void moveTop(Object o) {
                        var $ = $bricks.take(o);
                        if($.present()) {
                            $bricks.alter($);
                        }
                    }
                });
            }
        }
        return getHost().order(trade);
    }

    public class MonitorDeclaration {
        private final Impulse[] impulses;

        MonitorDeclaration(Impulse impulse) {
            impulses = new Impulse[]{impulse};
        }

        MonitorDeclaration(Impulse impulse, Impulse ... impulses) {
            this.impulses = Arrays.copyOf(impulses, impulses.length + 1);
            this.impulses[impulses.length] = impulse;
        }

        public MonitorDeclaration or(Impulse impulse) {
            return new MonitorDeclaration(impulse, impulses);
        }

        public BrickMonitor then(Statement statement) {
            return then(statement, true);
        }

        public BrickMonitor then(Action action) {
            return then(action, true);
        }

        public BrickMonitor then(Statement statement, boolean use) {
            BrickMonitor monitor = new BrickMonitor(impulses, statement);
            if(use) monitor.use();
            return monitor;
        }

        public BrickMonitor then(Action action, boolean use) {
            BrickMonitor monitor = new BrickMonitor(impulses, action);
            if(use) monitor.use();
            return monitor;
        }
    }

    public class BrickMonitor implements Monitor, Updatable {
        private final Impulse[] impulses;
        private final Action action;

        BrickMonitor(Impulse[] impulses, Action action) {
            this.impulses = impulses;
            this.action = action;
        }

        public boolean use() {
            $bricks.set(this);
            boolean detection = false;
            for (var i : impulses) {
                if(i.occur()) detection = true;
            }
            return detection;
        }

        public void cancel() {
            $bricks.unset(this);
        }

        @Override
        public void update() {
            boolean detection = false;
            for (var i : impulses) {
                if(i.occur()) detection = true;
            }
            if(detection) action.play();
        }

        public void play() {
            for (var i : impulses) i.occur();
            action.play();
        }
    }

    protected Subject $bricks = $();

    public Brick(W host) {
        super(host);
        hasMouse = Vars.set(HasMouse.NO);
    }

    protected Printer printer() {
        return order(Printer.class);
    }

    protected Input input() {
        return order(Input.class);
    }

    protected Clipboard clipboard() {
        return order(Clipboard.class);
    }

    protected Story story() {
        return order(Story.class);
    }

    protected Wall wall() {
        return order(Wall.class);
    }

    public MonitorDeclaration when(Impulse impulse) {
        return new MonitorDeclaration(impulse);
    }

    public<S> MonitorDeclaration when(Supplier<S> sup) {
        return new MonitorDeclaration(new InequalityImpulse<>(sup, sup.get()));
    }

    public Subject when(Source<Boolean> bool, Statement rising, Statement falling) {
        return $(
                "rising", $(when(bool.willBe(Edge::rising)).then(rising)),
                "falling", $(when(bool.willBe(Edge::falling)).then(falling)));
    }

    public Subject when(Source<Boolean> bool, Statement rising, boolean useRising, Statement falling, boolean useFalling) {
        return $(
                "rising", $(when(bool.willBe(Edge::rising)).then(rising, useRising)),
                "falling", $(when(bool.willBe(Edge::falling)).then(falling, useFalling)));
    }

    public Monitor when(Source<Boolean> bool, Statement rising) {
        return when(bool.willBe(Edge::rising)).then(rising);
    }

    public Monitor when(Impulse impulse, Statement then) {
        return when(impulse).then(then);
    }

    public Monitor when(Impulse impulse, Statement then, boolean use) {
        return when(impulse).then(then, use);
    }

    public Monitor when(Supplier<?> sup, Statement then) {
        return when(sup).then(then);
    }

    public Monitor when(Supplier<?> sup, Statement then, boolean use) {
        return when(sup).then(then, use);
    }

    protected<T> State<T> state(T init, Consumer<T> manual) {
        State<T> state = new State<>(init);
        when(state.signal(), () -> manual.accept(state.getInput()));
        return state;
    }

    @Override
    final public void update() {
        frontUpdate();
        Printer printer = null;
        var $processed = $();
        for(var $ : $bricks.list()) {
            if($processed.absent($.raw())) {
                $processed.set($.raw());
                if ($.is(Printable.class)) {
                    Printable printable = $.asExpected();
                    if (printer == null) printer = printer();
                    printer.print(printable);
                }
                if ($.is(Updatable.class)) {
                    Updatable updatable = $.asExpected();
                    updatable.update();
                }
            }
        }
        frontUpdateAfter();
    }

    protected abstract void frontUpdate();
    protected void frontUpdateAfter() {}

    protected final Var<HasMouse> hasMouse;
    @Override
    public HasMouse acceptMouse(Located crd) {
        HasMouse brickHasMouse = HasMouse.NO;
        for(var mo : $bricks.reverse().selectAs(MouseObserver.class)) {
            if(brickHasMouse != HasMouse.NO) mo.resetMouse();
            else brickHasMouse = mo.acceptMouse(crd);
        }
        switch (brickHasMouse) {
            case NO -> {
                if(contains(crd)) {
                    hasMouse.set(HasMouse.DIRECT);
                    return HasMouse.DIRECT;
                } else {
                    hasMouse.set(HasMouse.NO);
                    return HasMouse.NO;
                }
            }
            default -> {
                hasMouse.set(HasMouse.INDIRECT);
                return HasMouse.INDIRECT;
            }
            // case DIRECT / INDIRECT but contains() == false ?
        }
    }

    @Override
    public void resetMouse() {
        for(var $ : $bricks) {
            if($.is(MouseObserver.class)) {
                MouseObserver mouseObserver = $.asExpected();
                mouseObserver.resetMouse();
            }
        }
        hasMouse.set(HasMouse.NO);
    }

    @Override
    public Source<HasMouse> hasMouse() {
        return hasMouse;
    }

}
