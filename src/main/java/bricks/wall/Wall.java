package bricks.wall;

import bricks.Color;
import bricks.Coordinated;
import bricks.Sized;
import bricks.font.FontManager;
import bricks.graphic.ColorLine;
import bricks.graphic.ColorRectangle;
import bricks.graphic.ColorText;
import bricks.graphic.ImageRectangle;
import bricks.image.ImageManager;
import bricks.input.Clipboard;
import bricks.input.Keyboard;
import bricks.input.Mouse;
import bricks.input.Story;
import bricks.monitor.Monitor;
import bricks.trade.Composite;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.impulse.Impulse;
import bricks.var.impulse.State;
import bricks.var.special.Num;
import bricks.var.special.NumSource;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;

import suite.suite.Subject;
import suite.suite.action.Statement;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static suite.suite.$uite.set$;

public abstract class Wall implements Composite, Sized {

    static Subject $walls = set$();

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

    protected Keyboard keyboard;
    protected Mouse mouse;
    protected Clipboard clipboard;
    protected Story story;
    protected WallPainter wallPainter;
    protected WallDirector wallDirector;
    protected FontManager fontManager = new FontManager();
    protected ImageManager imageManager = new ImageManager();
    Num width;
    Num height;
    Var<Color> color;
    State<String> title;

    void setup0(int width, int height, Color color, String title) {
        this.width = Vars.num(width);
        this.height = Vars.num(height);
        this.color = Vars.set(color);
        this.title = new State<>(title);
        glid = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glid == NULL) throw new RuntimeException("Window based failed");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window willBe stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window willBe be resizable

        glfwSetFramebufferSizeCallback(glid, (win, w, h) -> {
            glfwMakeContextCurrent(win);
            glViewport(0, 0, w, h);
            this.width.set(w);
            this.height.set(h);
        });

        setLockKeyModifiers(true);
    }

    protected void setupDependencies() {
        keyboard = new Keyboard();
        mouse = new Mouse();
        clipboard = new Clipboard(this);
        story = new Story(20);
        wallPainter = new WallPainter(this);
        wallDirector = new WallDirector(this);
        fontManager = new FontManager();
        imageManager = new ImageManager();
    }

    void setup1() {
        glfwSetCursorPosCallback(glid, mouse::reportPositionEvent);
        glfwSetMouseButtonCallback(glid, mouse::reportMouseButtonEvent);
        glfwSetScrollCallback(glid, mouse::reportScrollEvent);

        glfwSetKeyCallback(glid, keyboard::reportKeyEvent);
        glfwSetCharModsCallback(glid, keyboard::reportCharEvent);

        when(title).then(() -> setTitle(title.get()));
    }

    public void update_() {
        Color c = color.get();
        glClearColor(c.red(), c.green(), c.blue(), c.alpha());
        wallDirector.update();
        update();
        wallPainter.paint();
        keyboard.update();
        processInput(getGlid());
    }

    protected abstract void setup();
    public abstract void update();

    @Override
    public Subject order(Subject trade) {
        if(trade.is(Class.class)) {
            Class<?> type = trade.asExpected();
            if(type.equals(Wall.class)) {
                return set$(this);
            } else if(type.equals(Mouse.class)) {
                return set$(mouse);
            } else if(type.equals(Keyboard.class)) {
                return set$(keyboard);
            } else if(type.equals(Clipboard.class)) {
                return set$(clipboard);
            } else if(type.equals(Story.class)) {
                return set$(story);
            } else if(type.equals(FontManager.class)) {
                return set$(fontManager);
            } else if(type.equals(ImageManager.class)) {
                return set$(imageManager);
            }
        }
        return set$();
    }

    public Var<Color> color() {
        return color;
    }

    @Override
    public NumSource width() {
        return width;
    }

    @Override
    public NumSource height() {
        return height;
    }

    public Coordinated center() {
        return new Coordinated() {

            @Override
            public NumSource x() {
                return () -> width.getFloat() / 2;
            }

            @Override
            public NumSource y() {
                return () -> height.getFloat() / 2;
            }
        };
    }

    public void setTitle(String t) {
        if(!t.equals(title.getState())) {
            glfwSetWindowTitle(glid, t);
            title.setState(t);
        }
    }

    public Var<String> title() {
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

    public void setClipboardString(String str) {
        glfwSetClipboardString(glid, str);
    }

    public String getClipboardString() {
        return glfwGetClipboardString(glid);
    }

    public void show(Object object) {
        if(object instanceof ColorRectangle) {
            wallPainter.set((ColorRectangle) object);
        } else if(object instanceof ColorText) {
            wallPainter.set((ColorText) object);
        } else if(object instanceof ColorLine) {
            wallPainter.set((ColorLine) object);
        } else if(object instanceof ImageRectangle) {
            wallPainter.set((ImageRectangle) object);
        } else if(object instanceof Brick) {
            ((Brick<?>) object).show();
        }
    }

    public void show(Object object, Object sequent) {
        if(object instanceof ColorRectangle) {
            wallPainter.set((ColorRectangle) object, sequent);
        } else if(object instanceof ColorText) {
            wallPainter.set((ColorText) object, sequent);
        } else if(object instanceof ColorLine) {
            wallPainter.set((ColorLine) object, sequent);
        } else if(object instanceof ImageRectangle) {
            wallPainter.set((ImageRectangle) object, sequent);
        } else if(object instanceof Brick) {
            ((Brick<?>) object).show();
        }
    }

    public void hide(Object object) {
        if(object instanceof Brick) {
            ((Brick<?>) object).hide();
        } else {
            wallPainter.unset(object);
        }
    }

    public void move(Object o) {
        if(o instanceof Brick<?> b) {
            wallDirector.set(b);
            b.move();
        }
    }

    public void stop(Object o) {
        if(o instanceof Brick<?> b) {
            b.stop();
            wallDirector.unset(b);
        }
    }

    public void use(Object o) {
        show(o);
        move(o);
    }

    public void use(Object o, boolean show, boolean move) {
        if(show) show(o);
        if(move) move(o);
    }

    public void cancel(Object o) {
        hide(o);
        stop(o);
    }

    public void cancel(Object o, boolean hide, boolean stop) {
        if(hide) hide(o);
        if(stop) stop(o);
    }

    protected WallDirector.MonitorDeclaration when(Impulse impulse) {
        return wallDirector.when(impulse);
    }

    protected WallDirector.MonitorDeclaration when(Supplier<?> sup) {
        return wallDirector.when(sup);
    }

    protected Monitor when(Source<Boolean> bool, Statement rising) {
        return wallDirector.when(bool, rising);
    }

    protected Subject when(Source<Boolean> bool, Statement rising, Statement falling) {
        return wallDirector.when(bool, rising, falling);
    }

    void processInput(long wglid) {
        if(glfwGetKey(wglid, GLFW_KEY_ESCAPE) == GLFW_PRESS)
            glfwSetWindowShouldClose(wglid, true);
    }
}
