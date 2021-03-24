package app.model.wall;

import app.model.Color;
import app.model.graphic.ColorLine;
import app.model.graphic.ColorRectangle;
import app.model.graphic.ColorText;
import app.model.graphic.ImageRectangle;
import app.model.font.FontManager;
import app.model.image.ImageManager;
import app.model.input.Keyboard;
import app.model.input.Mouse;
import app.model.trade.Composite;
import app.model.var.Source;
import app.model.var.Var;
import app.model.var.Vars;
import app.model.var.impulse.Impulse;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;
import suite.suite.Subject;

import java.lang.reflect.InvocationTargetException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.system.MemoryUtil.NULL;
import static suite.suite.$uite.$;

public abstract class Wall implements Composite {

    static Subject $walls = $();

    public static void play(Subject $sub) {
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        if ( !glfwInit() ) throw new IllegalStateException("Unable to initialize GLFW");
        Wall wall = Wall.create(
                $sub.in(Wall.class).asExpected(),
                $sub.get("width", "w").in().asInt(800),
                $sub.get("height", "h").in().asInt(600),
                Color.mix(
                        $sub.get("red", "r").in().asFloat(.2f),
                        $sub.get("green", "g").in().asFloat(.5f),
                        $sub.get("blue", "b").in().asFloat(.4f)
                ),
                $sub.get("title, t").in().asString("New Wall"));
        glfwShowWindow(wall.getGlid());

        glfwSwapInterval(1);

        while($walls.present())
        {
//            float currentFrame = (float)glfwGetTime();
//            deltaTime = currentFrame - lastFrame;
//            lastFrame = currentFrame;

            glfwPollEvents();
            for(Wall win : $walls.eachIn().eachAs(Wall.class)) {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                win.update_();
                glfwSwapBuffers(win.getGlid());
                if(glfwWindowShouldClose(win.getGlid())) $walls.unset(win.getGlid());
            }
        }

        glfwTerminate();
    }

    public static Wall create(Class<? extends Wall> wallType, int width, int height, Color color, String title) {
        Wall wall = null;
        try {
            wall = wallType.getConstructor().newInstance();
            wall.setup0(width, height, color, title);

            glfwMakeContextCurrent(wall.getGlid());

            GL.createCapabilities();
            GLUtil.setupDebugMessageCallback();

//            glEnable(GL_ALPHA_TEST);
//            glEnable(GL_DEPTH_TEST);
//            glEnable(GL_CULL_FACE);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            wall.setupDependencies();
            wall.setup1();
            wall.setup();

            long glid = wall.getGlid();

            $walls.aimedPut($walls.first().raw(), glid, wall);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return wall;
    }

    long glid;

    protected Brick mainBrick;
    protected Keyboard keyboard;
    protected Mouse mouse;
    protected WallPainter wallPainter;
    protected FontManager fontManager = new FontManager();
    protected ImageManager imageManager = new ImageManager();
    Var<Number> width;
    Var<Number> height;
    Var<Color> color;
    Var<String> title;

    void setup0(int width, int height, Color color, String title) {
        this.width = Vars.set(width);
        this.height = Vars.set(height);
        this.color = Vars.set(color);
        this.title = Vars.set(title);
        glid = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glid == NULL) throw new RuntimeException("Window based failed");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        glfwSetFramebufferSizeCallback(glid, (win, w, h) -> {
            glfwMakeContextCurrent(win);
            glViewport(0, 0, w, h);
            this.width.set(w);
            this.height.set(h);
        });
    }

    protected void setupDependencies() {
        mainBrick = new Brick(this) {};
        keyboard = new Keyboard();
        mouse = new Mouse(this);
        wallPainter = new WallPainter(this);
        fontManager = new FontManager();
        imageManager = new ImageManager();
    }

    void setup1() {
        glfwSetCursorPosCallback(glid, mouse::reportPositionEvent);
        glfwSetMouseButtonCallback(glid, mouse::reportMouseButtonEvent);
        glfwSetScrollCallback(glid, mouse::reportScrollEvent);

        glfwSetKeyCallback(glid, keyboard::reportKeyEvent);
        glfwSetCharModsCallback(glid, keyboard::reportCharEvent);
    }

    protected void setup() {

    }

    public void update_() {
        Color c = getColor();

        mainBrick.update();
        glClearColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
        update();
        wallPainter.paint();
        processInput(getGlid());
    }

    public void update() {

    }

    @Override
    public Subject order(Subject trade) {
        if(trade.is(Class.class)) {
            Class<?> type = trade.asExpected();
            if(type.equals(Mouse.class)) {
                return $(mouse);
            } else if(type.equals(Keyboard.class)) {
                return $(keyboard);
            } else if(type.equals(FontManager.class)) {
                return $(fontManager);
            } else if(type.equals(ImageManager.class)) {
                return $(imageManager);
            }
        }
        return $();
    }

    public Color getColor() {
        return color.get();
    }

    public Wall setColor(Color color) {
        this.color.set(color);
        return this;
    }

    public Var<Color> color() {
        return color;
    }

    public int getWidth() {
        return width.get().intValue();
    }

    public Source<Number> width() {
        return width;
    }

    public int getHeight() {
        return height.get().intValue();
    }

    public Source<Number> height() {
        return height;
    }

    public String getTitle() {
        return title.get();
    }

    public Wall setTitle(String title) {
        glfwSetWindowTitle(glid, title);
        this.title.set(title);
        return this;
    }

    public Source<String> title() {
        return title;
    }

    public long getGlid() {
        return glid;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setCursor(int cursor) {
        glfwSetInputMode(glid, GLFW_CURSOR, cursor);
    }

    public void setLockKeyModifiers(boolean lock) {
        glfwSetInputMode(glid, GLFW_LOCK_KEY_MODS, lock ? GLFW_TRUE : GLFW_FALSE);
    }

    public void show(ColorRectangle rectangle) {
        wallPainter.set(rectangle);
    }

    public void show(ColorLine line) {
        wallPainter.set(line);
    }

    public void show(ColorText text) {
        wallPainter.set(text);
    }

    public void show(ImageRectangle rectangle) {
        wallPainter.set(rectangle);
    }

    protected Brick.MonitorDeclaration when(Impulse impulse) {
        return mainBrick.when(impulse);
    }

    void processInput(long wglid) {
        if(glfwGetKey(wglid, GLFW_KEY_ESCAPE) == GLFW_PRESS)
            glfwSetWindowShouldClose(wglid, true);
    }
}
