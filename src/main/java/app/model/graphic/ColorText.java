package app.model.graphic;

import app.model.Color;
import app.model.Point;
import app.model.XOrigin;
import app.model.YOrigin;
import app.model.font.Font;
import app.model.font.FontManager;
import app.model.trade.Guest;
import app.model.trade.Host;
import app.model.var.Source;
import app.model.var.PreservativeVar;
import app.model.var.Var;
import app.model.var.Vars;

public class ColorText extends Guest {

    Var<String> text;
    Var<Point> position;
    Var<XOrigin> xOrigin;
    Var<YOrigin> yOrigin;
    Var<Color> color;
    Var<Number> size;
    Var<Font> font;

    PreservativeVar<Number> width;

    public ColorText(Host host) {
        super(host);
        text = Vars.set("");
        position = Vars.set(new Point(0,0));
        xOrigin = Vars.set(XOrigin.CENTER);
        yOrigin = Vars.set(YOrigin.CENTER);
        color = Vars.set(Color.mix(0,0,1));
        size = Vars.set(24);
        font = Vars.set(Font.TREBUC);

        width = Vars.preserve(() -> order(FontManager.class).getFont(font.get()).getStringWidth(text.get(), size.get().floatValue()),
                text, size, font);
    }

    public String getText() {
        return text.get();
    }

    public ColorText setText(String text) {
        this.text.set(text);
        return this;
    }

    public Var<String> text() {
        return text;
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