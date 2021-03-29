package bricks.wall;

import bricks.input.Keyboard;
import bricks.input.Mouse;
import bricks.monitor.Monitor;
import bricks.trade.Composite;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.impulse.Impulse;
import suite.suite.Subject;
import suite.suite.action.Statement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static suite.suite.$uite.$;
import static suite.suite.Suite.join;

public abstract class Brick extends Guest implements Composite {

    @Override
    public Subject order(Subject trade) {
        return host.order(trade);
    }

    public class MonitorDeclaration {
        private final List<Impulse> impulses = new LinkedList<>();

        MonitorDeclaration(Impulse impulse) {
            impulses.add(impulse);
        }

        public MonitorDeclaration or(Impulse impulse) {
            impulses.add(impulse);
            return this;
        }

        public BrickMonitor then(Statement statement) {
            return then(statement, true);
        }

        public BrickMonitor then(Statement statement, boolean set) {
            BrickMonitor monitor = new BrickMonitor(impulses, statement);
            if(set) monitor.use();
            return monitor;
        }
    }

    public class BrickMonitor implements Monitor {
        private final List<Impulse> impulses = new LinkedList<>();
        private Statement statement;

        BrickMonitor(Collection<Impulse> impulses, Statement statement) {
            this.impulses.addAll(impulses);
            this.statement = statement;
        }

        public void use() {
            $monitors.set(this);
        }

        public void cancel() {
            $monitors.unset(this);
        }

        public BrickMonitor correctThen(Statement statement) {
            this.statement = statement;
            return this;
        }

        void update() {
            boolean detection = false;
            for (var i : impulses) {
                if(i.occur()) detection = true;
            }
            if(detection) statement.play();
        }
    }

    Subject $monitors = $();

    public Brick(Host host) {
        super(host);
    }

    protected Mouse mouse() {
        return order(Mouse.class);
    }

    protected Keyboard keyboard() {
        return order(Keyboard.class);
    }

    protected void show(Object o) {
        order(Wall.class).show(o);
    }

    protected void show(Object o, Object sequent) {
        order(Wall.class).show(o, sequent);
    }

    protected void hide(Object o) {
        order(Wall.class).hide(o);
    }

    protected void move(Object o) {
        order(Wall.class).move(o);
    }

    protected void stop(Object o) {
        order(Wall.class).stop(o);
    }

    protected void use(Object o) {
        show(o);
        move(o);
    }

    protected void use(Object o, Object sequent) {
        show(o, sequent);
        move(o);
    }

    protected void use(Object o, boolean show, boolean move) {
        if(show) show(o);
        if(move) move(o);
    }

    protected void cancel(Object o) {
        hide(o);
        stop(o);
    }

    protected void cancel(Object o, boolean hide, boolean stop) {
        if(hide) hide(o);
        if(stop) stop(o);
    }

    protected MonitorDeclaration when(Impulse impulse) {
        return new MonitorDeclaration(impulse);
    }

    protected Subject when(Source<Boolean> bool, Statement rising, Statement falling) {
        return join(
                $("rising", when(bool.willGive((p, n) -> !p && n)).then(rising)),
                $("falling", when(bool.willGive((p, n) -> p && !n)).then(falling)));
    }

    protected Monitor when(Source<Boolean> bool, Statement rising) {
        return when(bool.willGive((p, n) -> !p && n)).then(rising);
    }

    public void setup() {}
    public void show() {}
    public void hide() {}
    public void update() {
        $monitors.eachAs(BrickMonitor.class).forEach(BrickMonitor::update);
    }
}
