package app.model.wall;

import app.model.Color;
import app.model.Point;
import app.model.graphic.ColorLine;
import app.model.graphic.ColorRectangle;
import app.model.graphic.Shader;
import brackettree.reader.BracketTree;

import static org.lwjgl.opengl.GL30.*;

public class ColorLinePainter {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public ColorLinePainter(Shader shader) {
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

    public void setWallSize(int width, int height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void paint(ColorLine line) {
        shader.use();

        float thick = line.getThick();
        Point beginPosition = line.getBeginPosition();
        Point endPosition = line.getEndPosition();
        Color color = line.getColor();

        float[] vertex = new float[]{
                beginPosition.getX(), beginPosition.getY(),
                endPosition.getX(), endPosition.getY(),
                thick,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()
        };

        glBindVertexArray(glid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
