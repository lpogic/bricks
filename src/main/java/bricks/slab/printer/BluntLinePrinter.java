package bricks.slab.printer;

import brackettree.BracketTree;
import bricks.Color;
import bricks.slab.BluntLineSlab;
import bricks.slab.Shader;

import java.nio.file.Path;

import static org.lwjgl.opengl.GL30.*;

public class BluntLinePrinter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public BluntLinePrinter(Shader shader) {
        glid = glGenVertexArrays();

        this.shader = shader != null ? shader :
                BracketTree.read(Path.of(System.getProperty("java.home"), "rsc", "forest", "bluntLineShader.tree").toFile()).as(Shader.class);

        vertexGlid = glGenBuffers();
        glBindVertexArray(glid);

        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferData(GL_ARRAY_BUFFER, 40, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 40, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 40, 8);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 1, GL_FLOAT, false, 40, 16);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, 1, GL_FLOAT, false, 40, 20);
        glEnableVertexAttribArray(3);

        glVertexAttribPointer(4, 4, GL_FLOAT, false, 40, 24);
        glEnableVertexAttribArray(4);
        glBindVertexArray(0);

    }

    public void setWallSize(float width, float height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void print(BluntLineSlab line) {
        shader.use();

        float thick = line.thick().getFloat();
        var beginPosition = line.begin();
        var endPosition = line.end();
        Color color = line.color().get();

        float[] vertex = new float[]{
                beginPosition.x().getFloat(), beginPosition.y().getFloat(),
                endPosition.x().getFloat(), endPosition.y().getFloat(),
                Math.max(thick, 3), Math.max(line.radius().getFloat() * 2, 3),
                color.red(), color.green(), color.blue(), color.alpha()
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
