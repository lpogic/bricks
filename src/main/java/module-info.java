open module bricks {
    requires org.joml;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.stb;
    requires brackettree;
    requires suite;

    exports bricks;
    exports bricks.font;
    exports bricks.graphic;
    exports bricks.image;
    exports bricks.input;
    exports bricks.trade;
    exports bricks.var;
    exports bricks.var.impulse;
    exports bricks.wall;
    exports bricks.monitor;
    exports bricks.var.special;
}