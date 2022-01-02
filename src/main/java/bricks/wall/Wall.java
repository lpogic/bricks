package bricks.wall;

import bricks.Color;
import bricks.font.FontManager;
import bricks.image.ImageManager;
import bricks.input.*;
import bricks.input.keyboard.Key;
import bricks.input.keyboard.Keyboard;
import bricks.input.mouse.Cursor;
import bricks.slab.Shape;
import bricks.slab.printer.Printer;
import bricks.slab.printer.SlabPrinter;
import bricks.trade.Contract;
import bricks.trade.Host;
import bricks.trait.Trait;
import bricks.trait.Traits;
import bricks.trait.number.NumberTrait;
import bricks.trait.number.NumberSource;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;

import suite.suite.Subject;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static suite.suite.$uite.*;

public abstract class Wall extends Brick<Host> implements Shape {

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
                $sub.get("title", "t").in().asString("New Wall"));
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

    public Wall() {
        super(null);
    }

    public static Wall create(Wall wall, int width, int height, Color color, String title) {
        wall.setup0(width, height, color, title);

        glfwMakeContextCurrent(wall.getGlid());

        GL.createCapabilities();
        GLUtil.setupDebugMessageCallback();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        wall.setupResources();
        wall.setup1();
        wall.setup();

        long glid = wall.getGlid();

        $walls.aimedPut($walls.first().raw(), glid, wall);

        return wall;
    }

    long glid;

    protected SlabPrinter printer;
    protected Input input;
    NumberTrait width;
    NumberTrait height;
    Trait<Color> color;
    String title;

    protected void setup0(int width, int height, Color color, String title) {
        this.width = Traits.num(width);
        this.height = Traits.num(height);
        this.color = Traits.set(color);
        this.title = title;
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

    protected void setupResources() {
        $resources
                .put(Wall.class, this)
                .put(Input.class, input = new Input())
                .put(Clipboard.class, new Clipboard() {
                    @Override
                    public void set(String str) {
                        setClipboardString(str);
                    }

                    @Override
                    public String get() {
                        return getClipboardString();
                    }
                })
                .put(Story.class, new Story(20))
                .put(FontManager.class, new FontManager())
                .put(ImageManager.class, new ImageManager())
                .put(Printer.class, printer = new SlabPrinter(this))
        ;
    }

    protected void resources(Subject res) {
        $resources.alter(res);
    }

    protected void setup1() {
        glfwSetCursorPosCallback(glid, input::reportMousePositionEvent);
        glfwSetMouseButtonCallback(glid, input::reportMouseButtonEvent);
        glfwSetScrollCallback(glid, input::reportMouseScrollEvent);
        glfwSetKeyCallback(glid, input::reportKeyEvent);
        glfwSetCharModsCallback(glid, input::reportCharEvent);
        glfwSetWindowCloseCallback(glid, glid -> close());
    }

    protected void update_() {
        Color c = color.get();
        glClearColor(c.red(), c.green(), c.blue(), c.alpha());
        printer.update();
        update__();
        update();
        print();
        input.update();
    }

    protected void update__() {}

    boolean escapeDown = false;

    @Override
    public void update() {
        super.update();

        if(escapeDown) {
            for(var e : input.getEvents(true)) {
                if(e instanceof Keyboard.KeyEvent ke && ke.key == Key.Code.ESCAPE) {
                    if(ke.isRelease() && !e.suppressed()) {
                        close();
                    } else {
                        escapeDown = false;
                    }
                }
            }
        } else {
            if(input.getEvents(Key.Code.ESCAPE).anyTrue(Keyboard.KeyEvent::isPress)) {
                escapeDown = true;
            }
        }
    }

    protected abstract void setup();

    protected final Subject $resources = $(
    );

    @Override
    public Subject order(Subject trade) {
        if(trade.is(Class.class) || trade.is(Contract.class)) {
            return $resources.in(trade.raw()).get();
        }
        return $();
    }

    public Trait<Color> color() {
        return color;
    }

    @Override
    public NumberSource width() {
        return width;
    }

    @Override
    public NumberSource height() {
        return height;
    }

    @Override
    public NumberSource x() {
        return () -> width.getFloat() / 2;
    }

    @Override
    public NumberSource y() {
        return () -> height.getFloat() / 2;
    }

    @Override
    public NumberSource left() {
        return () -> 0;
    }

    @Override
    public NumberSource right() {
        return width;
    }

    @Override
    public NumberSource top() {
        return () -> 0;
    }

    @Override
    public NumberSource bottom() {
        return height;
    }

    public void setTitle(String t) {
        if(!t.equals(title)) {
            glfwSetWindowTitle(glid, t);
            title = t;
        }
    }

    public String getTitle() {
        return title;
    }

    public long getGlid() {
        return glid;
    }

    @Override
    protected Printer printer() {
        return printer;
    }

    public void setCursor(Cursor.Face face) {
        var cursorGlid = glfwCreateStandardCursor(face.getGLFWCode());
        glfwSetCursor(glid, cursorGlid);
    }

    public void setCursor(Cursor.Mode mode) {
        glfwSetInputMode(glid, GLFW_CURSOR, mode.getGLFWCode());
    }

    public void setLockKeyModifiers(boolean lock) {
        glfwSetInputMode(glid, GLFW_LOCK_KEY_MODS, lock ? GLFW_TRUE : GLFW_FALSE);
    }

    public void setAttributes(boolean decorated, boolean resizable, boolean floating, boolean autoIconify, boolean focusOnShow) {
        glfwSetWindowAttrib(glid, GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
        glfwSetWindowAttrib(glid, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        glfwSetWindowAttrib(glid, GLFW_FLOATING, floating ? GLFW_TRUE : GLFW_FALSE);
        glfwSetWindowAttrib(glid, GLFW_AUTO_ICONIFY, autoIconify ? GLFW_TRUE : GLFW_FALSE);
        glfwSetWindowAttrib(glid, GLFW_FOCUS_ON_SHOW, focusOnShow ? GLFW_TRUE : GLFW_FALSE);
    }

    public void close() {
        glfwSetWindowShouldClose(glid, true);
    }

    public void minimize() {
        glfwIconifyWindow(glid);
    }

    public void setClipboardString(String str) {
        glfwSetClipboardString(glid, str);
    }

    public String getClipboardString() {
        return glfwGetClipboardString(glid);
    }

    public Subject getBricks() {
        return $bricks;
    }

    public void setBricks(Subject $bricks) {
        this.$bricks = $bricks;
    }
}
