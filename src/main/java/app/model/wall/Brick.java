package app.model.wall;

import app.model.input.Keyboard;
import app.model.input.Mouse;
import app.model.trade.Guest;
import app.model.trade.Host;
import app.model.var.impulse.Impulse;
import suite.suite.Subject;
import suite.suite.action.Statement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static suite.suite.$uite.$;

public abstract class Brick extends Guest {

    public class MonitorDeclaration {
        private final List<Impulse> impulses = new LinkedList<>();

        MonitorDeclaration(Impulse impulse) {
            impulses.add(impulse);
        }

        public MonitorDeclaration or(Impulse impulse) {
            impulses.add(impulse);
            return this;
        }

        public Monitor then(Statement statement) {
            Monitor monitor = new Monitor(impulses, statement);
            $monitors.set(monitor);
            return monitor;
        }
    }

    public class Monitor {
        private final List<Impulse> impulses = new LinkedList<>();
        private Statement statement;

        Monitor(Collection<Impulse> impulses, Statement statement) {
            this.impulses.addAll(impulses);
            this.statement = statement;
        }

        public Monitor set() {
            $monitors.set(this);
            return this;
        }

        public void cancel() {
            $monitors.unset(this);
        }

        public Monitor correctThen(Statement statement) {
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

    protected MonitorDeclaration when(Impulse impulse) {
        return new MonitorDeclaration(impulse);
    }

    public void setup() {}
    public void update() {
        $monitors.eachAs(Monitor.class).forEach(Monitor::update);
    }
}
