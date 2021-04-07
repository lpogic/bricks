package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.Color;
import bricks.Point;
import bricks.font.BackedFont;
import bricks.font.CharacterTexture;
import bricks.font.FontManager;
import bricks.graphic.ColorText;
import bricks.graphic.Shader;
import bricks.trade.Guest;
import bricks.trade.Host;
import org.lwjgl.stb.STBTTAlignedQuad;
import suite.suite.util.Cascade;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.stb.STBTruetype.stbtt_GetBakedQuad;

public class ColorTextPainter extends Guest<Host> {

    private final int vao;
    private final int vbo;
    private final Shader shader;

    public ColorTextPainter(Host host, Shader shader) {
        super(host);

        this.shader = shader != null ? shader : BracketTree.read(Shader.class.getClassLoader().
                getResourceAsStream("forest/textShader.tree")).as(Shader.class);

        vbo = glGenBuffers();
        vao = glGenVertexArrays();

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, 32, GL_DYNAMIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 32, 0);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 32, 8);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 32, 16);
        glEnableVertexAttribArray(3);
        glVertexAttribPointer(3, 2, GL_FLOAT, false, 32, 24);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

    }

    public void setWallSize(int width, int height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void paint(ColorText text, int wallHeight) {

        BackedFont font = order(FontManager.class).getFont(text.getFont(), text.getHeight());

        float fontSize = font.getSize();
        int bitmapWidth = font.getLoadedFont().getBitmapWidth();
        int bitmapHeight = font.getLoadedFont().getBitmapHeight();

        String txt = text.getString();
        Point position = text.getPosition();
        Color color = text.getColor();

        var hRef = text.getXOrigin();
        var vRef = text.getYOrigin();
        float textSize = text.getHeight();

        shader.use();
        shader.set("textColor", color.red(), color.green(), color.blue(), color.alpha());

        glActiveTexture(GL_TEXTURE0);

        float scale = textSize / fontSize;

        float descent = font.getScaledDescent() * scale;
        float[] X = new float[1];
        float[] Y = new float[1];
        X[0] = switch (hRef) {
            case RIGHT -> position.x() - text.getWidth();
            case CENTER -> position.x() - text.getWidth() / 2;
            case LEFT -> position.x();
        };
        Y[0] = switch (vRef) {
            case TOP -> position.y() + fontSize;
            case CENTER -> position.y() - descent;
            case BOTTOM -> position.y();
        };

        glBindVertexArray(vao);

        STBTTAlignedQuad quad = STBTTAlignedQuad.create();

        Cascade<Integer> codePoints = new Cascade<>(txt.codePoints().iterator());

        for(int codePoint : codePoints) {
            CharacterTexture charTex = font.getCharacterTexture(codePoint);
            float xRef = X[0];
            float yRef = Y[0];

            stbtt_GetBakedQuad(charTex.getBuffer(), bitmapWidth, bitmapHeight, charTex.getBufferOffset(),
                    X, Y, quad, true);

            float x0 = scale(quad.x0(), xRef, scale);
            float x1 = scale(quad.x1(), xRef, scale);
            float y0 = wallHeight - scale(quad.y0(), yRef, scale);
            float y1 = wallHeight - scale(quad.y1(), yRef, scale);
            X[0] = scale(X[0], xRef, scale);

            float[] vertices = new float[] {
                    x0, y0, x1, y1,
                    quad.s0(), quad.t0(), quad.s1(), quad.t1(),
            };

            glBindTexture(GL_TEXTURE_2D, charTex.getTextureGlid());
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);
            glDrawArrays(GL_POINTS, 0, 1);
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        }
        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getIndex(ColorText text, float testX) {

        BackedFont font = order(FontManager.class).getFont(text.getFont(), text.getHeight());

        float fontSize = font.getSize();
        int bitmapWidth = font.getLoadedFont().getBitmapWidth();
        int bitmapHeight = font.getLoadedFont().getBitmapHeight();

        String txt = text.getString();
        Point position = text.getPosition();

        var hRef = text.getXOrigin();
        float textSize = text.getHeight();

        float scale = textSize / fontSize;

        float[] X = new float[1];
        float[] Y = new float[1];
        X[0] = switch (hRef) {
            case RIGHT -> position.x() - text.getWidth();
            case CENTER -> position.x() - text.getWidth() / 2;
            case LEFT -> position.x();
        };

        STBTTAlignedQuad quad = STBTTAlignedQuad.create();

        Cascade<Integer> codePoints = new Cascade<>(txt.codePoints().iterator());

        int resultIndex = 0;
        for(int codePoint : codePoints) {
            CharacterTexture charTex = font.getCharacterTexture(codePoint);
            float xRef = X[0];

            stbtt_GetBakedQuad(charTex.getBuffer(), bitmapWidth, bitmapHeight, charTex.getBufferOffset(),
                    X, Y, quad, true);

            float x1 = scale(quad.x1(), xRef, scale);
            X[0] = scale(X[0], xRef, scale);

            if(x1 < testX) ++resultIndex;
        }

        return resultIndex;
    }

    private float scale(float rel, float ref, float scale) {
        return (rel - ref) * scale + ref;
    }
}