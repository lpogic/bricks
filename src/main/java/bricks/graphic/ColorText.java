package bricks.graphic;

import bricks.Color;
import bricks.Point;
import bricks.XOrigin;
import bricks.YOrigin;
import bricks.font.Font;
import bricks.font.FontManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.PreservativeVar;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;

public class ColorText extends Guest<Host> {

    Var<String> string;
    Var<Point> position;
    Var<XOrigin> xOrigin;
    Var<YOrigin> yOrigin;
    Var<Color> color;
    Var<Number> size;
    Var<Font> font;

    PreservativeVar<Number> width;

    public ColorText(Host host) {
        super(host);
        string = Vars.set("");
        position = Vars.set(new Point(0,0));
        xOrigin = Vars.set(XOrigin.CENTER);
        yOrigin = Vars.set(YOrigin.CENTER);
        color = Vars.set(Color.mix(0,0,1));
        size = Vars.set(24);
        font = Vars.set(Font.TREBUC);

        width = Vars.preserve(() -> order(FontManager.class).getFont(font.get()).getStringWidth(string.get(), size.get().floatValue()),
                string, size, font);
    }

    public String getString() {
        return string.get();
    }

    public ColorText setString(String string) {
        this.string.set(string);
        return this;
    }

    public Var<String> text() {
        return string;
    }

    public Point getPosition() {
        return position.get();
    }

    public ColorText setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public ColorText setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public Var<Point> position() {
        return position;
    }

    public XOrigin getXOrigin() {
        return xOrigin.get();
    }

    public ColorText setXOrigin(XOrigin origin) {
        this.xOrigin.set(origin);
        return this;
    }

    public Var<XOrigin> xOrigin() {
        return xOrigin;
    }

    public YOrigin getYOrigin() {
        return yOrigin.get();
    }

    public ColorText setYOrigin(YOrigin origin) {
        this.yOrigin.set(origin);
        return this;
    }

    public Var<YOrigin> yOrigin() {
        return yOrigin;
    }

    public ColorText setOrigin(XOrigin xOrigin, YOrigin yOrigin) {
        return setXOrigin(xOrigin).setYOrigin(yOrigin);
    }

    public Color getColor() {
        return color.get();
    }

    public ColorText setColor(Color color) {
        this.color.set(color);
        return this;
    }

    public Var<Color> color() {
        return color;
    }

    public float getSize() {
        return size.get().floatValue();
    }

    public ColorText setSize(Number size) {
        this.size.set(size);
        return this;
    }

    public Var<Number> size() {
        return size;
    }

    public Font getFont() {
        return font.get();
    }

    public ColorText setFont(Font font) {
        this.font.set(font);
        return this;
    }

    public Var<Font> font() {
        return font;
    }

    public float getWidth() {
        return width.get().floatValue();
    }

    public Source<Number> width() {
        return width;
    }
}