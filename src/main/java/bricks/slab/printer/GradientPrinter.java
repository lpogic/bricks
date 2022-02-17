package bricks.slab.printer;

import brackettree.BracketTree;
import bricks.Color;
import bricks.slab.GradientSlab;
import bricks.slab.Shader;

import java.nio.file.Path;

import static org.lwjgl.opengl.GL30.*;

public class GradientPrinter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public GradientPrinter(Shader shader) {
        glid = glGenVertexArrays();

        this.shader = shader != null ? shader :
                BracketTree.read(Path.of(System.getProperty("java.home"), "rsc", "forest", "colorfulRectangleShader.tree").toFile(), Shader.class);

        vertexGlid = glGenBuffers();
        glBindVertexArray(glid);

        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferData(GL_ARRAY_BUFFER, 80, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 80, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 80, 8);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 4, GL_FLOAT, false, 80, 16);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, 4, GL_FLOAT, false, 80, 32);
        glEnableVertexAttribArray(3);

        glVertexAttribPointer(4, 4, GL_FLOAT, false, 80, 48);
        glEnableVertexAttribArray(4);

        glVertexAttribPointer(5, 4, GL_FLOAT, false, 80, 64);
        glEnableVertexAttribArray(5);
        glBindVertexArray(0);

    }

    public void setWallSize(float width, float height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void print(GradientSlab rectangle) {
        shader.use();

        float width = rectangle.width().getFloat();
        float height = rectangle.height().getFloat();
        float x = rectangle.x().getFloat();
        float y = rectangle.y().getFloat();
        Color clt = rectangle.colorLeftTop().get();
        Color clb = rectangle.colorLeftBottom().get();
        Color crt = rectangle.colorRightTop().get();
        Color crb = rectangle.colorRightBottom().get();

        float[] vertex = new float[]{
                x, y, width, height,
                clt.red(), clt.green(), clt.blue(), clt.alpha(),
                clb.red(), clb.green(), clb.blue(), clb.alpha(),
                crt.red(), crt.green(), crt.blue(), crt.alpha(),
                crb.red(), crb.green(), crb.blue(), crb.alpha(),
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
