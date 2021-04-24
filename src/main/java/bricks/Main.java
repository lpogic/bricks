package bricks;

import bricks.input.Mouse;
import bricks.var.impulse.Edge;
import bricks.wall.Wall;

import static suite.suite.$uite.arm$;

public class Main extends Wall {

    public static void main(String[] args) {
        Wall.play(arm$(Wall.class, Main.class));
    }

    public void setup() {

        var line = line();//.setEndPosition(700, 400);
        line.end().aim(mouse.position());
        line.thick().set(10);
        show(line);

        var txt = text();
        txt.aim(mouse.position());
        txt.string().let(() -> mouse.position().toString());
        show(txt);

        var txt1 = text();
        txt1.left().set(30);
        txt1.top().set(30);
        txt1.string().let(txt.width().per(String::valueOf));
        show(txt1);

        when(mouse.leftButton().willBe(Edge::rising))
                .or(mouse.rightButton().willBe(Edge::rising))
                .then(() -> System.out.print("Click!"));
//      when mouse left button willBe give button pressing, then system out print "Click!"
    }

    @Override
    public void update() {

    }
}