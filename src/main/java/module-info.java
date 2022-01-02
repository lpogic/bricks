open module bricks {
    requires org.joml;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.stb;
    requires brackettree;
    requires suite;

    exports bricks;
    exports bricks.font;
    exports bricks.slab;
    exports bricks.image;
    exports bricks.input;
    exports bricks.trade;
    exports bricks.trait;
    exports bricks.trait.sensor;
    exports bricks.trait.subject;
    exports bricks.wall;
    exports bricks.monitor;
    exports bricks.trait.number;
    exports bricks.slab.printer;
    exports bricks.input.mouse;
    exports bricks.input.keyboard;
}