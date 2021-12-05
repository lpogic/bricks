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
    exports bricks.var;
    exports bricks.var.impulse;
    exports bricks.var.subject;
    exports bricks.wall;
    exports bricks.monitor;
    exports bricks.var.num;
    exports bricks.slab.printer;
    exports bricks.input.mouse;
    exports bricks.input.keyboard;
}