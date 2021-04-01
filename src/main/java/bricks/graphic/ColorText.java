package bricks.graphic;

import bricks.*;
import bricks.font.Font;
import bricks.font.FontManager;
import bricks.trade.Guest;
import bricks.trade.Host;
import bricks.var.PreservativeVar;
import bricks.var.Source;
import bricks.var.Var;
import bricks.var.Vars;

public class ColorText extends Guest<Host> implements Rectangular {

    Var<String> string;
    Var<Point> position;
    Var<XOrigin> xOrigin;
    Var<YOrigin> yOrigin;
    Var<Color> color;
    Var<Number> height;
    Var<Font> font;

    Source<Number> width;

    public ColorText(Host host) {
        super(host);
        string = Vars.set("");
        position = Vars.set(new Point(0,0));
        xOrigin = Vars.set(XOrigin.CENTER);
        yOrigin = Vars.set(YOrigin.CENTER);
        color = Vars.set(Color.mix(1,1,1));
        height = Vars.set(24);
        font = Vars.set(Font.TREBUC);

        width = Vars.let(() -> order(FontManager.class).getFont(font.get())
                        .getStringWidth(string.get(), height.get().floatValue()), string, height, font);
    }

    public Var<String> string() {
        return string;
    }

    public String getString() {
        return string.get();
    }

    public ColorText setString(String string) {
        this.string.set(string);
        return this;
    }

    public Var<Point> position() {
        return position;
    }

    public ColorText setPosition(Point position) {
        this.position.set(position);
        return this;
    }

    public ColorText setPosition(Number x, Number y) {
        this.position.set(new Point(x, y));
        return this;
    }

    public Var<XOrigin> xOrigin() {
        return xOrigin;
    }

    public ColorText setXOrigin(XOrigin origin) {
        this.xOrigin.set(origin);
        return this;
    }

    public Var<YOrigin> yOrigin() {
        return yOrigin;
    }

    public ColorText setYOrigin(YOrigin origin) {
        this.yOrigin.set(origin);
        return this;
    }

    public ColorText setOrigin(XOrigin xOrigin, YOrigin yOrigin) {
        return setXOrigin(xOrigin).setYOrigin(yOrigin);
    }

    public Var<Color> color() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }

    public ColorText setColor(Color color) {
        this.color.set(color);
        return this;
    }

    public Var<Number> height() {
        return height;
    }

    public ColorText setHeight(Number height) {
        this.height.set(height);
        return this;
    }

    public Source<Number> width() {
        return width;
    }

    public Var<Font> font() {
        return font;
    }

    public Font getFont() {
        return font.get();
    }

    public ColorText setFont(Font font) {
        this.font.set(font);
        return this;
    }
}