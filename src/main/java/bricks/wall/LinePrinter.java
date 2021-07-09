package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.Color;
import bricks.graphic.LineBrick;
import bricks.graphic.Shader;

import static org.lwjgl.opengl.GL30.*;

public class LinePrinter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public LinePrinter(Shader shader) {
        glid = glGenVertexArrays();

        this.shader = shader != null ? shader : BracketTree.read(Shader.class.getClassLoader().
                getResourceAsStream("forest/colorLineShader.tree")).as(Shader.class);

        vertexGlid = glGenBuffers();
        glBindVertexArray(glid);

        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferData(GL_ARRAY_BUFFER, 36, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 36, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 36, 8);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 1, GL_FLOAT, false, 36, 16);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, 4, GL_FLOAT, false, 36, 20);
        glEnableVertexAttribArray(3);
        glBindVertexArray(0);

    }

    public void setWallSize(float width, float height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void print(LineBrick line) {
        shader.use();

        float thick = line.thick().getFloat();
        var beginPosition = line.begin();
        var endPosition = line.end();
        Color color = line.color().get();

        float[] vertex = new float[]{
                beginPosition.x().getFloat(), beginPosition.y().getFloat(),
                endPosition.x().getFloat(), endPosition.y().getFloat(),
                thick,
                color.red(), color.green(), color.blue(), color.alpha()
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
