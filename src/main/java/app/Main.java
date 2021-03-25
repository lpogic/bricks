package app;

import app.model.*;
import app.model.XOrigin;
import app.model.YOrigin;
import app.model.image.Image;
import app.model.input.Mouse;
import app.model.wall.Wall;

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

        when(mouse.leftButton().willGive(Mouse.Button::click))
                .or(mouse.rightButton().willGive(Mouse.Button::click))
                .then(() -> System.out.print("Click!"));
//      when mouse left button will give button click, then system out print "Click!"
    }

    @Override
    public void update() {

    }
}