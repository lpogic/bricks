package bricks.wall;

import bricks.Color;
import bricks.Coordinate;
import bricks.font.FontManager;
import bricks.image.ImageManager;
import bricks.input.*;
import bricks.trade.Contract;
import bricks.trade.Host;
import bricks.var.Var;
import bricks.var.Vars;
import bricks.var.impulse.State;
import bricks.var.special.Num;
import bricks.var.special.NumSource;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;

import suite.suite.Subject;
import suite.suite.Suite;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static suite.suite.$.arm$;
import static suite.suite.$.set$;

public abstract class Wall extends Brick<Host> {

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

    protected WallPrinter printer;
    protected Input input;
    Num width;
    Num height;
    Var<Color> color;
    State<String> title;
    Brick<?> mouseRoot;

    protected void setup0(int width, int height, Color color, String title) {
        this.width = Vars.num(width);
        this.height = Vars.num(height);
        this.color = Vars.set(color);
        this.title = state(title, this::setTitle);
        this.mouseRoot = this;
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
                .put(Printer.class, printer = new WallPrinter(this))
        ;
    }

    protected void setup1() {
        glfwSetCursorPosCallback(glid, input::reportMousePositionEvent);
        glfwSetMouseButtonCallback(glid, input::reportMouseButtonEvent);
        glfwSetScrollCallback(glid, input::reportMouseScrollEvent);
        glfwSetKeyCallback(glid, input::reportKeyEvent);
        glfwSetCharModsCallback(glid, input::reportCharEvent);

        when(title).then(() -> setTitle(title.get()));
    }

    protected void update_() {
        Color c = color.get();
        glClearColor(c.red(), c.green(), c.blue(), c.alpha());
        printer.preparePrinters();
        var crd = new Coordinate.Cartesian();
        crd.x().set(input.state.mouseCursorX());
        crd.y().set(input.state.mouseCursorY());
        mouseRoot.acceptMouse(crd);
        update();
        input.update();
    }

    @Override
    protected void frontUpdateAfter() {
        if(input.getEvents(Key.Code.ESCAPE).anyTrue(Keyboard.KeyEvent::isPress)) {
            close();
        }
    }

    protected abstract void setup();

    protected final Subject $resources = set$();

    @Override
    public Subject order(Subject trade) {
        if(trade.is(Class.class) || trade.is(Contract.class)) {
            return $resources.in(trade.raw()).get();
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

    @Override
    public NumSource x() {
        return () -> width.getFloat() / 2;
    }

    @Override
    public NumSource y() {
        return () -> height.getFloat() / 2;
    }

    @Override
    public NumSource left() {
        return () -> 0;
    }

    @Override
    public NumSource right() {
        return width;
    }

    @Override
    public NumSource top() {
        return () -> 0;
    }

    @Override
    public NumSource bottom() {
        return height;
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

    @Override
    protected Printer printer() {
        return printer;
    }

    public void trapMouse(Brick<?> brick) {
        mouseRoot = brick;
    }

    public void freeMouse() {
        mouseRoot = this;
    }

    public boolean mouseTrappedBy(Brick<?> brick) {
        return mouseRoot == brick;
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

    public void push(Brick<?> brick) {
        $bricks.aimedSet(new Suite.Auto(), brick);
    }

    public void pop(Brick<?> brick) {
        $bricks.unset(brick);
    }
}
