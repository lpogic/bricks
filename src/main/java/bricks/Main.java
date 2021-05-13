package bricks;

import bricks.input.Mouse;
import bricks.wall.Wall;

import static suite.suite.$.arm$;

public class Main extends Wall {

    public static void main(String[] args) {
        Wall.play(arm$(Wall.class, Main.class));
    }

    public void setup() {

        var line = line();//.setEndPosition(700, 400);
        line.end().aim(mouse.position());
        line.thick().set(10);
        $bricks.set(line);

        var txt = text();
        txt.aim(mouse.position());
        txt.string().let(() -> mouse.position().toString());
        $bricks.set(txt);

        var txt1 = text();
        txt1.left().set(30);
        txt1.top().set(30);
        txt1.string().let(txt.width().per(String::valueOf));
        $bricks.set(txt1);

        when(mouse.leftButton().willBe(Mouse.Button::pressing))
                .or(mouse.rightButton().willBe(Mouse.Button::pressing))
                .then(() -> System.out.print("Click!"));
//      when mouse left button willBe give button pressing, then system out print "Click!"
    }

    @Override
    public void update() {

    }
}