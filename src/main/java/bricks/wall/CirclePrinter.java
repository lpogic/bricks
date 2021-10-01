package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.Color;
import bricks.graphic.CircleBrick;
import bricks.graphic.Shader;

import java.nio.file.Path;

import static org.lwjgl.opengl.GL30.*;

public class CirclePrinter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public CirclePrinter(Shader shader) {
        glid = glGenVertexArrays();

        this.shader = shader != null ? shader :
                BracketTree.read(Path.of(System.getProperty("java.home"), "rsc", "forest", "colorCircleShader.tree").toFile()).as(Shader.class);

        vertexGlid = glGenBuffers();
        glBindVertexArray(glid);

        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferData(GL_ARRAY_BUFFER, 28, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 28, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 1, GL_FLOAT, false, 28, 8);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 4, GL_FLOAT, false, 28, 12);
        glEnableVertexAttribArray(2);
        glBindVertexArray(0);

    }

    public void setWallSize(float width, float height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void print(CircleBrick circle) {
        shader.use();

        float radius = circle.radius().getFloat();
        float x = circle.x().getFloat();
        float y = circle.y().getFloat();
        Color color = circle.color().get();

        float[] vertex = new float[]{
                x, y, radius,
                color.red(), color.green(), color.blue(), color.alpha()
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
