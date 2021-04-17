package bricks.wall;


import suite.suite.Subject;

import static suite.suite.$uite.set$;

public class WallDirector extends Brick<Wall> {

    private final Subject $directed;

    public WallDirector(Wall host) {
        super(host);
        this.$directed = set$();
    }

    public void set(Brick<?> brick) {
        $directed.set(brick);
    }

    public void unset(Brick<?> brick) {
        $directed.unset(brick);
    }

    public void update() {
        super.update();
        $directed.eachAs(Brick.class).forEach(Brick::update);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void move() {

    }

    @Override
    public void stop() {

    }
}
