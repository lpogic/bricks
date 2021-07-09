package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.Color;
import bricks.graphic.RectangleBrick;
import bricks.graphic.Shader;

import static org.lwjgl.opengl.GL30.*;

public class RectanglePrinter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public RectanglePrinter(Shader shader) {
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

    public void setWallSize(float width, float height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void print(RectangleBrick rectangle) {
        shader.use();

        float width = rectangle.width().getFloat();
        float height = rectangle.height().getFloat();
        float x = rectangle.x().getFloat();
        float y = rectangle.y().getFloat();
        Color color = rectangle.color().get();

        float[] vertex = new float[]{
                x, y, width, height,
                color.red(), color.green(), color.blue(), color.alpha()
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
