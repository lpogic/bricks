package bricks;

import bricks.input.Mouse;
import bricks.wall.Wall;

import java.util.Objects;

import static suite.suite.$uite.$;

public class Main extends Wall {

    public static void main(String[] args) {
        Wall.play($(Wall.class, Main.class));
    }

    public void setup() {

        var line = line();//.setEndPosition(700, 400);
        line.endPosition().let(mouse.position().or(Point.zero()));
        show(line.setThick(10));

        var txt = text()
                .setXOrigin(XOrigin.RIGHT)
                .setYOrigin(YOrigin.BOTTOM);
        txt.position().let(mouse.position().or(Point.zero()));
        txt.text().let(mouse.position().per(Objects::toString));
        show(txt);

        var txt1 = text().setPosition(100, 30);
        txt1.text().let(txt.width().per(Objects::toString));
        show(txt1);

        show(rect().setPosition(100, 100).setWidth(5).setHeight(5).setColor(Color.mix(.5, 0 , 0)));

        when(mouse.leftButton().willBe(Mouse.Button::pressed))
                .or(mouse.rightButton().willBe(Mouse.Button::pressed))
                .then(() -> System.out.print("Click!"));
//      when mouse left button willBe give button pressing, then system out print "Click!"
    }

    @Override
    public void update() {

    }
}