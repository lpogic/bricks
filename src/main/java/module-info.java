open module lwjgl.main {
    requires org.joml;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.stb;
    requires brackettree;
    requires suite.main;
    exports app.model.font;
    exports app.model.graphic;
    exports app.model.image;
    exports app.model.input;
    exports app.model.trade;
    exports app.model.var;
    exports app.model.wall;
}