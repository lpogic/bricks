package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.Color;
import bricks.Point;
import bricks.graphic.ColorRectangle;
import bricks.graphic.Shader;

import static org.lwjgl.opengl.GL30.*;

public class ColorRectanglePainter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public ColorRectanglePainter(Shader shader) {
        glid = glGenVertexArrays();

        this.shader = shader != null ? shader : BracketTree.read(Shader.class.getClassLoader().
                getResourceAsStream("forest/colorRectangleShader.tree")).as(Shader.class);

        vertexGlid = glGenBuffers();
        glBindVertexArray(glid);

        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferData(GL_ARRAY_BUFFER, 32, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 32, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 32, 8);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 4, GL_FLOAT, false, 32, 16);
        glEnableVertexAttribArray(2);
        glBindVertexArray(0);

    }

    public void setWallSize(int width, int height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void paint(ColorRectangle rectangle) {
        shader.use();

        float width = rectangle.getWidth();
        float height = rectangle.getHeight();
        Point position = rectangle.getPosition();
        Color color = rectangle.getColor();
        float x = switch (rectangle.getXOrigin()) {
            case LEFT -> position.getX() + width / 2;
            case CENTER -> position.getX();
            case RIGHT -> position.getX() - width / 2;
        };

        float y = switch (rectangle.getYOrigin()) {
            case BOTTOM -> position.getY() - height / 2;
            case CENTER -> position.getY();
            case TOP -> position.getY() + height / 2;
        };

        float[] vertex = new float[]{
                x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}