package bricks.wall;

import bricks.trade.Agent;
import bricks.trade.Host;
import suite.suite.Subject;

import static suite.suite.$uite.$;

public class WallDirector extends Brick {

    private final Subject $directed;

    public WallDirector(Host host) {
        super(host);
        this.$directed = $();
    }

    public void set(Brick brick) {
        $directed.set(brick);
    }

    public void unset(Brick brick) {
        $directed.unset(brick);
    }

    public void update() {
        super.update();
        $directed.eachAs(Brick.class).forEach(Brick::update);
    }
}
