package bricks;

import bricks.graphic.Rectangular;
import bricks.input.Mouse;
import bricks.wall.Wall;

import static suite.suite.$.arm$;

public class Main extends Wall {

    public static void main(String[] args) {
        Wall.play(arm$(Wall.class, Main.class));
    }

    public void setup() {

//        var line = line();
//        line.end().aim(mouse().getPosition());
//        line.thick().set(10);
//
//        $bricks.set(line);
//
//        Rectangular txt = text();
//        text().aim(mouse().getPosition());
//        text().string().let(() -> mouse().getPosition().toString());
//        $bricks.set(txt);
//
//        var ctxt = text();
//        ctxt.left().set(30);
//        ctxt.top().set(30);
//        ctxt.string().let(txt.width().per(String::valueOf));
//
//        $bricks.set(ctxt);
//
//        when(mouse().getLeftButton().willBe(Mouse.Button::pressing))
//                .or(mouse().getRightButton().willBe(Mouse.Button::pressing))
//                .then(() -> System.out.print("Click!"));
//      when mouse left button willBe give button pressing, then system out print "Click!"
    }

    @Override
    public void frontUpdate() {

    }
}