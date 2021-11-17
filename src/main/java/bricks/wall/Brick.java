package bricks.wall;

import bricks.slab.*;
import bricks.input.*;
import bricks.monitor.Monitor;
import bricks.slab.printer.Printer;
import bricks.trade.Agent;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.impulse.Edge;
import bricks.var.impulse.Impulse;
import bricks.var.impulse.InequalityImpulse;
import suite.suite.Subject;
import suite.suite.action.Action;
import suite.suite.action.Statement;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import static suite.suite.$uite.*;


public abstract class Brick<W extends Host> extends Agent<W> implements Updatable {

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
    }

    public void lay(Object l) {
        $bricks.set(l);
    }

    public void lay(Object ... l) {
        for(var o : l) lay(o);
    }

    public void drop(Object d) {
        $bricks.unset(d);
    }

    public void drop(Object ... d) {
        for(var o : d) drop(o);
    }

    public void dropAll() {
        $bricks.unset();
    }

    public Subject bricks() {
        return $bricks;
    }

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

    public <S> Monitor when(Supplier<S> sup, BiPredicate<S, S> constraint, Statement then) {
        return when(Source.wrap(sup).willBe(constraint), then);
    }

    public void when(Source<Boolean> bool, Statement goesTrue, Statement goesFalse) {
        when(bool.willBe(Edge::rising)).then(goesTrue);
        when(bool.willBe(Edge::falling)).then(goesFalse);
    }

    public Monitor when(Source<Boolean> bool, Statement goesTrue) {
        return when(bool.willBe(Edge::rising)).then(goesTrue);
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

    @Override
    public void update() {
        var $processed = $();
        for(var b : $bricks.reverse().each()) {
            if($processed.absent(b)) {
                $processed.set(b);
                if(b instanceof Updatable updatable) updatable.update();
            }
        }
    }

    public void print() {
        Printer printer = null;
        for(var b : $bricks.each()) {
            if(b instanceof Printable printable) {
                if (printer == null) printer = printer();
                printer.print(printable);
            }
            if(b instanceof Brick brick) brick.print();
        }
    }
}
