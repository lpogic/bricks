package bricks.wall;

import bricks.Located;
import bricks.trade.Host;
import bricks.var.Source;
import bricks.var.special.NumSource;
import suite.suite.Subject;

public class FantomBrick extends Brick<Host> {

    public FantomBrick(Host host) {
        super(host);
    }

    public Subject bricks() {
        return $bricks;
    }

    @Override
    protected void frontUpdate() {

    }

    @Override
    public HasMouse acceptMouse(Located crd) {
        HasMouse brickHasMouse = HasMouse.NO;
        for(var mo : $bricks.reverse().selectAs(MouseObserver.class)) {
            if(brickHasMouse != HasMouse.NO) mo.resetMouse();
            else brickHasMouse = mo.acceptMouse(crd);
        }
        switch (brickHasMouse) {
            case NO -> {
                hasMouse.set(HasMouse.NO);
                return HasMouse.NO;
            }
            default -> {
                hasMouse.set(HasMouse.INDIRECT);
                return HasMouse.INDIRECT;
            }
        }
    }

    @Override
    public void resetMouse() {
        for(var mo : $bricks.selectAs(MouseObserver.class)) {
            mo.resetMouse();
        }
        hasMouse.set(HasMouse.NO);
    }

    @Override
    public Source<HasMouse> hasMouse() {
        return hasMouse;
    }

    @Override
    public NumSource x() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource y() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource width() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource height() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource left() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource right() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource top() {
        throw new RuntimeException("This is FantomBrick");
    }

    @Override
    public NumSource bottom() {
        throw new RuntimeException("This is FantomBrick");
    }
}
